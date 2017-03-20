package com.spirit.project.sysmgr.ui.service;

import java.util.List;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

public interface ServiceModuleService {
	public PageResult<ServiceModuleVO> findPage(PageReq pageReq) throws SpiritUIServiceException;

	public ServiceModuleVO findByServiceModuleId(Long id) throws SpiritUIServiceException;

	public void deleteByServiceModuleId(Long id) throws SpiritUIServiceException;

	public ServiceModuleVO updateServiceModule(ServiceModuleVO serviceModuleVO) throws SpiritUIServiceException;
	
	public List<ServiceModuleVO> findServiceModules() throws SpiritUIServiceException;
}
