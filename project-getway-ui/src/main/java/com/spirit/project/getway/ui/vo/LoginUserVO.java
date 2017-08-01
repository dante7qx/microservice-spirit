package com.spirit.project.getway.ui.vo;

import java.util.Set;

import lombok.Data;

/**
 * 登录用户 VO
 * 
 * @author dante
 *
 */
@Data
public class LoginUserVO {

	private Long id;
	private String account;
	private String password;
	private String name;
	private String email;
	private Boolean ldapUser;
	private Set<String> authoritys;
	private String status;

}
