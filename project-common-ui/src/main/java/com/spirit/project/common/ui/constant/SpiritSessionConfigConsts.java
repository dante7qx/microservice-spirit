package com.spirit.project.common.ui.constant;

/**
 * Spring Session 配置常量
 * 
 * @author dante
 *
 */
public interface SpiritSessionConfigConsts {
	
	/**
	 * session在redis中的命名控件
	 */
	public final static String REDIS_NAMESPACE = "PROJECT_SESSION";
	
	/**
	 * session过期时间，默认 1800s（30分钟）
	 */
	public final static int MAX_INACTIVE_INTERVAL_IN_SECONDS = 3600;
	
}
