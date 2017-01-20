package com.freud.zkadmin.framework.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTERN_DATE = "yyyy-MM-dd";

	public static Timestamp string2Timestamp(final String strDateTime,
			String pattern) {
		if (strDateTime == null || strDateTime.equals("")) {
			throw new java.lang.IllegalArgumentException(
					"Date Time Null Illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}

		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = sdf.parse(strDateTime);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
		return new Timestamp(date.getTime());
	}
	
	public static boolean compareDate(final Date firstDate,
			final Date secondDate) {
		if (firstDate == null || secondDate == null) {
			throw new java.lang.RuntimeException();
		}

		final String strFirstDate = date2String(firstDate, PATTERN_DATE);
		final String strSecondDate = date2String(secondDate, PATTERN_DATE);
		if (strFirstDate.equals(strSecondDate)) {
			return true;
		}
		return false;
	}

	public static Timestamp currentTimestamp() {
		return new Timestamp(new Date().getTime());
	}

	public static String date2String(final Date date, String pattern) {
		if (date == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static Date getFirstDayOfMonth(final Calendar c) {
		final int year = c.get(Calendar.YEAR);
		final int month = c.get(Calendar.MONTH);
		final int day = 1;
		c.set(year, month, day, 0, 0, 0);
		return c.getTime();
	}

	public static Date getLastDayOfMonth(final Calendar c) {
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		final int day = 1;
		if (month > 11) {
			month = 0;
			year = year + 1;
		}
		c.set(year, month, day - 1, 0, 0, 0);
		return c.getTime();
	}

	public static Date string2Date(final String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		final SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (final ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}

	public static String timestamp2String(final Timestamp timestamp,
			String pattern) {
		if (timestamp == null) {
			throw new java.lang.IllegalArgumentException(
					"timestamp null illegal");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = PATTERN_STANDARD;
		}
		return new SimpleDateFormat(pattern).format(new Date(timestamp
				.getTime()));
	}
}