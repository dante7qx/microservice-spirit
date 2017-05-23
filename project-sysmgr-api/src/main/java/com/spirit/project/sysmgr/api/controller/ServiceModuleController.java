package com.spirit.project.sysmgr.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.dto.req.ServiceModuleReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ServiceModuleRespDTO;
import com.spirit.project.sysmgr.api.service.ServiceModuleService;

/**
 * 服务模块 REST API
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/servicemodule")
public class ServiceModuleController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ServiceModuleController.class);

	@Autowired
	private ServiceModuleService serviceModuleService;
	
	/**
	 * 分页查询服务模块
	 * 
	 * @param pageReq
	 * @return
	 */
	@PostMapping(value = "/query_page")
	public BaseResp<PageResp<ServiceModuleRespDTO>> queryServiceModulePage(@RequestBody PageReq pageReq) {
		BaseResp<PageResp<ServiceModuleRespDTO>> result = new BaseResp<>();
		try {
			PageResp<ServiceModuleRespDTO> pageResp = serviceModuleService.findPage(pageReq);
			result.setData(pageResp);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("queryServiceModulePage {} error.", pageReq, e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 根据Id获取服务模块
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/query_by_id/{id}")
	public BaseResp<ServiceModuleRespDTO> queryByServiceModuleId(@PathVariable Long id) {
		BaseResp<ServiceModuleRespDTO> result = new BaseResp<>();
		try {
			ServiceModuleRespDTO serviceModuleResp = serviceModuleService.findById(id);
			result.setData(serviceModuleResp);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("queryByServiceModuleId ServiceModuleId: {} error.", id, e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 获取所有服务模块
	 * 
	 * @return
	 */
	@PostMapping(value = "/query_all")
	public BaseResp<List<ServiceModuleRespDTO>> queryAllServiceModule() {
		BaseResp<List<ServiceModuleRespDTO>> result = new BaseResp<>();
		try {
			List<ServiceModuleRespDTO> serviceModuleResp = serviceModuleService.findServiceModuleResps();
			result.setData(serviceModuleResp);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("queryAllServiceModule error.", e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 新增服务模块
	 * 
	 * @param serviceModuleReqDto
	 * @return
	 */
	@PostMapping("/add")
	public BaseResp<ServiceModuleRespDTO> addServiceModule(@RequestBody ServiceModuleReqDTO serviceModuleReqDto) {
		BaseResp<ServiceModuleRespDTO> result = new BaseResp<>();
		if(!checkParam(serviceModuleReqDto, true)) {
			result.setResultCode(RespCodeEnum.LACK_PARAM.code());
			return result;
		}
		try {
			ServiceModuleRespDTO serviceModuleRespDto = serviceModuleService.persist(serviceModuleReqDto);
			result.setData(serviceModuleRespDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("addServiceModule serviceModule: {} error.", serviceModuleReqDto, e);
		}
		return result;
	}
	
	/**
	 * 更新服务模块
	 * 
	 * @param serviceModuleReqDto
	 * @return
	 */
	@PostMapping("/update")
	public BaseResp<ServiceModuleRespDTO> updateServiceModule(@RequestBody ServiceModuleReqDTO serviceModuleReqDto) {
		BaseResp<ServiceModuleRespDTO> result = new BaseResp<>();
		if(!checkParam(serviceModuleReqDto, false)) {
			result.setResultCode(RespCodeEnum.LACK_PARAM.code());
			return result;
		}
		try {
			ServiceModuleRespDTO serviceModuleRespDto = serviceModuleService.persist(serviceModuleReqDto);
			result.setData(serviceModuleRespDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateServiceModule serviceModule: {} error.", serviceModuleReqDto, e);
		}
		return result;
	}
	
	/**
	 * 根据id删除服务模块（物理删除）
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(value = "/delete_by_id/{id}")
	public BaseResp<? extends Object> deleteByServiceModuleId(@PathVariable Long id) {
		BaseResp<? extends Object> result = new BaseResp<>();
		try {
			serviceModuleService.deleteById(id);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("deleteByServiceModuleId serviceModuleId: {} error.", id, e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 参数校验
	 * 
	 * @param userReqDTO
	 * @return
	 */
	private boolean checkParam(ServiceModuleReqDTO serviceModuleReqDTO, boolean isNew) {
		boolean checkValid = true;
		if(!isNew && serviceModuleReqDTO.getId() == null) {
			checkValid = false;
		}
		if(StringUtils.isEmpty(serviceModuleReqDTO.getName())) {
			checkValid = false;
		} else if(StringUtils.isEmpty(serviceModuleReqDTO.getUrl())) {
			checkValid = false;
		} else if(serviceModuleReqDTO.getUpdateUser() == null) {
			checkValid = false;
		}
		return checkValid;
	}
	
}
