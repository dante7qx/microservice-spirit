package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.ServiceModuleReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ServiceModuleRespDTO;

public interface ServiceModuleService extends SpiritAbstractService<ServiceModuleReqDTO, ServiceModuleRespDTO> {
	
	public List<ServiceModuleRespDTO> findServiceModuleResps() throws SpiritAPIServiceException;
	
}
