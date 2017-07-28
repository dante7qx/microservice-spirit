package com.spirit.project.sysmgr.ui.vo.user;

import lombok.Data;

/**
 * 修改密码 VO
 * 
 * @author dante
 *
 */
@Data
public class UserModifyPasswordVO {
	
	private Long id;
	private String oldPassword;
	private String newPassword;
	private Long updateUser;
	
}
