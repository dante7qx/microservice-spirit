package com.spirit.project.sysmgr.dao.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.sysmgr.dao.dao.nativesql.AuthorityRoleSQL;
import com.spirit.project.sysmgr.dao.po.AuthorityPO;

public interface AuthorityDAO extends JpaRepository<AuthorityPO, Long> {
	
	@Query("select t from AuthorityPO t where t.parentAuthority.id is null order by t.showOrder asc")
	public List<AuthorityPO> findRootAuthority() throws SpiritDaoException;
	
	@Query("select t from AuthorityPO t where t.parentAuthority.id = :pid order by t.showOrder asc")
	public List<AuthorityPO> findByParentId(@Param("pid") Long pid) throws SpiritDaoException;
	
	@Query(value = AuthorityRoleSQL.FIND_AUTHORITY_ROLE_BY_ROLE_ID, nativeQuery = true)
	public List<Object[]> findAuthorityRoleByRoleId(Long roleId) throws SpiritDaoException;
	
}
