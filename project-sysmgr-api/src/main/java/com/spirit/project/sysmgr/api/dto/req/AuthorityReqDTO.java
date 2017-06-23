package com.spirit.project.sysmgr.api.dto.req;

/**
 * 权限请求参数
 * 
 * @author dante
 *
 */
public class AuthorityReqDTO {
	private Long id;
	private String code;
	private String name;
	private String authorityDesc;
	private Integer showOrder;
	private Long pid;
	private Long updateUser;
	private String updateDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}

	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Integer getShowOrder() {
		return showOrder;
	}

	public void setShowOrder(Integer showOrder) {
		this.showOrder = showOrder;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "AuthorityReqDTO [id=" + id + ", code=" + code + ", name=" + name + ", authorityDesc=" + authorityDesc
				+ ", showOrder=" + showOrder + ", pid=" + pid + ", updateUser=" + updateUser + ", updateDate="
				+ updateDate + "]";
	}

	
}
