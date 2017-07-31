package com.spirit.project.syslog.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.syslog.api.dto.req.SysLogReqDTO;
import com.spirit.project.syslog.api.dto.resp.SysLogRespDTO;
import com.spirit.project.syslog.api.service.SysLogService;

/**
 * 用户 REST API
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/syslog")
public class SysLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogController.class);

	@Autowired
	private SysLogService sysLogService;

	/**
	 * 分页查询系统日志
	 * 
	 * @param pageReq
	 * @return
	 */
	@PostMapping("/query_page")
	public BaseResp<PageResp<SysLogRespDTO>> querySysLogPage(@RequestBody PageReq pageReq) {
		BaseResp<PageResp<SysLogRespDTO>> result = new BaseResp<>();
		try {
			PageResp<SysLogRespDTO> pageResp = sysLogService.findPage(pageReq);
			result.setData(pageResp);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("querySysLogPage error.", e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}

	/**
	 * 新增系统日志
	 * 
	 * @param userReqDto
	 * @return
	 */
	@PostMapping("/add")
	public BaseResp<SysLogRespDTO> addSysLog(@RequestBody SysLogReqDTO sysLogReqDto) {
		BaseResp<SysLogRespDTO> result = new BaseResp<>();
		try {
			SysLogRespDTO userRespDto = sysLogService.persist(sysLogReqDto);
			result.setData(userRespDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("addSysLog: {} error.", sysLogReqDto, e);
		}
		return result;
	}

	
}
