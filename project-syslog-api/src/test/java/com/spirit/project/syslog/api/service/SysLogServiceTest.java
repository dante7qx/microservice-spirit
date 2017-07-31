package com.spirit.project.syslog.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.syslog.api.BaseSysLogAPITest;
import com.spirit.project.syslog.api.dto.req.SysLogReqDTO;
import com.spirit.project.syslog.api.dto.resp.SysLogRespDTO;

/**
 * 系统日志测试类
 * 
 * @author dante
 *
 */
public class SysLogServiceTest extends BaseSysLogAPITest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogServiceTest.class);
	
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 测试分页 
	 */
	@Test
	public void testFindPage() {
		try {
			PageReq pageReq = new PageReq();
			pageReq.getQ().put("ip", "88.202");
			pageReq.getQ().put("visitStartTime", "2017-07-28");
			pageReq.getQ().put("visitEndTime", "2017-08-01");
			PageResp<SysLogRespDTO> pageResult = sysLogService.findPage(pageReq);
			Assert.assertNotNull(pageResult);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("testFindPage error", e);
		}
	}
	
	/**
	 * 测试持久化
	 */
	@Test
	public void testPersist() {
		SysLogReqDTO sysLogReqDTO = new SysLogReqDTO();
		try {
			sysLogReqDTO.setIp("10.71.88.214");
			sysLogReqDTO.setRequestUrl("/sysmgr/user/query_page");
			sysLogReqDTO.setRequestMethod("POST");
			SysLogRespDTO sysLogRespDTO = sysLogService.persist(sysLogReqDTO);
			Assert.assertNotNull(sysLogRespDTO);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("testPersist {} error", sysLogReqDTO, e);
		}
	}
}
