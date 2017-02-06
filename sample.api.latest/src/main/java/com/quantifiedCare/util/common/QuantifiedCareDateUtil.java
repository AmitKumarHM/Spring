/**
 * 
 * Copyright (C) 2013 Lufthansa-confidential- All rights reserved.
 * 
 * This file is part of Lufthansa Krmaaockpit project.
 * 
 * Revision: 1.0, last modified: 2014
 * 
 */
package com.quantifiedCare.util.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.annotation.Nonnull;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.quantifiedCare.util.constant.QuantifiedCareConstant;

/**
 * This is the common date utility class . This class is a suite of utility
 * method surrounding the use of the java.util.Calendar and java.util.Date
 * object.
 */
/**
 * @author arvind
 *
 */
public final class QuantifiedCareDateUtil {

	/** The Full qualified class name constant. */
	private static final String FQCN = QuantifiedCareDateUtil.class.getName();

	/** The Constant W3CDATETIME_MASKS. */
	private static final String W3CDATETIME_MASKS = "yyyy-MM-dd'T'HH:mmz";

	/** The Constant UTC. */
	private static final TimeZone UTC = QuantifiedCareDateUtil.getTimeZone("UTC");

	/** W3CDATETIME_MASKS_FORMATTER. */
	private static final SimpleDateFormat W3CDATETIME_MASKS_FORMATTER = new SimpleDateFormat(
			W3CDATETIME_MASKS);

	/**
	 * Instantiates a new KrmaaDateUtil.
	 */
	private QuantifiedCareDateUtil() {
		// throw new Exception("KrmaaDateUtil");
	}

	/**
	 * Gets the checks if is o8601 date format.
	 * 
	 * @return the checks if is o8601 date format
	 */
	private static DateFormat getISO8601DateFormat() {
		return W3CDATETIME_MASKS_FORMATTER;
	}

	/**
	 * Gets the specific date format.
	 * 
	 * @param format
	 *            the format
	 * @return the specific date format
	 */
	public static DateFormat getSpecificDateFormat(final String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat;
	}

	/**
	 * Gets the time zone.
	 * 
	 * @param tzId
	 *            the tz id
	 * @return the time zone
	 */
	public static TimeZone getTimeZone(final String tzId) {
		if (tzId == null) {
			throw new IllegalArgumentException("getTimeZone");
		}
		TimeZone tz = TimeZone.getTimeZone(tzId);
		if (!tz.getID().equals(tzId)) {
			throw new IllegalArgumentException("getTimeZone");
		}
		return tz;
	}

	/**
	 * Parses the date utc.
	 * 
	 * @param str
	 *            the str
	 * @return the date
	 * @throws ParseException
	 */
	public static Date parseDateUTC(final String str) throws ParseException {
		try {
			return QuantifiedCareDateUtil.getISO8601DateFormat().parse(str);
		} catch (ParseException e) {
			throw new ParseException("parseDateUTC", 0);
		}
	}

	/**
	 * Parses the date.
	 * 
	 * @param str
	 *            the str
	 * @param format
	 *            the format
	 * @return the date
	 * @throws ParseException
	 */
	public static Date parseDate(final String str, final String format)
			throws ParseException {
		try {
			return QuantifiedCareDateUtil.getSpecificDateFormat(format).parse(str);
		} catch (ParseException e) {
			throw new ParseException("parseDate", 0);
		}
	}

	/**
	 * Format date utc.
	 * 
	 * @param date
	 *            the date
	 * @return the string
	 */
	public static String formatDateUTC(final Date date) {
		return date != null ? QuantifiedCareDateUtil.getISO8601DateFormat().format(date)
				: StringUtils.EMPTY;
	}

	/**
	 * Format date custom.
	 * 
	 * @param d
	 *            the d
	 * @param format
	 *            the format
	 * @return the string
	 */
	public static String formatDateCustom(final Date d, final String format) {
		return d != null ? QuantifiedCareDateUtil.getSpecificDateFormat(format)
				.format(d) : QuantifiedCareConstant.NULL_STRING;
	}

	/**
	 * Format date utc.
	 * 
	 * @param cal
	 *            the c
	 * @return the string
	 */
	public static String formatDateUTC(final Calendar cal) {
		return cal != null ? QuantifiedCareDateUtil.formatDateUTC(cal.getTime())
				: QuantifiedCareConstant.NULL_STRING;
	}

	/**
	 * Create a Calendar instance using the specified date and Time zone.
	 * 
	 * @param dateString
	 *            the date string
	 * @param tz
	 *            : TimeZone
	 * @return appropriate Calendar object
	 * @throws ParseException
	 */
	public static Calendar getCalendar(final String dateString,
			final TimeZone tz) throws ParseException {
		Calendar calDate = null;
		if (StringUtils.isNotEmpty(dateString)) {
			Date date = QuantifiedCareDateUtil.parseDate(dateString,
					QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
			calDate = Calendar.getInstance();
			calDate.setTime(date);
		}
		return calDate;
	}

	/**
	 * Create a Calendar instance using the specified date in specified format.
	 * 
	 * @param dateString
	 *            the date string
	 * @param dateFormat
	 *            the date format
	 * @return appropriate Calendar object
	 * @throws ParseException
	 */
	public static Calendar getCalendar(final String dateString,
			String dateFormat) throws ParseException {
		Calendar calDate = null;
		if (StringUtils.isNotEmpty(dateString)) {
			Date date = QuantifiedCareDateUtil.parseDate(dateString, dateFormat);
			calDate = Calendar.getInstance();
			calDate.setTime(date);
		}
		return calDate;
	}

	/**
	 * Return the UTC date and time in W3C format down to second
	 * (yyyy-MM-ddTHH:mm:ssZ). i.e.: 1997-07-16T19:20:30Z
	 * 
	 * @param date
	 *            the date
	 * @return the formatted time string.
	 */
	public static String convertDateToString(final Date date) {
		W3CDATETIME_MASKS_FORMATTER.setTimeZone(UTC);
		return W3CDATETIME_MASKS_FORMATTER.format(date);
	}

	/**
	 * Return the UTC date and time in W3C format down to second
	 * (yyyy-MM-ddTHH:mm:ssZ). i.e.: 1997-07-16T19:20:30Z The input date is a
	 * long (Unix Time Stamp)
	 * 
	 * @param timeStamp
	 *            the time stamp
	 * @return the formatted time string.
	 */
	public static String convertDateToString(final long timeStamp) {
		W3CDATETIME_MASKS_FORMATTER.setTimeZone(UTC);
		return W3CDATETIME_MASKS_FORMATTER.format(new Date(timeStamp));
	}

	/**
	 * Method to convert date string to calendar object.
	 * 
	 * @param dateString
	 *            dateString
	 * @return calendarObj calendarObj
	 * @throws ParseException
	 */
	public static Calendar getCalendar(final String dateString)
			throws ParseException {
		Calendar calDate = null;
		if (StringUtils.isNotEmpty(dateString)) {
			calDate = QuantifiedCareDateUtil.getCalendar(dateString, UTC);
		}
		return calDate;
	}

	/**
	 * Converts long value to time string.
	 * 
	 * @param time
	 *            the time
	 * @return the string
	 */
	public static String convertTime(long time) {
		Date date = new Date(time);
		DateFormat format = QuantifiedCareDateUtil
				.getSpecificDateFormat(QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
		return format.format(date);
	}

	/**
	 * This method gives the latest date among the list of dates.
	 * 
	 * @param dates
	 *            date list
	 * @return latestDate
	 * @throws ParseException
	 *             the parse exception
	 */
	public static String getLatestDate(@Nonnull final List<String> dates)
			throws ParseException {
		int index = 0;
		int lastIndex = 0;
		Calendar nearestDate = null;
		Calendar firstElmnt = null;
		Calendar lastElmnt = null;
		if (dates.size() == 1) {
			nearestDate = QuantifiedCareDateUtil.getCalendar(dates.get(index));
		} else {
			lastIndex = dates.size() - 1;
			firstElmnt = QuantifiedCareDateUtil.getCalendar(dates.get(index));
			lastElmnt = QuantifiedCareDateUtil.getCalendar(dates.get(lastIndex));
			if (firstElmnt.before(lastElmnt)) {
				nearestDate = lastElmnt;
			} else {
				nearestDate = firstElmnt;
			}
		}
		return DateFormatUtils.format(nearestDate,
				QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
	}

	/**
	 * Format the calendar date as per the provided pattern.
	 * 
	 * @param calendar
	 *            the calendar
	 * @param simpleDateFormat
	 *            the simple date format
	 * @return the string
	 */
	public static String format(final Calendar calendar,
			final DateFormat simpleDateFormat) {
		return calendar != null ? simpleDateFormat.format(calendar.getTime())
				: null;
	}

	/**
	 * Format the calendar date as per the provided pattern.
	 * 
	 * @param date
	 *            the date
	 * @param simpleDateFormat
	 *            the simple date format
	 * @return the string
	 */
	public static String format(final Date date,
			final DateFormat simpleDateFormat) {
		return date != null ? simpleDateFormat.format(date) : null;
	}

	/**
	 * Format the calendar date as per the provided pattern.
	 * 
	 * @param calendar
	 *            the calendar
	 * @param formatPattern
	 *            the format pattern
	 * @return the string
	 */
	public static String format(final Calendar calendar,
			final String formatPattern) {
		String dateString = null;
		if (calendar != null) {
			Date date = calendar.getTime();
			DateFormat simpleDateFormat = QuantifiedCareDateUtil
					.getSpecificDateFormat(formatPattern);
			dateString = simpleDateFormat.format(date);
		}
		return dateString;
	}

	/**
	 * Format the date as per the provided pattern.
	 * 
	 * @param date
	 *            the date
	 * @param formatPattern
	 *            the format pattern
	 * @return the string
	 */
	public static String format(final Date date, final String formatPattern) {
		String dateString = null;
		if (date != null) {
			DateFormat simpleDateFormat = QuantifiedCareDateUtil
					.getSpecificDateFormat(formatPattern);
			dateString = simpleDateFormat.format(date);
		}
		return dateString;
	}

	/**
	 * This method will convert calendar instance into SQL timestamp.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return the sql timestamp by calendar
	 */
	public static Timestamp getTimestampByCalendar(final Calendar calendar) {
		Timestamp timestamp = null;
		if (calendar != null) {
			timestamp = new Timestamp(DateUtils
					.round(calendar, Calendar.SECOND).getTimeInMillis());
		}
		return timestamp;
	}

	/**
	 * Gets the diff from current date in days.
	 * 
	 * @param dateToCompare
	 *            the date to compare
	 * @return the diff from current date in days
	 */
	public static Integer getDiffFromCurrentDateInDays(Date dateToCompare) {
		Calendar currentTime = Calendar.getInstance();
		QuantifiedCareDateUtil.trim(currentTime);
		Date now = currentTime.getTime();

		Integer diffDays = -1;
		if (dateToCompare != null) {

			// in milliseconds
			long diff = dateToCompare.getTime() - now.getTime();
			diffDays = (int) (diff / (24 * 60 * 60 * 1000));
		}

		return diffDays;
	}

	
	/**
	 * This API will return the day of week for the given date. In the Krmaa
	 * system Monday has 1 value for day of week.
	 * 
	 * @param date
	 *            the date
	 * @return the day of week
	 */
	public static int getDayOfWeek(Calendar date) {
		int dayOfWeek = date.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 0) {
			dayOfWeek = 7;
		}
		return dayOfWeek;
	}

	/**
	 * This API will return the day of week for the given date. In the Krmaa
	 * system Monday has 1 value for day of week.
	 * 
	 * @param date
	 *            the date
	 * @return the day of week
	 */
	public static int getDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return QuantifiedCareDateUtil.getDayOfWeek(cal);
	}

	/**
	 * This method will trim calendar to day by setting hour,min and sec to
	 * zero.
	 * 
	 * @param cal
	 *            the calendar object
	 */
	public static void trim(final Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
	}

	/**
	 * returns true if calendar1 and calendar2 are equal.
	 * 
	 * @param calendar1
	 *            the calendar1
	 * @param calendar2
	 *            the calendar2
	 * @return less than 0 if calendar1 date is before calendar2 date, 0 if both
	 *         are equal and greater than 0 if first is after later.
	 * @throws ParseException
	 *             the parse exception
	 */
	public static int compareDates(Calendar calendar1, Calendar calendar2)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat(
				QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
		Date date1 = sdf.parse(QuantifiedCareDateUtil.format(calendar1.getTime(),
				QuantifiedCareConstant.SIMPLE_DATE_FORMAT));
		Date date2 = sdf.parse(QuantifiedCareDateUtil.format(calendar2.getTime(),
				QuantifiedCareConstant.SIMPLE_DATE_FORMAT));
		return date1.compareTo(date2);
	}

	/**
	 * This method returns the String value of calendar.
	 * 
	 * @param calendar
	 *            the calendar
	 * @return the time
	 */
	public static String convertCalenderToString(Calendar calendar) {
		String time = null;
		if (null != calendar) {
			time = QuantifiedCareDateUtil.format(calendar.getTime(),
					QuantifiedCareConstant.SIMPLE_DATE_FORMAT);
		}
		return time;
	}

	/**
	 * This method returns the String value of calendar.
	 * 
	 * @param calendar
	 *            the calendar
	 * @param format
	 *            the format
	 * @return the time
	 */
	public static String convertCalenderToString(Calendar calendar,
			String format) {
		String time = null;
		if (null != calendar) {
			time = QuantifiedCareDateUtil.format(calendar.getTime(), format);
		}
		return time;
	}

	/**
	 * Gets the calendar from epoch number.
	 * 
	 * @param epochNumber
	 *            the epoch number
	 * @param dateFormat
	 *            the date format
	 * @return the calendar from epoch number
	 * @throws ParseException
	 */
	public static Calendar getCalendarFromEpochNumber(long epochNumber,
			String dateFormat) throws ParseException {
		String date = new java.text.SimpleDateFormat(dateFormat)
				.format(new java.util.Date(epochNumber));
		return QuantifiedCareDateUtil.getCalendar(date, dateFormat);
	}

	/**
	 * This method gets the past dates based on the no. of days passed.
	 * 
	 * @param before
	 *            the before
	 * @return the previous date
	 */
	public static Calendar getPreviousDate(String before) {

		int beforeInt = 0 - Integer.valueOf(before);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, beforeInt);
		return cal;
	}
}
