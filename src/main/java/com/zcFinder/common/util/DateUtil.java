package com.zcFinder.common.util;

import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangc on 2017/8/29.
 */
public class DateUtil {
	private static String datePattern = "yyyy-MM-dd";
	private static String timePattern = "HH:mm:ss";
	private static SimpleDateFormat dateFormat;
	private static SimpleDateFormat datetimeFormat;
	private static Date todayDefault = new Date();

	static {
		dateFormat = new SimpleDateFormat(datePattern);
		datetimeFormat = new SimpleDateFormat(datePattern + " " + timePattern);
	}

	public DateUtil() {
	}


	public static Date toDate(String s) {
		s = StringUtils.trim(s);
		if (s.length() < 1) {
			return null;
		} else {
			try {
				return s.length() <= 10 ? dateFormat.parse(s) : toDate(Timestamp.valueOf(s));
			} catch (Exception var2) {
				return null;
			}
		}
	}


	public static Map<String, String> getFirstDayAndLastDayOfMonth(String date) {
		Map firstAndLastDay = new HashMap<String, String>();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar caleFirst = null;
		Calendar caleLast = null;
		// 获取当月第一天和最后一天
		String firstDay, lastDay;
		// 获取当前月的第一天
		caleFirst = Calendar.getInstance();
		caleFirst.setTime(toDate(date));
		caleFirst.add(Calendar.MONTH, 0);
		caleFirst.set(Calendar.DAY_OF_MONTH, 1);
		firstDay = df.format(caleFirst.getTime());

		// 获取当前月的最后一天
		caleLast = Calendar.getInstance();
		caleLast.setTime(toDate(date));
		caleLast.add(Calendar.MONTH, 1);
		caleLast.set(Calendar.DAY_OF_MONTH, 0);
		lastDay = df.format(caleLast.getTime());


		firstAndLastDay.put("firstDay", firstDay);
		firstAndLastDay.put("lastDay", lastDay);
		return firstAndLastDay;
	}


	/**
	 * 日期增减天数(String)
	 *
	 * @param qTime
	 * @param days
	 * @return
	 */
	public static String datePlusOrMinusDay(String qTime, int days) {
		DateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
		Calendar beforeDate = Calendar.getInstance();
		try {
			beforeDate.setTime(dfDate.parse(qTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		beforeDate.add(Calendar.DATE, days);
		return dfDate.format(beforeDate.getTime());
	}

	public static String getCurrDateTime() {
		return toDatetimeString(new Date());
	}

	public static String toDatetimeString(Date date) {
		return date == null ? null : datetimeFormat.format(date);
	}

	/**
	 * 得到当前天
	 *
	 * @return
	 */
	public static String getToday() {
		SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
		String today = sdf.format(new Date());
		return today;
	}


	public static Date toDate(Timestamp timestamp) {
		return timestamp == null ? null : new Date(timestamp.getTime());
	}

	/**
	 * format   date
	 *
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null) return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String datestr = sdf.format(date);
		return datestr;
	}

	/**
	 * format   date
	 *
	 * @return
	 */
	public static Date strToDate(String datestr, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(datestr);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 判断日期是否为当前月
	 * 传入日期格式为yyyy-MM
	 *
	 * @return
	 */
	public static boolean isCurrentMonth(String dateStr) {
		boolean isCurrent = false;
		String today = formatDate(todayDefault, "yyyy-MM");
		if (today.equals(dateStr)) {
			isCurrent = true;
		}
		return isCurrent;
	}

	/**
	 * 获得当前月
	 *
	 * @return
	 */
	public static String getCurrentMonth() {
		String currentMonth = formatDate(todayDefault, "yyyy-MM");
		return currentMonth;
	}

	/**
	 * 得到指定月份的第一天
	 *
	 * @return
	 */
	public static String getMonthFirstDay(String monthStr) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(monthStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMinimum(Calendar.DAY_OF_MONTH));
		return getFormatDate(calendar.getTime(), datePattern);
	}

	public static String getFormatDate(Date currDate, String format) {
		if (currDate == null) {
			return "";
		}
		SimpleDateFormat dtFormatdB = null;
		try {
			dtFormatdB = new SimpleDateFormat(format);
			return dtFormatdB.format(currDate);
		} catch (Exception e) {
			dtFormatdB = new SimpleDateFormat(datePattern);
			try {
				return dtFormatdB.format(currDate);
			} catch (Exception ex) {
			}
		}
		return null;
	}


	/**
	 * 得到指定月的最后一天
	 * 传入日期格式为yyyy-MM
	 *
	 * @return
	 */
	public static String getMonthLastDay(String monthStr) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date date = null;
		try {
			date = sdf.parse(monthStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH));
		return getFormatDate(calendar.getTime(), datePattern);
	}

	/**
	 * 判断日期是否为当前年
	 *
	 * @return
	 */
	public static boolean isCurrentYear(String dateStr) {
		boolean isCurrent = false;
		String today = formatDate(todayDefault, "yyyy");
		if (today.equals(dateStr)) {
			isCurrent = true;
		}
		return isCurrent;
	}
}

