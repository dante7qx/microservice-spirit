package com.spirit.project.sysmgr.api.dto.resp;

/**
 * 服务模块返回参数
 * 
 * @author dante
 *
 */
public class ServiceModuleRespDTO {
	private Long id;
	private String name;
	private String url;
	private String updateUserName;
	private String updateDate;

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

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	@Override
	public String toString() {
		return "ServiceModuleRespDTO [id=" + id + ", name=" + name + ", url=" + url + ", updateUserName="
				+ updateUserName + ", updateDate=" + updateDate + "]";
	}

}
