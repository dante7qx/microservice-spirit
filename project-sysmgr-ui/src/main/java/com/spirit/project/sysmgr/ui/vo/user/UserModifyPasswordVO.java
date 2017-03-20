package com.spirit.project.sysmgr.ui.vo.user;

public class UserModifyPasswordVO {
	
	private Long id;
	private String oldPassword;
	private String newPassword;
	private Long updateUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public Long getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	@Override
	public String toString() {
		return "UserModifyPasswordReqDTO [id=" + id + ", updateUser=" + updateUser + "]";
	}
	
}
