package com.spirit.project.sysmgr.api.dto.req;

import java.util.Set;

/**
 * 角色请求参数
 * 
 * @author dante
 *
 */
public class RoleReqDTO {

	private Long id;
	private String name;
	private String roleDesc;
	private Long updateUser;
	private Set<Long> authorityIds;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Set<Long> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(Set<Long> authorityIds) {
		this.authorityIds = authorityIds;
	}

	@Override
	public String toString() {
		return "RoleReqDTO [id=" + id + ", name=" + name + ", roleDesc=" + roleDesc + ", updateUser=" + updateUser
				+ ", authorityIds=" + authorityIds + "]";
	}

}
