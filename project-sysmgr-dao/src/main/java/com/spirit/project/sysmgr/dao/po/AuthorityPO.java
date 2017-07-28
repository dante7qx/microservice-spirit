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
@Table(name = "t_authority")
@Data
public class AuthorityPO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String code;
	private String authorityDesc;
	private Integer showOrder;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "update_user")
	private UserPO updateUser;
	private Date updateDate;
	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	private AuthorityPO parentAuthority;

	public AuthorityPO() {
		// 默认构造函数
	}

	public AuthorityPO(Long id) {
		this.id = id;
	}

}
