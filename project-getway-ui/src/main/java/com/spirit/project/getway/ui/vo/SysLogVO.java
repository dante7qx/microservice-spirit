package com.spirit.project.getway.ui.vo;

import lombok.Data;

/**
 * 系统日志 VO
 * 
 * @author dante
 *
 */
@Data
public class SysLogVO {
	private Long id;
	private String ip;
	private String userAccount;
	private String requestUrl;
	private String requestMethod;
	private String requestParameter;
	private String visitTime;
}
