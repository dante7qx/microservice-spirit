package com.spirit.project.commom.util;

import org.junit.Test;
import org.springframework.util.Assert;

/**
 * 加密工具类测试类
 * 
 * @author dante
 *
 */
public class EncryptUtilsTest {
	
	@Test
	public void testEncrypt() {
		String encrt = EncryptUtils.encrypt("123456");
		Assert.notNull(encrt);
	}
	
	@Test
	public void testMatch() {
		String encrt = EncryptUtils.encrypt("123456");
		boolean match = EncryptUtils.match("123456", encrt);
		Assert.isTrue(match);
	}
}
