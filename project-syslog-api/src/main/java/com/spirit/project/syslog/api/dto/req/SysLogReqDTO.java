package com.spirit.project.syslog.api.dto.req;

import lombok.Data;

/**
 * 系统日志请求参数
 * 
 * @author dante
 *
 */
@Data
public class SysLogReqDTO {

	private Long id;
	private String ip;
	private String userAccount;
	private String requestUrl;
	private String requestMethod;
	private String requestParameter;
	private String visitTime;
}
