package com.spirit.project.sysmgr.dao.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.spirit.project.sysmgr.dao.BaseSysMgrDaoTest;
import com.spirit.project.sysmgr.dao.dao.UserDAO;
import com.spirit.project.sysmgr.dao.po.UserPO;

public class UserDAOTest extends BaseSysMgrDaoTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	public void testFindAll() {
		try {
			List<UserPO> users = userDAO.findAll();
			System.out.println(users);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
