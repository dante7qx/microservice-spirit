package com.spirit.project.sysmgr.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.sysmgr.ui.client.ServiceModuleFeignClient;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

@Component
public class ServiceModuleFeignClientFallback implements ServiceModuleFeignClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceModuleFeignClientFallback.class);
	
	@Override
	public BaseResp<PageResp<ServiceModuleVO>> findPage(PageReq pageReq) {
		LOGGER.error("findByPage pageReq {} fallback.", pageReq);
		BaseResp<PageResp<ServiceModuleVO>> resp = new BaseResp<PageResp<ServiceModuleVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ServiceModuleVO> findByServiceModuleId(Long id) {
		LOGGER.error("findByServiceModuleId id {} fallback.", id);
		BaseResp<ServiceModuleVO> resp = new BaseResp<ServiceModuleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<ServiceModuleVO>> findAllServiceModule() {
		LOGGER.error("findAllServiceModule fallback.");
		BaseResp<List<ServiceModuleVO>> resp = new BaseResp<List<ServiceModuleVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}
	
	@Override
	public BaseResp<ServiceModuleVO> addServiceModule(ServiceModuleVO serviceModuleVO) {
		LOGGER.error("addServiceModule serviceModuleVO {} fallback.", serviceModuleVO);
		BaseResp<ServiceModuleVO> resp = new BaseResp<ServiceModuleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ServiceModuleVO> updateServiceModule(ServiceModuleVO serviceModuleVO) {
		LOGGER.error("updateServiceModule serviceModuleVO {} fallback.", serviceModuleVO);
		BaseResp<ServiceModuleVO> resp = new BaseResp<ServiceModuleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> deleteByServiceModuleId(Long id) {
		LOGGER.error("deleteByServiceModuleId id {} fallback.", id);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
