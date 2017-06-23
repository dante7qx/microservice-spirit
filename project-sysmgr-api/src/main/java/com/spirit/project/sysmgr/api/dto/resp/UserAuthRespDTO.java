package com.spirit.project.sysmgr.api.dto.resp;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * 当前用户权限返回参数
 * 
 * @author dante
 *
 */
public class UserAuthRespDTO {
	private Long id;
	private String account;
	private String password;
	private String name;
	private String email;
	private Set<String> authoritys;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Set<String> getAuthoritys() {
		if (this.authoritys == null) {
			this.authoritys = Sets.newHashSet();
		}
		return authoritys;
	}

	public void setAuthoritys(Set<String> authoritys) {
		this.authoritys = authoritys;
	}

	@Override
	public String toString() {
		return "UserAuthRespDTO [id=" + id + ", account=" + account + ", password=" + password + ", name=" + name
				+ ", email=" + email + ", authoritys=" + authoritys + "]";
	}

}
