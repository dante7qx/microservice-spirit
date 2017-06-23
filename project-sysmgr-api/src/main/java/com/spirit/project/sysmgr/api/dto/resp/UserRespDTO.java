package com.spirit.project.sysmgr.api.dto.resp;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 用户请求返回参数
 * 
 * @author dante
 *
 */
public class UserRespDTO {
	private Long id;
	private String account;
	private String name;
	private String email;
	private String updateUserName;
	private String updateDate;
	private Set<Long> roleIds;
	private String status;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Long> getRoleIds() {
		if (roleIds == null) {
			roleIds = Sets.newHashSet();
		}
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}

	@Override
	public String toString() {
		return "UserRespDTO [id=" + id + ", account=" + account + ", name=" + name + ", email=" + email
				+ ", updateUserName=" + updateUserName + ", updateDate=" + updateDate + ", roleIds=" + roleIds
				+ ", status=" + status + "]";
	}

}
