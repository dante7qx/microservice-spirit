package com.spirit.project.getway.ui.prop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "spirit")
@Data
public class SpiritProperties {
	/**
	 * 是否进行校验码验证
	 */
	private Boolean kaptcha;

	/**
	 * 域服务器URL
	 */
	private String ldapurl;
}
