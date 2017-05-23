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
import com.spirit.project.sysmgr.ui.client.AuthorityFeignClient;
import com.spirit.project.sysmgr.ui.service.AuthorityService;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityTreeVO;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityVO;

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Autowired
	private AuthorityFeignClient authorityFeignClient;
	
	@Override
	@HystrixCommand
	public AuthorityVO findByAuthorityId(Long id) throws SpiritUIServiceException {
		BaseResp<AuthorityVO> resp = authorityFeignClient.findByAuthorityId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public List<AuthorityTreeVO> findAuthorityTrees() throws SpiritUIServiceException {
		BaseResp<List<AuthorityVO>> resp = authorityFeignClient.findRootAuthority();
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<AuthorityTreeVO> trees = Lists.newArrayList();
		List<AuthorityVO> authorityVOs = resp.getData();
		if(CollectionUtils.isEmpty(authorityVOs)) {
			return trees;
		}
		for (AuthorityVO authorityVO : authorityVOs) {
			AuthorityTreeVO treeVO = convertToTree(authorityVO);
			trees.add(treeVO);
			buildAuthorityTree(treeVO);
		}
		return trees;
	}
	
	private void buildAuthorityTree(AuthorityTreeVO treeVO) throws SpiritUIServiceException {
		Long id = treeVO.getId();
		BaseResp<List<AuthorityVO>> resp = authorityFeignClient.findByAuthorityPid(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<AuthorityVO> childVos = resp.getData();
		if(!CollectionUtils.isEmpty(childVos)) {
			for (AuthorityVO authorityVO : childVos) {
				AuthorityTreeVO childTreeVO = convertToTree(authorityVO);
				treeVO.getChildren().add(childTreeVO);
				buildAuthorityTree(childTreeVO);
			}
		} else {
			treeVO.setState(EasyUITreeConsts.STATE_OPEN);
		}
	}
	
	private AuthorityTreeVO convertToTree(AuthorityVO authorityVO) {
		AuthorityTreeVO treeVO = new AuthorityTreeVO();
		treeVO.setId(authorityVO.getId());
		treeVO.setText(authorityVO.getName());
		treeVO.setAttributes(authorityVO);
		return treeVO;
	}

	@Override
	@HystrixCommand
	public void deleteByAuthorityId(Long id) throws SpiritUIServiceException {
		BaseResp<?> resp = authorityFeignClient.deleteByAuthorityId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	@HystrixCommand
	public AuthorityVO updateAuthority(AuthorityVO authorityVO) throws SpiritUIServiceException {
		Long id = authorityVO.getId();
		BaseResp<AuthorityVO> resp;
		if(id == null) {
			resp = authorityFeignClient.addAuthority(authorityVO);
		} else {
			resp = authorityFeignClient.updateAuthority(authorityVO);
		}
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public void updateAuthorityWhenDrag(EasyUIDragTreeReq dragTreeReq) throws SpiritUIServiceException {
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
	 * 将源节点移动到目标节点的上方
	 * showOrder(source) = showOrder(target) - 1, pid(source) = pid(target)
	 * 
	 * @param targetPid
	 * @param targetShowOrder
	 * @param sourceId
	 * @throws SpiritUIServiceException 
	 */
	@HystrixCommand
	private void handleDragTop(Long targetPid, int targetShowOrder, Long sourceId, Long updateUser)
			throws SpiritUIServiceException {
		AuthorityVO sourceAuthority = findByAuthorityId(sourceId);
		sourceAuthority.setPid(targetPid);
		sourceAuthority.setShowOrder(targetShowOrder > 1 ? (targetShowOrder - 1) : 1);
		sourceAuthority.setUpdateUser(updateUser);
		authorityFeignClient.updateAuthority(sourceAuthority);
	}
	
	/**
	 * 将源节点移动到目标节点的下方
	 * showOrder(source) = showOrder(target) - 1, pid(source) = pid(target)
	 * 
	 * @param targetPid
	 * @param targetShowOrder
	 * @param sourceId
	 * @throws SpiritUIServiceException 
	 */
	@HystrixCommand
	private void handleDragBottom(Long targetPid, int targetShowOrder, Long sourceId, Long updateUser)
			throws SpiritUIServiceException {
		AuthorityVO sourceAuthority = findByAuthorityId(sourceId);
		sourceAuthority.setPid(targetPid);
		sourceAuthority.setShowOrder(targetShowOrder + 1);
		sourceAuthority.setUpdateUser(updateUser);
		authorityFeignClient.updateAuthority(sourceAuthority);
	}

	/**
	 * 将源节点移动到目标节点内
	 * pid(source) = id(target)
	 * 
	 * @param targetId
	 * @param sourceId
	 * @throws SpiritUIServiceException 
	 */
	@HystrixCommand
	private void handleDragAppend(Long targetId, Long sourceId, Long updateUser) throws SpiritUIServiceException {
		AuthorityVO sourceAuthority = findByAuthorityId(sourceId);
		if (targetId > 0) {
			sourceAuthority.setPid(targetId);
		}
		sourceAuthority.setUpdateUser(updateUser);
		authorityFeignClient.updateAuthority(sourceAuthority);
	}
	
}
