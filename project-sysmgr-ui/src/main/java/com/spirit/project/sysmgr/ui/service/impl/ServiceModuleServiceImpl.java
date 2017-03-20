package com.spirit.project.sysmgr.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.client.ServiceModuleFeignClient;
import com.spirit.project.sysmgr.ui.service.ServiceModuleService;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

@Service
public class ServiceModuleServiceImpl implements ServiceModuleService {

	@Autowired
	private ServiceModuleFeignClient serviceModuleFeignClient;

	@Override
	@HystrixCommand
	public PageResult<ServiceModuleVO> findPage(PageReq pageReq) throws SpiritUIServiceException {
		PageResult<ServiceModuleVO> pageResult = new PageResult<ServiceModuleVO>();
		BaseResp<PageResp<ServiceModuleVO>> resp = serviceModuleFeignClient.findPage(pageReq);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		PageResp<ServiceModuleVO> pageResp = resp.getData();
		pageResult.setRows(pageResp.getResult());
		pageResult.setTotal(pageResp.getTotalCount());
		return pageResult;
	}

	@Override
	@HystrixCommand
	public ServiceModuleVO findByServiceModuleId(Long id) throws SpiritUIServiceException {
		BaseResp<ServiceModuleVO> resp = serviceModuleFeignClient.findByServiceModuleId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public void deleteByServiceModuleId(Long id) throws SpiritUIServiceException {
		BaseResp<?> resp = serviceModuleFeignClient.deleteByServiceModuleId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	@HystrixCommand
	public ServiceModuleVO updateServiceModule(ServiceModuleVO serviceModuleVO) throws SpiritUIServiceException {
		Long id = serviceModuleVO.getId();
		BaseResp<ServiceModuleVO> resp = null;
		if (id == null) {
			resp = serviceModuleFeignClient.addServiceModule(serviceModuleVO);
		} else {
			resp = serviceModuleFeignClient.updateServiceModule(serviceModuleVO);
		}
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public List<ServiceModuleVO> findServiceModules() throws SpiritUIServiceException {
		BaseResp<List<ServiceModuleVO>> resp = serviceModuleFeignClient.findAllServiceModule();
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

}
