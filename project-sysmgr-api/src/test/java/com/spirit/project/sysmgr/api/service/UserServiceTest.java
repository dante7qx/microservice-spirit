package com.spirit.project.sysmgr.api.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.BaseSysMgrAPITest;
import com.spirit.project.sysmgr.api.dto.resp.UserRespDTO;


public class UserServiceTest extends BaseSysMgrAPITest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindPage() {
		try {
			PageReq pageReq = new PageReq();
			pageReq.getQ().put("account", "superadmin");
			PageResp<UserRespDTO> pageResult = userService.findPage(pageReq);
			Assert.assertNotNull(pageResult);
		} catch (SpiritAPIServiceException e) {
			e.printStackTrace();
		}
	}
}
