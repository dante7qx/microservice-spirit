package com.spirit.project.syslog.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.spirit.project.syslog.dao.po.SysLogPO;


/**
 * 用户 DAO 
 * 
 * @author dante
 *
 */
public interface SysLogDAO extends JpaRepository<SysLogPO, Long>, JpaSpecificationExecutor<SysLogPO>{
		
}
