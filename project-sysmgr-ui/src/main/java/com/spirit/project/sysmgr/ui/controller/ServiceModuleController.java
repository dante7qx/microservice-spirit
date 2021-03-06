package com.spirit.project.sysmgr.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.sysmgr.ui.service.ServiceModuleService;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

/**
 * 服务模块 UI Controller
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
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.query')")
	@PostMapping(value = "/query_page")
	public PageResult<ServiceModuleVO> queryServiceModulePage(PageReq pageReq) {
		PageResult<ServiceModuleVO> result = null;
		try {
			result = serviceModuleService.findPage(pageReq);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryServiceModulePage {} error.", pageReq, e);
		}
		return result;
	}

	/**
	 * 根据id获取服务模块
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.query')")
	@PostMapping(value = "/query_by_id")
	public BaseResp<ServiceModuleVO> queryByServiceModuleId(Long id) {
		BaseResp<ServiceModuleVO> result = new BaseResp<>();
		try {
			ServiceModuleVO serviceModuleVO = serviceModuleService.findByServiceModuleId(id);
			result.setData(serviceModuleVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByServiceModuleId serviceModuleId: {} error.", id, e);
		}
		return result;
	}

	/**
	 * 服务模块下拉查询
	 * 
	 * @param q
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.query')")
	@PostMapping(value = "/query_commbo")
	public List<ServiceModuleVO> queryServiceModuleCommbo(Object q) {
		List<ServiceModuleVO> result = null;
		try {
			result = serviceModuleService.findServiceModules();
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryServiceModuleCommbo error.", e);
		}
		return result;
	}
	
	/**
	 * 更新服务模块
	 * 
	 * @param serviceModuleVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.update')")
	@PostMapping(value = "/update_servicemodule")
	public BaseResp<ServiceModuleVO> updateServiceModule(ServiceModuleVO serviceModuleVO) {
		BaseResp<ServiceModuleVO> result = new BaseResp<>();
		try {
			serviceModuleVO.setUpdateUser(LoginUserUtils.loginUserId());
			ServiceModuleVO serviceModuleVOResp = serviceModuleService.updateServiceModule(serviceModuleVO);
			result.setData(serviceModuleVOResp);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateServiceModule serviceModuleVO: {} error.", serviceModuleVO, e);
		}
		return result;
	}

	/**
	 * 删除服务模块
	 * 
	 * @param id
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.delete')")
	@PostMapping(value = "/delete_by_id")
	public BaseResp deleteByServiceModuleId(Long id) {
		BaseResp result = new BaseResp<>();
		try {
			serviceModuleService.deleteByServiceModuleId(id);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteByServiceModuleId id: {} error.", id, e);
		}
		return result;
	}
}
