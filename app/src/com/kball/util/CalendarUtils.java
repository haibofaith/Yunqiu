package com.kball.util;

import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class CalendarUtils {
	public final static int COMPARETYPE_DAY = 0;
	public final static int COMPARETYPE_HOUR = 1;
	public final static int COMPARETYPE_MINUTE = 2;

	/*
	 * private final static int[] TIME_VALUES = { 24 * 60 * 60 * 1000, 60 * 60 *
	 * 1000, 60 * 1000 };
	 */

	/*
	 * public int compareDate(Calendar c1,Calendar c2,int type) { switch(type) {
	 * case COMPARETYPE_DAY: Calendar c = (Calendar)c1.clone();
	 * c.set(Calendar.HOUR_OF_DAY, 0); c.set(Calendar.MINUTE, 0);
	 * c.set(Calendar.SECOND, 0); c.set(Calendar.MILLISECOND, 0);
	 * 
	 * break; } return 0; }
	 */

	public final static int compareDate(Calendar c1, Calendar c2) {
		int y1 = c1.get(Calendar.YEAR);
		int y2 = c2.get(Calendar.YEAR);
		int m1 = c1.get(Calendar.MONTH);
		int m2 = c2.get(Calendar.MONTH);
		int d1 = c1.get(Calendar.DAY_OF_MONTH);
		int d2 = c2.get(Calendar.DAY_OF_MONTH);

		if (y1 == y2) {
			if (m1 == m2) {
				return d1 - d2;
			} else {
				return m1 - m2;
			}
		} else {
			return y1 - y2;
		}
	}

	public static int getDaysBetween(Calendar d1, Calendar d2) {
		if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
			Calendar swap = d1;
			d1 = d2;
			d2 = swap;
		}
		int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
		int y2 = d2.get(Calendar.YEAR);
		if (d1.get(Calendar.YEAR) != y2) {
			d1 = (Calendar) d1.clone();
			do {
				days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
				d1.add(Calendar.YEAR, 1);
			} while (d1.get(Calendar.YEAR) != y2);
		}
		return days;
	}

	public static int countDaysBetween(Calendar d1, Calendar d2) {
		return (int) ((d1.getTimeInMillis() - d2.getTimeInMillis()) / (1000 * 3600 * 24));
	}

	/**
	 * 该方法不做参数验证了，要求参数是：2010-1-1或2010-01-01这样的格式
	 */
	public static long getDaysBetween(String startDate, String endDate) {
		String startArr[] = startDate.split("-");
		String endArr[] = endDate.split("-");

		Calendar startCalendar = CalendarUtils.getCalendarInstance();
		Calendar endCalendar = CalendarUtils.getCalendarInstance();

		startCalendar.set(Integer.valueOf(startArr[0]), Integer.valueOf(startArr[1]), Integer.valueOf(startArr[2]));
		endCalendar.set(Integer.valueOf(endArr[0]), Integer.valueOf(endArr[1]), Integer.valueOf(endArr[2]));

		return getDaysBetween(startCalendar, endCalendar);
	}

	/**
	 * @Title: getDateLong
	 * @Description: 把YYYY-MM-dd转换为long型日期
	 * @param @param date
	 * @param @return
	 * @return long
	 */
	public static long getDateLong(String date) {
		String dateArr[] = date.split("-");
		Calendar calendar = getCalendarInstance();
		calendar.set(Integer.valueOf(dateArr[0]), Integer.valueOf(dateArr[1]) - 1, Integer.valueOf(dateArr[2]));
		return calendar.getTimeInMillis();
	}

	public static Calendar getDateCalendar(String date) {
		String dateArr[] = date.split("-");
		Calendar calendar = getCalendarInstance();
		calendar.set(Integer.valueOf(dateArr[0]), Integer.valueOf(dateArr[1]) - 1, Integer.valueOf(dateArr[2]));
		return calendar;
	}

	/**
	 * 
	 * @Title: getCalendarInstance
	 * @Description: 返回日历对象(默认采用东八区区时)
	 * @param @return
	 * @return Calendar
	 * @throws
	 */
	public static final Calendar getCalendarInstance() {
		return Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
	}

	/**
	 * 格式化日期为字符串 yyyy-MM-dd格式
	 * 
	 * @param calendar
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String formatToString(Calendar calendar) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(calendar.getTime());
		// return calendar.get(Calendar.YEAR) + "-" +
		// (calendar.get(Calendar.MONTH)+1) + "-" +
		// calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @Title: getDateMonthStart
	 * @Description: 获取当月起始日期
	 * @param @param calendar
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getDateMonthStart(Calendar calendar) {
		int day = calendar.get(Calendar.DAY_OF_MONTH) - 1;
		calendar.add(Calendar.DAY_OF_MONTH, -day);
		long l = -1l;
		l = calendar.getTimeInMillis();
		return l;
	}

	/**
	 * @Title: getDateMonthEnd
	 * @Description: 获取当月结束日期
	 * @param @param calendar
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getDateMonthEnd(Calendar calendar) {
		int day = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		long l = -1l;
		l = calendar.getTimeInMillis();
		return l;
	}

	/**
	 * @Title: getDateWeekStart
	 * @Description: 获取本周其实日期
	 * @param @param calendar
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getDateWeekStart(Calendar calendar) {
		int day = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		calendar.add(Calendar.DAY_OF_WEEK, -day);
		long l = -1l;
		l = calendar.getTimeInMillis();
		return l;
	}

	/**
	 * @Title: getDateWeekEnd
	 * @Description: 获取本周结束日期
	 * @param @param calendar
	 * @param @return
	 * @return long
	 * @throws
	 */
	public static long getDateWeekEnd(Calendar calendar) {
		int day = 7 - calendar.get(Calendar.DAY_OF_WEEK);
		calendar.add(Calendar.DAY_OF_WEEK, day);
		long l = -1l;
		l = calendar.getTimeInMillis();
		return l;
	}
}
