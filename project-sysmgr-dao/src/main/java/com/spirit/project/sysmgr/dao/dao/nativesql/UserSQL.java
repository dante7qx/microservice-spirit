package com.spirit.project.sysmgr.dao.dao.nativesql;

public interface UserSQL {
	
	public final static String DELETE_USER_ROLE_BY_USER_ID = "delete from t_user_role where user_id = ?1";
	
}
