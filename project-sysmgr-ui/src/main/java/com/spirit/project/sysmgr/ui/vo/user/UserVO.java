package com.spirit.project.sysmgr.ui.vo.user;

import java.util.Set;

import lombok.Data;

/**
 * 用户 VO
 * 
 * @author dante
 *
 */
@Data
public class UserVO {
	private Long id;
	private String account;
	private String name;
	private String email;
	private String password;
	private Long updateUser;
	private String status;
	private Set<Long> roleIds;
	private String updateUserName;
	private String updateDate;

}
