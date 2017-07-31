package com.spirit.project.syslog.dao.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.spirit.project.syslog.dao.BaseSysLogDaoTest;
import com.spirit.project.syslog.dao.po.SysLogPO;

/**
 * 系统日志测试类
 * 
 * @author dante
 *
 */
public class SysLogDAOTest extends BaseSysLogDaoTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogDAOTest.class);

	@Autowired
	private SysLogDAO sysLogDAO;

	@Test
	public void testFindAll() { 
		try {
			List<SysLogPO> sysLogs = sysLogDAO.findAll(new Sort(Direction.DESC, "visitTime"));
			Assert.assertNotNull(sysLogs);
		} catch (Exception e) {
			LOGGER.error("find all sysLog error", e);
		}
	}
}
