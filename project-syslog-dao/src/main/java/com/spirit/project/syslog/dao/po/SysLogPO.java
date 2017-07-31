package com.spirit.project.syslog.dao.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * 系统日志 PO
 * 
 * @author dante
 *
 */
@Entity
@Table(name = "t_sys_log")
@Data
public class SysLogPO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String ip;
	private String userAccount;
	private String requestUrl;
	private String requestMethod;
	private Date visitTime;
	private Date updateDate;

	public SysLogPO() {
		// 默认构造函数
	}

	public SysLogPO(Long id) {
		this.id = id;
	}

}
