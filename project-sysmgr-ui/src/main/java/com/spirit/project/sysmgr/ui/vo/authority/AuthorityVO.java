package com.spirit.project.sysmgr.ui.vo.authority;

public class AuthorityVO {
	private Long id;
	private String name;
	private String code;
	private String authorityDesc;
	private Integer showOrder;
	private Long pid;
	private Long updateUser;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "AuthorityVO [id=" + id + ", name=" + name + ", code=" + code + ", authorityDesc=" + authorityDesc
				+ ", showOrder=" + showOrder + ", pid=" + pid + "]";
	}

}
