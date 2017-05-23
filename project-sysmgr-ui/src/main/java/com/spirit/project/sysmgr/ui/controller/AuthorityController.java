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
import com.spirit.project.sysmgr.ui.service.AuthorityService;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityTreeVO;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityVO;

/**
 * 权限 UI Controller
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/authority")
public class AuthorityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired
	private AuthorityService authorityService;
	
	/**
	 * 获取权限树
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.authority.query')")
	@PostMapping("/query_tree")
	public List<AuthorityTreeVO> queryAuthorityTree() {
		List<AuthorityTreeVO> trees = Lists.newArrayList();
		try {
			AuthorityTreeVO root = new AuthorityTreeVO();
			root.setId(-1L);
			root.setText("所有权限");
			root.setState(EasyUITreeConsts.STATE_OPEN);
			List<AuthorityTreeVO> childTrees = authorityService.findAuthorityTrees();
			root.setChildren(childTrees);
			trees.add(root);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryAuthorityTree error.", e);
		}
		return trees;
	}
	
	/**
	 * 获取权限 ComboTree
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.authority.query')")
	@PostMapping("/query_combotree")
	public List<AuthorityTreeVO> queryAuthorityComboTree() {
		List<AuthorityTreeVO> trees = Lists.newArrayList();
		try {
			trees = authorityService.findAuthorityTrees();
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryAuthorityTree error.", e);
		}
		return trees;
	}
	
	/**
	 * 更新权限
	 * 
	 * @param authorityVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.authority.update')")
	@PostMapping("/update_authority")
	public BaseResp<AuthorityVO> updateAuthority(AuthorityVO authorityVO) {
		BaseResp<AuthorityVO> result = new BaseResp<>();
		try {
			authorityVO.setUpdateUser(LoginUserUtils.loginUserId());
			AuthorityVO authorityResp = authorityService.updateAuthority(authorityVO);
			result.setData(authorityResp);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateAuthority authorityVO: {} error.", authorityVO, e);
		}
		return result;
	}
	
	/**
	 * 权限拖拽
	 * 
	 * @param dragTreeReq
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.authority.update')")
	@PostMapping("/update_when_drag")
	public BaseResp updateAuthorityWhenDrap(EasyUIDragTreeReq dragTreeReq) {
		BaseResp result = new BaseResp<>();
		try {
			dragTreeReq.setUpdateUser(LoginUserUtils.loginUserId());
			authorityService.updateAuthorityWhenDrag(dragTreeReq);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateAuthorityWhenDrap dragTree {} error.", dragTreeReq, e);
		}
		return result;
	}
	
	/**
	 * 根据id删除权限
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.authority.delete')")
	@PostMapping("/delete_by_id")
	public BaseResp deleteByAuthorityId(Long id) {
		BaseResp result = new BaseResp<>();
		try {
			authorityService.deleteByAuthorityId(id);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteByAuthorityId id: {} error.", id, e);
		}
		return result;
	}
}
