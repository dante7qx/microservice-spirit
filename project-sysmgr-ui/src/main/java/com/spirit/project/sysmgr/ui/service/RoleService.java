package com.spirit.project.sysmgr.ui.service;

import java.util.List;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

public interface RoleService {

	public PageResult<RoleVO> findPage(PageReq pageReq) throws SpiritUIServiceException;

	public RoleVO findByRoleId(Long id) throws SpiritUIServiceException;

	public void deleteByRoleId(Long id) throws SpiritUIServiceException;

	public RoleVO updateRole(RoleVO roleVO) throws SpiritUIServiceException;
	
	public List<RoleTreeVO> findRoleTree() throws SpiritUIServiceException;

	public List<AuthorityRoleTreeVO> findAuthoritysByRoleId(Long roleId) throws SpiritUIServiceException;
}
