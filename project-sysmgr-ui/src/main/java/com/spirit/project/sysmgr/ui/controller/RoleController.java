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
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.sysmgr.ui.service.RoleService;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

/**
 * 角色 UI Controller
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	/**
	 * 分页查询角色
	 * 
	 * @param pageReq
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.query')")
	@PostMapping(value = "/query_page")
	public PageResult<RoleVO> queryRolePage(PageReq pageReq) {
		PageResult<RoleVO> result = null;
		try {
			result = roleService.findPage(pageReq);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryRolePage {} error.", pageReq, e);
		}
		return result;
	}
	
	/**
	 * 以树的形式返回角色
	 * 
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.query')")
	@PostMapping(value = "/query_role_tree")
	public List<RoleTreeVO> queryRoleTree() {
		RoleTreeVO root = new RoleTreeVO();
		root.setId(-1L);
		root.setText("所有角色");
		try {
			List<RoleTreeVO> children = roleService.findRoleTree();
			root.setChildren(children);
		} catch (Exception e) {
			LOGGER.error("queryRoleTree error.", e);
		}
		return Lists.newArrayList(root);
	}
	
	/**
	 * 根据id获取角色
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.query')")
	@PostMapping(value = "/query_by_id")
	public BaseResp<RoleVO> queryByRoleId(Long id) {
		BaseResp<RoleVO> result = new BaseResp<>();
		try {
			RoleVO roleVO = roleService.findByRoleId(id);
			result.setData(roleVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByRoleId roleId: {} error.", id, e);
		}
		return result;
	}
	
	/**
	 * 更新角色
	 * 
	 * @param roleVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.update')")
	@PostMapping(value = "/update_role")
	public BaseResp<RoleVO> updateRole(RoleVO roleVO) {
		BaseResp<RoleVO> result = new BaseResp<RoleVO>();
		try {
			roleVO.setUpdateUser(LoginUserUtils.loginUserId());
			RoleVO roleVOResp = roleService.updateRole(roleVO);
			result.setData(roleVOResp);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateRole roleVO: {} error.", roleVO, e);
		}
		return result;
	}
	
	/**
	 * 根据id删除角色
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.delete')")
	@PostMapping(value = "/delete_by_id")
	public BaseResp<? extends Object> deleteByRoleId(Long id) {
		BaseResp<? extends Object> result = new BaseResp<>();
		try {
			roleService.deleteByRoleId(id);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteByRoleId id: {} error.", id, e);
		}
		return result;
	}
	
	/**
	 * 查询指定roleId下的权限
	 * 
	 * @param roleId
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.role.query')")
	@PostMapping(value = "/query_role_authority")
	public List<AuthorityRoleTreeVO> findAuthorityRoleByRoleId(Long roleId) {
		List<AuthorityRoleTreeVO> trees = null;
		try {
			trees = roleService.findAuthoritysByRoleId(roleId);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("findAuthorityRoleByRoleId roleId: {} error.", roleId, e);
		}
		return trees;
	}
}
