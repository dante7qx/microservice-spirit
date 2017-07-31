package com.spirit.project.syslog.ui.vo.syslog;

import lombok.Data;

/**
 * 服务模块 VO
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
	private String visitTime;	// 访问时间 (yyyy-MM-dd HH:mm:ss)
	private String updateDate;	// 更新时间 (yyyy-MM-dd HH:mm:ss)

}
