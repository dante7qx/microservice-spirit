package com.spirit.project.syslog.api.dto.resp;

import lombok.Data;

/**
 * 系统日志请求返回参数
 * 
 * @author dante
 *
 */
@Data
public class SysLogRespDTO {
	private Long id;
	private String ip;
	private String userAccount;
	private String requestUrl;
	private String requestMethod;
	private String requestParameter;
	private String visitTime;	// 访问时间 (yyyy-MM-dd HH:mm:ss)
	private String updateDate;	// 更新时间 (yyyy-MM-dd HH:mm:ss)

}
