package com.spirit.project.sysmgr.dao.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_service_module")
@Data
public class ServiceModulePO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String url;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "update_user")
	private UserPO updateUser;
	private Date updateDate;

	public ServiceModulePO() {
		// 默认构造函数
	}

	public ServiceModulePO(Long id) {
		this.id = id;
	}

}
