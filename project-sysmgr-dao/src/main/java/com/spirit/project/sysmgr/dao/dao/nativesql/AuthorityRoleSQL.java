package com.spirit.project.sysmgr.dao.dao.nativesql;

/**
 * 角色权限原生SQL
 * 
 * @author dante
 *
 */
public final class AuthorityRoleSQL {
	
	private AuthorityRoleSQL() {
		throw new IllegalAccessError("AuthorityRoleSQL 常量类，不能实例化！");
	}
	
	// 根据roleId获取角色、权限信息
	public static final String FIND_AUTHORITY_ROLE_BY_ROLE_ID = "select t.id, t.name, t.code, t.authority_desc as authorityDesc, t.show_order as showOrder, t.pid, t1.role_id as roleId"
														 + " from t_authority t "
														 + " left join t_authority t2 on t.pid = t2.id"
														 + " left join t_role_authority t1 on t.id = t1.authority_id and t1.role_id = ?1"
														 + " order by t2.show_order asc, t.show_order asc";
}
