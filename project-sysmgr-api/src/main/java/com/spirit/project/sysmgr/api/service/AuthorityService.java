package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.AuthorityReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.AuthorityRespDTO;

public interface AuthorityService extends SpiritAbstractService<AuthorityReqDTO, AuthorityRespDTO> {

	public List<AuthorityRespDTO> findRootAuthority() throws SpiritAPIServiceException;

	public List<AuthorityRespDTO> findByPid(Long pid) throws SpiritAPIServiceException;
}
