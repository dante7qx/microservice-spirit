package com.spirit.project.sysmgr.dao.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_resource")
public class ResourcePO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;
	@Column(name = "full_id")
	private String fullId;
	@Column(name = "show_order")
	private Integer showOrder;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "update_user")
	private UserPO updateUser;
	private Date updateDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_module_id")
	private ServiceModulePO serviceModule;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	private ResourcePO parentResource;

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "authority_id")
	private AuthorityPO authority;

	public ResourcePO() {
	}

	public ResourcePO(Long id) {
		this.id = id;
	}

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

	public ResourcePO getParentResource() {
		return parentResource;
	}

	public void setParentResource(ResourcePO parentResource) {
		this.parentResource = parentResource;
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

	public AuthorityPO getAuthority() {
		return authority;
	}

	public void setAuthority(AuthorityPO authority) {
		this.authority = authority;
	}

	public UserPO getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(UserPO updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public ServiceModulePO getServiceModule() {
		return serviceModule;
	}

	public void setServiceModule(ServiceModulePO serviceModule) {
		this.serviceModule = serviceModule;
	}

}
