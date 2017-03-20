package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.RoleReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.AuthorityRoleRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.RoleRespDTO;

public interface RoleService extends SpiritAbstractService<RoleReqDTO, RoleRespDTO>{
	
	public List<RoleRespDTO> findAllRoles() throws SpiritAPIServiceException;
	
	public List<AuthorityRoleRespDTO> findAuthorityRoleByRoleId(Long roleId) throws SpiritAPIServiceException;
	
}
