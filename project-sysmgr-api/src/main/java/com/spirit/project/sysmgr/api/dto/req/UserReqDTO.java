package com.spirit.project.sysmgr.api.dto.req;

import java.util.Set;

/**
 * 用户请求参数
 * 
 * @author dante
 *
 */
public class UserReqDTO {

	private Long id;
	private String account;
	private String name;
	private String password;
	private String email;
	private Long updateUser;
	private Set<Long> roleIds;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public Set<Long> getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Set<Long> roleIds) {
		this.roleIds = roleIds;
	}
	
	@Override
	public String toString() {
		return "UserReqDto [id=" + id + ", account=" + account + ", name=" + name + ", email=" + email + ", roleIds=" + roleIds + "]";
	}

}
