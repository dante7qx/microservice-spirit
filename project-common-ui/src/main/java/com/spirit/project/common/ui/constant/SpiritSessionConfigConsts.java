package com.spirit.project.common.ui.constant;

/**
 * Spring Session 配置常量
 * 
 * @author dante
 *
 */
public class SpiritSessionConfigConsts {
	
	private SpiritSessionConfigConsts() {
		throw new IllegalAccessError("SpiritSessionConfigConsts 常量类，不能实例化！");
	}
	
	/**
	 * session在redis中的命名控件
	 */
	public static final String REDIS_NAMESPACE = "PROJECT_SESSION";
	
	/**
	 * session过期时间，默认 1800s（30分钟）
	 */
	public static final int MAX_INACTIVE_INTERVAL_IN_SECONDS = 3600;
	
}
