package com.spirit.project.sysmgr.dao.mapper;

import java.util.List;

import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.sysmgr.dao.bo.AuthorityRoleBO;


/**
 * 权限 Mapper
 * 
 * @author dante
 *
 */
public interface AuthorityMapper {

	public List<AuthorityRoleBO> findAuthorityRoleByRoleId(Long roleId) throws SpiritDaoException;
	
}
