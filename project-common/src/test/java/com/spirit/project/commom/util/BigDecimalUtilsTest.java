package com.spirit.project.commom.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BigDecimalUtils 测试类
 * 
 * @author dante
 *
 */
public class BigDecimalUtilsTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BigDecimalUtilsTest.class);
	
	@Test
	public void testAdd() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.add(d1, d2);
		LOGGER.debug("add result -> {}", result);
	}
	
	@Test
	public void testSub() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.sub(d2, d1);
		LOGGER.debug("sub result -> {}", result);
	}
	
	@Test
	public void testMul() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.mul(d2, d1);
		LOGGER.debug("mul result -> {}", result);
	}
	
	@Test
	public void testDiv() throws IllegalAccessException {
		Double d1 = 3.0;
		Double d2 = 7.0;
		double result = BigDecimalUtils.div(d2, d1, 2);
		LOGGER.debug("div result -> {}", result);
	}
	
	@Test
	public void testRound() {
		Double d1 = 10.8725;
		double result = BigDecimalUtils.round(d1, 3);
		LOGGER.debug("round result -> {}", result);
	}
	
	@Test
	public void testAddRound() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.add(d1, d2, 3);
		LOGGER.debug("add round result -> {}", result);
	}
	
	@Test
	public void testSubRound() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.sub(d2, d1, 3);
		LOGGER.debug("sub round result -> {}", result);
	}
	
	@Test
	public void testMulRound() {
		Double d1 = 10.872;
		Double d2 = 38.987;
		double result = BigDecimalUtils.mul(d2, d1, 3);
		LOGGER.debug("mul round result -> {}", result);
	}
	
}
