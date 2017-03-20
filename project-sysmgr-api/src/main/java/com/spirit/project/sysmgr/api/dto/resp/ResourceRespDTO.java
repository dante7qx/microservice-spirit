package com.spirit.project.sysmgr.api.dto.resp;

public class ResourceRespDTO {
	private Long id;
	private String name;
	private String url;
	private Long authorityId;
	private Long serviceModuleId;
	private String serviceModuleName;
	private String serviceModuleUrl;
	private String fullId;
	private Integer showOrder;
	private Long pid;

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

	public Long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}

	public String getFullId() {
		return fullId;
	}

	public void setFullId(String fullId) {
		this.fullId = fullId;
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

	public Long getServiceModuleId() {
		return serviceModuleId;
	}

	public void setServiceModuleId(Long serviceModuleId) {
		this.serviceModuleId = serviceModuleId;
	}

	public String getServiceModuleName() {
		return serviceModuleName;
	}

	public void setServiceModuleName(String serviceModuleName) {
		this.serviceModuleName = serviceModuleName;
	}

	public String getServiceModuleUrl() {
		return serviceModuleUrl;
	}

	public void setServiceModuleUrl(String serviceModuleUrl) {
		this.serviceModuleUrl = serviceModuleUrl;
	}

}
