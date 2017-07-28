package com.spirit.project.sysmgr.dao.bo;

import lombok.Data;

/**
 * 角色、权限业务辅助BO
 * 
 * @author dante
 *
 */
@Data
public class AuthorityRoleBO {
	private Long id;
	private Long pid;
	private String name;
	private String code;
	private String authorityDesc;
	private Integer showOrder;
	private Long roleId;
	private Boolean hasRelRole = false;

	public AuthorityRoleBO() {
		// 默认构造函数
	}

	/**
	 * 构造函数参数的顺序必须与NativeSQL中查询字段顺序保持一致
	 * 
	 * @param id
	 * @param name
	 * @param code
	 * @param authorityDesc
	 * @param showOrder
	 * @param pid
	 * @param roleId
	 */
	public AuthorityRoleBO(Object id, Object name, Object code, Object authorityDesc, Object showOrder, Object pid,
			Object roleId) {
		this.id = id != null ? Long.parseLong(id.toString()) : null;
		this.pid = pid != null ? Long.parseLong(pid.toString()) : null;
		this.name = name != null ? name.toString() : null;
		this.code = code != null ? code.toString() : null;
		this.authorityDesc = authorityDesc != null ? authorityDesc.toString() : null;
		this.showOrder = showOrder != null ? Integer.parseInt(showOrder.toString()) : 1;
		this.roleId = roleId != null ? Long.parseLong(roleId.toString()) : null;
	}

}
