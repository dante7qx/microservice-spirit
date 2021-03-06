package com.spirit.project.sysmgr.dao.dao.nativesql;

/**
 * 用户相关原生SQL
 * 
 * @author dante
 *
 */
public final class UserSQL {
	
	private UserSQL() {
		throw new IllegalAccessError("UserSQL 常量类，不能实例化！");
	}
	
	// 根据userId删除用户角色表信息
	public static final String DELETE_USER_ROLE_BY_USER_ID = "delete from t_user_role where user_id = ?1";
	
}
