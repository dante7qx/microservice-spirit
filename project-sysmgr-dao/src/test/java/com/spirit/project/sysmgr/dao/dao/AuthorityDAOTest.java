package com.spirit.project.sysmgr.dao.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.sysmgr.dao.BaseSysMgrDaoTest;

public class AuthorityDAOTest extends BaseSysMgrDaoTest {
	
	@Autowired
	private AuthorityDAO authorityDAO;
	
	@Test
	public void testFindAuthorityRoleByRoleId() {
		try {
			List<Object[]> list = authorityDAO.findAuthorityRoleByRoleId(1L);
			Assert.assertNotNull(list);
		} catch (SpiritDaoException e) {
			e.printStackTrace();
		}
		
	}
}
