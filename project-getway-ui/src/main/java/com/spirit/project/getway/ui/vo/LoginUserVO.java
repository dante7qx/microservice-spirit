package com.spirit.project.getway.ui.vo;

import java.util.Set;

public class LoginUserVO {

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
		return authoritys;
	}

	public void setAuthoritys(Set<String> authoritys) {
		this.authoritys = authoritys;
	}

}
