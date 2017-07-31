package com.spirit.project.syslog.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.syslog.ui.service.SysLogService;
import com.spirit.project.syslog.ui.vo.syslog.SysLogVO;

/**
 * 系统日志 UI Controller
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/syslog")
public class SysLogController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogController.class);

	@Autowired
	private SysLogService serviceModuleService;

	/**
	 * 分页查询服务模块
	 * 
	 * @param pageReq
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.servicemodule.query')")
	@PostMapping(value = "/query_page")
	public PageResult<SysLogVO> queryServiceModulePage(PageReq pageReq) {
		PageResult<SysLogVO> result = null;
		try {
			result = serviceModuleService.findPage(pageReq);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryServiceModulePage {} error.", pageReq, e);
		}
		return result;
	}

}
