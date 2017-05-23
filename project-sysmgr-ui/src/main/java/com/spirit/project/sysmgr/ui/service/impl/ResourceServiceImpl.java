package com.spirit.project.sysmgr.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;
import com.spirit.project.common.ui.dto.req.EasyUIDragTreeReq;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.client.ResourceFeignClient;
import com.spirit.project.sysmgr.ui.service.ResourceService;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceTreeVO;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceVO;

@Service
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private ResourceFeignClient resourceFeignClient;

	@Override
	public ResourceVO findByResourceId(Long id) throws SpiritUIServiceException {
		BaseResp<ResourceVO> resp = resourceFeignClient.findByResourceId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public List<ResourceTreeVO> findResourceTrees() throws SpiritUIServiceException {

		BaseResp<List<ResourceVO>> resp = resourceFeignClient.findRootResource();
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<ResourceTreeVO> roots = Lists.newArrayList();
		List<ResourceVO> resourceVOs = resp.getData();
		if (CollectionUtils.isEmpty(resourceVOs)) {
			return roots;
		}
		for (ResourceVO resourceVO : resourceVOs) {
			ResourceTreeVO treeVO = convertToResourceTreeVO(resourceVO);
			roots.add(treeVO);
			buildResourceTree(treeVO);
		}
		return roots;
	}

	private void buildResourceTree(ResourceTreeVO tree) throws SpiritUIServiceException {
		Long id = tree.getId();
		BaseResp<List<ResourceVO>> resp = resourceFeignClient.findByResourcePid(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<ResourceVO> childResources = resp.getData();
		if (!CollectionUtils.isEmpty(childResources)) {
			for (ResourceVO childResource : childResources) {
				ResourceTreeVO childTree = convertToResourceTreeVO(childResource);
				tree.getChildren().add(childTree);
				buildResourceTree(childTree);
			}
		}
	}

	private ResourceTreeVO convertToResourceTreeVO(ResourceVO resourceVO) {
		ResourceTreeVO tree = new ResourceTreeVO();
		tree.setId(resourceVO.getId());
		tree.setText(resourceVO.getName());
		tree.setAttributes(resourceVO);
		return tree;
	}

	@Override
	public void deleteByResourceId(Long id) throws SpiritUIServiceException {
		BaseResp<? extends Object> resp = resourceFeignClient.deleteByResourceId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	public ResourceVO updateResource(ResourceVO resourceVO) throws SpiritUIServiceException {
		Long id = resourceVO.getId();
		BaseResp<ResourceVO> resp;
		if (id == null) {
			resp = resourceFeignClient.addResource(resourceVO);
		} else {
			resp = resourceFeignClient.updateResource(resourceVO);
		}
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public void updateResourceWhenDrag(EasyUIDragTreeReq dragTreeReq) throws SpiritUIServiceException {
		String point = dragTreeReq.getPoint();
		if (EasyUITreeConsts.POINT_APPEND.equalsIgnoreCase(point)) {
			handleDragAppend(dragTreeReq.getTargetId(), dragTreeReq.getSourceId(), dragTreeReq.getUpdateUser());
		} else if (EasyUITreeConsts.POINT_TOP.equalsIgnoreCase(point)) {
			handleDragTop(dragTreeReq.getTargetPid(), dragTreeReq.getTargetShowOrder(), dragTreeReq.getSourceId(),
					dragTreeReq.getUpdateUser());
		} else if (EasyUITreeConsts.POINT_BOTTOM.equalsIgnoreCase(point)) {
			handleDragBottom(dragTreeReq.getTargetPid(), dragTreeReq.getTargetShowOrder(), dragTreeReq.getSourceId(),
					dragTreeReq.getUpdateUser());
		} else {
			throw new SpiritUIServiceException("Drag point mush match 'append' 'top' 'bottom'");
		}
	}

	/**
	 * 将源节点移动到目标节点的上方 showOrder(source) = showOrder(target) - 1, pid(source) =
	 * pid(target)
	 * 
	 * @param targetPid
	 * @param targetShowOrder
	 * @param sourceId
	 * @throws SpiritUIServiceException
	 */
	@HystrixCommand
	private void handleDragTop(Long targetPid, int targetShowOrder, Long sourceId, Long updateUser)
			throws SpiritUIServiceException {
		ResourceVO sourceResource = findByResourceId(sourceId);
		sourceResource.setPid(targetPid);
		sourceResource.setShowOrder(targetShowOrder > 1 ? (targetShowOrder - 1) : 1);
		sourceResource.setUpdateUser(updateUser);
		resourceFeignClient.updateResource(sourceResource);
	}

	/**
	 * 将源节点移动到目标节点的下方 showOrder(source) = showOrder(target) - 1, pid(source) =
	 * pid(target)
	 * 
	 * @param targetPid
	 * @param targetShowOrder
	 * @param sourceId
	 * @throws SpiritUIServiceException
	 */
	@HystrixCommand
	private void handleDragBottom(Long targetPid, int targetShowOrder, Long sourceId, Long updateUser)
			throws SpiritUIServiceException {
		ResourceVO sourceResource = findByResourceId(sourceId);
		sourceResource.setPid(targetPid);
		sourceResource.setShowOrder(targetShowOrder + 1);
		sourceResource.setUpdateUser(updateUser);
		resourceFeignClient.updateResource(sourceResource);
	}

	/**
	 * 将源节点移动到目标节点内 pid(source) = id(target)
	 * 
	 * @param targetId
	 * @param sourceId
	 * @throws SpiritUIServiceException
	 */
	@HystrixCommand
	private void handleDragAppend(Long targetId, Long sourceId, Long updateUser) throws SpiritUIServiceException {
		ResourceVO sourceResource = findByResourceId(sourceId);
		if (targetId > 0) {
			sourceResource.setPid(targetId);
		}
		sourceResource.setUpdateUser(updateUser);
		resourceFeignClient.updateResource(sourceResource);
	}

}
