package com.spirit.project.sysmgr.dao.dao;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.sysmgr.dao.BaseSysMgrDaoTest;
import com.spirit.project.sysmgr.dao.po.ServiceModulePO;
import com.spirit.project.sysmgr.dao.po.UserPO;

public class ServiveModuleDaoTest extends BaseSysMgrDaoTest {

	@Autowired
	private ServiceModuleDAO serviveModuleDAO;
	
	@Test
	public void testSave() {
		ServiceModulePO sm = new ServiceModulePO();
		
		sm.setName("test");
		sm.setUrl("sysmgr");
		sm.setUpdateUser(new UserPO(1L));
		sm.setUpdateDate(DateUtils.currentDate());
		serviveModuleDAO.save(sm);
	}
	
	public static void main(String[] args) {
		System.out.println(Instant.now());
		System.out.println(Clock.systemDefaultZone());
		System.out.println(new Date());
		System.out.println(new Date().toInstant());
		System.out.println(ZoneId.systemDefault().toString());
		System.out.println(Date.from(Instant.now()));
	}
}
