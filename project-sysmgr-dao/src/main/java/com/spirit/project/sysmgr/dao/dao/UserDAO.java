package com.spirit.project.sysmgr.dao.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.sysmgr.dao.dao.nativesql.UserSQL;
import com.spirit.project.sysmgr.dao.po.UserPO;

public interface UserDAO extends JpaRepository<UserPO, Long>, JpaSpecificationExecutor<UserPO>{
	public UserPO findByAccount(String account) throws SpiritDaoException;
	
	@Modifying
	@Query(value = UserSQL.DELETE_USER_ROLE_BY_USER_ID, nativeQuery = true)
	public void deleteUserRoleByUserId(Long userId);
	
}
