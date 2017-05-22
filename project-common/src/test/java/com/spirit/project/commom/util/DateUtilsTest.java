package com.spirit.project.commom.util;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.spirit.project.commom.util.DateUtils.TimeFormat;

public class DateUtilsTest {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DateUtilsTest.class);
	
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
	
}
