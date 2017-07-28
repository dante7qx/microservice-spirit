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

import lombok.Data;

@Entity
@Table(name = "t_resource")
@Data
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
		// 默认构造函数
	}

	public ResourcePO(Long id) {
		this.id = id;
	}

}
