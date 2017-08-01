package com.spirit.project.getway.ui.util;

import javax.servlet.http.HttpServletRequest;

import com.spirit.project.common.ui.util.IPUtils;
import com.spirit.project.getway.ui.vo.SysLogVO;

/**
 * 系统日志工具类
 * 
 * @author dante
 *
 */
public final class SysLogUtils {
	
	private SysLogUtils() {
		throw new IllegalAccessError("SysLogUtils工具类，不能实例化！");
	}
	
	/**
	 * 记录用户登录日志
	 * 
	 * @param request
	 */
	public static SysLogVO buildSysLoginLog(String account, Boolean ldapUser, HttpServletRequest request) {
		final String ip = IPUtils.getIpAddr(request);
		final String requestUri = request.getRequestURI();
		if(!requestUri.matches(".*(?<!.gif|.jpg|.png|.bmp|.jpeg|.tiff|.js|.css)$") || "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			return null;
		}
        return buildSysLoginLog(ip, account, ldapUser?"Ldap":"Password");
	}
	
	/**
	 * 记录用户登录日志
	 * 
	 * @param ip
	 * @param account
	 * @param url
	 * @param method
	 */
	private static SysLogVO buildSysLoginLog(String ip, String account, String parameter) {
		SysLogVO sysLog = new SysLogVO();
		sysLog.setIp(ip);
		sysLog.setUserAccount(account);
		sysLog.setRequestUrl("/login");
		sysLog.setRequestMethod("LOGIN");
		sysLog.setRequestParameter(parameter);
		sysLog.setVisitTime("" + System.currentTimeMillis());
		return sysLog;
	}
	
	
	/**
	 * 构造用户注销日志
	 * 
	 * @param account
	 * @param request
	 * @return
	 */
	public static SysLogVO buildSysLogoutLog(String account, HttpServletRequest request) {
		final String ip = IPUtils.getIpAddr(request);
		final String requestUri = request.getRequestURI();
		if(!requestUri.matches(".*(?<!.gif|.jpg|.png|.bmp|.jpeg|.tiff|.js|.css)$") || "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			return null;
		}
		return buildSysLogoutLog(ip, account);
	}
	
	/**
	 * 构造用户注销日志
	 * 
	 * @param ip
	 * @param account
	 * @return
	 */
	private static SysLogVO buildSysLogoutLog(String ip, String account) {
		SysLogVO sysLog = new SysLogVO();
		sysLog.setIp(ip);
		sysLog.setUserAccount(account);
		sysLog.setRequestUrl("/logout");
		sysLog.setRequestMethod("LOGOUT");
		sysLog.setRequestParameter("");
		sysLog.setVisitTime("" + System.currentTimeMillis());
		return sysLog;
	}
	
	/**
	 * 构造用户访问系统日志
	 * 
	 * @param ip
	 * @param account
	 * @param url
	 * @param method
	 * @param parameter
	 * @return
	 */
	public static SysLogVO buildSysVisitLog(String ip, String account, String url, String method, String parameter) {
		SysLogVO sysLog = new SysLogVO();
		sysLog.setIp(ip);
		sysLog.setUserAccount(account);
		sysLog.setRequestUrl(url);
		sysLog.setRequestMethod(method);
		sysLog.setRequestParameter(parameter);
		sysLog.setVisitTime("" + System.currentTimeMillis());
		return sysLog;
	}
}
