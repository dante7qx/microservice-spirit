package com.spirit.project.commom.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spirit.project.commom.util.DateUtils.TimeFormat;

/**
 * 日期工具类测试类
 * 
 * @author dante
 *
 */
public class DateUtilsTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DateUtilsTest.class);
	
	@Test
	public void testGetCurrentDate() {
		String date = DateUtils.getCurrentDate();
		Assert.assertNotNull(date);
		LOGGER.debug("date: {}",date);
	}
	
	@Test
	public void testGetCurrentDatetime() {
		String date = DateUtils.getCurrentDatetime();
		Assert.assertNotNull(date);
		LOGGER.debug("date: {}",date);
	}
	
	@Test
	public void testGetCurrentDateWithMilliSecond() {
		String date = DateUtils.getCurrentDateWithMilliSecond();
		Assert.assertNotNull(date);
		LOGGER.debug("date: {}",date);
	}
	
	@Test
	public void testFormatDateTime() {
		Date date = DateUtils.currentDate();
		String dateStr = DateUtils.formatDate(date);
		String dateTimeStr = DateUtils.formatDateTime(date);
		String yearMonth = DateUtils.formatDate(date, TimeFormat.SHORT_DATE_PATTERN_YEAR_MONTH);
		Assert.assertNotNull(dateStr);
		Assert.assertNotNull(dateTimeStr);
		LOGGER.debug("\n dateStr: {} \n dateTimeStr: {} \n yearMonth: {}", dateStr, dateTimeStr, yearMonth);
	}
	
	@Test
	public void testParseDate() {
		String str = "2016-03-06";
		LOGGER.debug("" + DateUtils.parseDate(str));
		
		String str1 = "20160306";
		LOGGER.debug("" + DateUtils.parseDate(str1, TimeFormat.SHORT_DATE_PATTERN_NONE));
		
		String str2 = "2016-03-06 22:23:34";
		LOGGER.debug("" + DateUtils.parseDateTime(str2));
		
		String str3 = "2016-03-06 22:23:34";
		LOGGER.debug("" + DateUtils.parseDateTime(str3, TimeFormat.LONG_DATE_PATTERN_WITH_MILSEC_LINE));
	}
	
}
