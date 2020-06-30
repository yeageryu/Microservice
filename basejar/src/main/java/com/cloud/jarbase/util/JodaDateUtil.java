package com.cloud.jarbase.util;

import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author Jimmy
 * @title joda Date 工具类
 * @description
 * @usage
 * @copyright Copyright 2014 RENRENMEIJU Corporation. All rights reserved.
 * @company 上海众多美网络科技有限公司
 * @create 2015年1月19日 下午3:45:35
 */
public class JodaDateUtil {

	public final static String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";

	public final static String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	public final static String PATTERN_YYYY_MM_DD_HH_MM_SS_SSSS = "yyyy-MM-dd HH:mm:ss +SSSS";

	public final static String PATTERN_MM_DD_HH_MM = "MM-dd HH:mm";

	public final static String PATTERN_DD = "dd";

	public final static String PATTERN_YYYYMMDD = "yyyyMMdd";

	public static String formatDate(Date date, String pattern) {
		DateTime dateTime = new DateTime(date);
		return dateTime.toString(pattern);
	}

	public static DateTime parseDate(String date, String pattern) {
		try {
			DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
			DateTime dateTime = format.parseDateTime(date);
			return dateTime;
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}
		return null;
	}

	public static String getTimeStr(Date date) {
		return getTimeStr(date, JodaDateUtil.PATTERN_YYYY_MM_DD_HH_MM_SS);
	}

	public static String getTimeStr(Date date, String pattern) {
		String timeStr = "";
		Date now = new Date();
		double second = Math.ceil((now.getTime() - date.getTime()) / 1000.0);
		second = second < 1 ? 1 : second;
		if (second < 60) {
			timeStr += (int) second + "秒前";
		} else {
			double minute = Math.floor(second / 60.0);
			if (minute < 60) {
				timeStr += (int) minute + "分钟前";
			} else {
				double hour = Math.floor(minute / 60.0);
				if (hour < 24) {
					timeStr += (int) hour + "小时前";
				} else {
					timeStr += JodaDateUtil.formatDate(date, pattern);
				}
			}
		}
		return timeStr;
	}

	public static Boolean isBeforeNow(Date date, int plusDay) {
		DateTime dateTime = new DateTime(date).plusDays(plusDay);
		if (dateTime.isBeforeNow()) {
			return true;
		}
		return false;
	}
	public static Boolean isAfterNow(Date date, int plusDay) {
		DateTime dateTime = new DateTime(date).plusDays(plusDay);
		if (dateTime.isAfterNow()) {
			return true;
		}
		return false;
	}

	public static Boolean isActivityTime(String start, String end) {
		DateTime startTime = JodaDateUtil.parseDate(start, JodaDateUtil.PATTERN_YYYY_MM_DD);
		DateTime endTime = JodaDateUtil.parseDate(end, JodaDateUtil.PATTERN_YYYY_MM_DD);
		if (startTime.isBeforeNow() && endTime.isAfterNow()) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Boolean isActivityTime(String start, String end, String pattern) {
		DateTime startTime = JodaDateUtil.parseDate(start, pattern);
		DateTime endTime = JodaDateUtil.parseDate(end, pattern);
		if (startTime.isBeforeNow() && endTime.isAfterNow()) {
			return true;
		} else {
			return false;
		}
	}


	public static Boolean isConfines(Date date, Date rangeDate, int type) {
		DateTime dateTime = new DateTime(date);
		DateTime dateTimeStart = null;
		DateTime dateTimeEnd = null;
		if(type == 1){
			dateTimeStart = new DateTime(rangeDate).withTimeAtStartOfDay();
			dateTimeEnd = new DateTime(rangeDate).millisOfDay().withMaximumValue();
		}
		if(type == 2){
			dateTimeStart = new DateTime(rangeDate).withDayOfWeek(1).millisOfDay().withMinimumValue();
			dateTimeEnd = new DateTime(rangeDate).withDayOfWeek(7).millisOfDay().withMaximumValue();
		}
		if (type == 3) {
			dateTimeStart = new DateTime(rangeDate).dayOfMonth().withMinimumValue();
			dateTimeEnd = new DateTime(rangeDate).dayOfMonth().withMaximumValue();
		}
		Interval interval = new Interval(dateTimeStart, dateTimeEnd);
		return interval.contains(dateTime);
	}

//	public static void main(String[] args) {
//		DateTime start = new DateTime(2015, 5, 1, 0, 0, 0);
//		DateTime end = new DateTime(2015, 5, 1, 23, 59, 59);
//		Period period = new Period(start, end);
//		Interval interval = new Interval(start, end);
//		System.out.println(interval.contains(new DateTime(2015, 5, 2, 15, 5, 5)));
//		System.out.println("month：" + period.getMonths());
//		System.out.println("days：" + period.getDays());
//		System.out.println("hours：" + period.getHours());
//		System.out.println("minutes：" + period.getMinutes());
//		System.out.println("second：" + period.getSeconds());
//
//	}


}
