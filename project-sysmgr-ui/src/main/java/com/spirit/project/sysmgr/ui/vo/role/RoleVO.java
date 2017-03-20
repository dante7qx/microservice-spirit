package com.spirit.project.sysmgr.ui.vo.role;

import java.util.Set;

public class RoleVO {
	private Long id;
	private String name;
	private String roleDesc;
	private Set<Long> authorityIds;
	private Long updateUser;
	private String updateUserName;
	private String updateDate;
	
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

	public Set<Long> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(Set<Long> authorityIds) {
		this.authorityIds = authorityIds;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "RoleVO [id=" + id + ", name=" + name + ", roleDesc=" + roleDesc + ", authorityIds=" + authorityIds
				+ ", updateUser=" + updateUser + "]";
	}

}
