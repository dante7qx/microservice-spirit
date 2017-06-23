package com.spirit.project.sysmgr.api.dto.req;

/**
 * 服务模块请求参数
 * 
 * @author dante
 *
 */
public class ServiceModuleReqDTO {
	private Long id;
	private String name;
	private String url;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}

	@Override
	public String toString() {
		return "ServiceModuleReqDTO [id=" + id + ", name=" + name + ", url=" + url + ", updateUser=" + updateUser + "]";
	}

}
