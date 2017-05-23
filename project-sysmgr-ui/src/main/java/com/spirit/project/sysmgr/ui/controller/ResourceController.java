package com.spirit.project.sysmgr.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;
import com.spirit.project.common.ui.dto.req.EasyUIDragTreeReq;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.sysmgr.ui.service.ResourceService;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceTreeVO;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceVO;

/**
 * 资源 UI Controller
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 获取资源树
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.resource.query')")
	@PostMapping("/query_tree")
	public List<ResourceTreeVO> queryResourceTree() {
		List<ResourceTreeVO> trees = Lists.newArrayList();
		try {
			ResourceTreeVO root = new ResourceTreeVO();
			root.setId(-1L);
			root.setText("所有菜单");
			root.setState(EasyUITreeConsts.STATE_OPEN);
			List<ResourceTreeVO> childTrees = resourceService.findResourceTrees();
			root.setChildren(childTrees);
			trees.add(root);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryResourceTree error.", e);
		}
		return trees;
	}
	
	/**
	 * 更新资源
	 * 
	 * @param resourceVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.resource.update')")
	@PostMapping("/update_resource")
	public BaseResp<ResourceVO> updateResource(ResourceVO resourceVO) {
		BaseResp<ResourceVO> result = new BaseResp<>();
		try {
			resourceVO.setUpdateUser(LoginUserUtils.loginUserId());
			ResourceVO resourceResp = resourceService.updateResource(resourceVO);
			result.setData(resourceResp);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateResource resourceVO: {} error.", resourceVO, e);
		}
		return result;
	}
	
	/**
	 * 资源的拖拽
	 * 
	 * @param dragTreeReq
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.resource.update')")
	@PostMapping("/update_when_drag")
	public BaseResp updateResourceWhenDrap(EasyUIDragTreeReq dragTreeReq) {
		BaseResp result = new BaseResp<>();
		try {
			dragTreeReq.setUpdateUser(LoginUserUtils.loginUserId());
			resourceService.updateResourceWhenDrag(dragTreeReq);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateResourceWhenDrap dragTree {} error.", dragTreeReq, e);
		}
		return result;
	}
	
	/**
	 * 根据id删除资源
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.resource.delete')")
	@PostMapping("/delete_by_id")
	public BaseResp deleteByResourceId(Long id) {
		BaseResp result = new BaseResp<>();
		try {
			resourceService.deleteByResourceId(id);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteByResourceId id: {} error.", id, e);
		}
		return result;
	}
}
