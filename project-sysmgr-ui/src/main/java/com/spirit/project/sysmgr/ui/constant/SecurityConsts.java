package com.spirit.project.sysmgr.ui.constant;

/**
 * Spring Security 常量类
 * 
 * @author dante
 *
 */
public final class SecurityConsts {
	
	private SecurityConsts() {
		throw new IllegalAccessError("SecurityConsts 常量类，不能实例化！");
	}
	
	public static final String ROLE_PREFIX = "AUTH_";
	public static final String SESSION_TIMEOUT = "/session-timeout";
	
}
