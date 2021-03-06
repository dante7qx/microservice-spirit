package com.spirit.project.sysmgr.dao.mapper;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.sysmgr.dao.BaseSysMgrDaoTest;
import com.spirit.project.sysmgr.dao.bo.AuthorityRoleBO;

/**
 * AuthorityMapper 测试类
 * 
 * @author dante
 *
 */
public class AuthorityMapperTests extends BaseSysMgrDaoTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityMapperTests.class);
	
	@Autowired
	private AuthorityMapper authorityMapper;
	
	@Test
	public void findAuthorityRoleByRoleId() {
		Long roleId = 1L;
		try {
			List<AuthorityRoleBO> authRoles = authorityMapper.findAuthorityRoleByRoleId(roleId);
			Assert.assertNotNull(authRoles);
		} catch (SpiritDaoException e) {
			LOGGER.error("findAuthorityRoleByRoleId 1 error.", e);
		}
	}
}
