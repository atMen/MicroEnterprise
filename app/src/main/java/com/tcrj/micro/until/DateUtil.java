package com.tcrj.micro.until;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期时间管理类.
 * <p>
 * Title: CicroDate
 * </p>
 * <p>
 * Description: 日期时间管理
 * </p>
 * <p>
 * Copyright: Copyright (c) 2002
 * </p>
 * <p>
 * Company: Cicro
 * </p>
 * 
 * @author kongxx
 * @author Sunyi
 * @version 1.3 *
 */

public class DateUtil {
	private static final String DATE_PATTERN = "yyyy-MM-dd";
	private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 获取当前系统时间. 默认模板格式yyyy-MM-dd hh:mm:ss.
	 * 
	 * @return 当前系统时间
	 */
	public static String getCurrentDateTime() {
		return getCurrentDateTime(DATETIME_PATTERN);
	}

	/**
	 * 获取当前系统同期。
	 * 
	 * @return 当前系统日期
	 * @author zhenggz 2003-11-09
	 */
	public static String getCurrentDate() {
		return getCurrentDateTime(DATE_PATTERN);
	}

	/**
	 * 获取当前系统时间.
	 * 
	 * @param strPattern
	 *            时间模板
	 * @return 当前系统时间
	 */
	public static String getCurrentDateTime(String pattern) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(cal.getTime());
	}

	/**
	 * 把字串转化成为Date对象，时间字串格式为2000-01-01 00:00:00
	 * 
	 * @param dateString
	 *            被转化的时间字串，以 yyyy-MM-dd HH:mm:ss 的格式
	 * @throws ParseException
	 * */
	public static Date getDate(String dateStr) throws ParseException {
		return getDate(dateStr, DATETIME_PATTERN);
	}

	/**
	 * 把字串转化成为Date对象，时间字串格式需要设定
	 * 
	 * @param dateString
	 *            被转化的时间字串
	 * @param pattern
	 *            时间字串的日期格式，如yyyy-MM-dd
	 * @throws ParseException
	 * */
	public static Date getDate(String dateStr, String pattern)
			throws ParseException {
		Date date = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		date = dateFormat.parse(dateStr);

		return date;
	}

	/**
	 * 取得日期字串
	 * 
	 * @param date
	 *            Date对象
	 * @return 日期字串，格式如：2003-12-02
	 * */
	public static String getDateString(Date date) {
		return getString(date, DATE_PATTERN);
	}

	/**
	 * 取得日期时间字串
	 * 
	 * @param date
	 *            Date对象
	 * @return 日期时间字串，格式如：2003-12-02 13:10:00
	 * */
	public static String getDateTimeString(Date date) {
		return getString(date, DATETIME_PATTERN);
	}

	/**
	 * 日期比较大小
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws Exception
	 */
	public static boolean getDateCompare(String start, String end)
			throws Exception {
		boolean flag = false;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d1 = sdf.parse(start);
		Date d2 = sdf.parse(end);
		if (d1.getTime() > d2.getTime()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}

	/**
	 * 按照指定格式取得时间字串
	 * 
	 * @param date
	 *            Date对象
	 * @param pattern
	 *            时间字串的日期格式，如yyyy-MM-dd
	 * @return 日期时间字串，格式如：2003-12-02 13:10:00
	 * */
	public static String getString(Date date, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);

		return dateFormat.format(date);
	}

	/**
	 * 格式化日期字串
	 * 
	 * @param dateStr
	 * @return 格式化后的字串，格式如：2003-12-02
	 * */
	public static String formatToDateString(String dateStr) {
		try {
			return formatToString(dateStr, DATE_PATTERN);
		} catch (Exception e) {
			e.printStackTrace();
			return dateStr;
		}
	}

	/**
	 * 格式化日期时间字串
	 * 
	 * @param dateTimeStr
	 * @return String　格式化后的字串，格式如：2003-12-02 12:12:10
	 * */
	public static String formatToDateTimeString(String dateTimeStr)
			throws ParseException {
		return formatToString(dateTimeStr, DATETIME_PATTERN);
	}

	/**
	 * 格式化日期时间字串为指定的格式字串
	 * 
	 * @param String
	 *            时间字串
	 * @param String
	 *            时间字串的日期格式，如yyyy-MM-dd
	 * @return String　格式化后的字串，格式如：2003-12-02 12:12:10
	 * */
	public static String formatToString(String dateStr, String pattern)
			throws ParseException {
		dateStr = format(dateStr);
		Date date = null;
		if (checkDateString(dateStr)) {
			date = getDate(dateStr, DATE_PATTERN);
			return getString(date, pattern);
		} else if (checkDateTimeString(dateStr)) {
			date = getDate(dateStr);
			return getString(date, pattern);
		} else {
			throw new ParseException("日期格式不正确", 1);
		}
	}

	/**
	 * 检查日期字串的格式
	 * 
	 * @param String
	 *            时间字串 YYYY-MM-DD
	 * @return boolean　true or false
	 * */
	public static boolean checkDateString(String dateStr) {
		Pattern pattern = Pattern.compile("\\d{2,4}-\\d{1,2}-\\d{1,2}");
		Matcher matcher = pattern.matcher(dateStr);

		return matcher.matches();
	}

	/**
	 * 检查日期时间字串的格式
	 * 
	 * @param String
	 *            时间字串 YYYY-MM-DD hh:mm:ss
	 * @return boolean　true or false
	 * */
	public static boolean checkDateTimeString(String dateTimeStr) {
		Pattern pattern = Pattern
				.compile("\\d{2,4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}");
		Matcher matcher = pattern.matcher(dateTimeStr);

		return matcher.matches();
	}

	/**
	 * 规范化时间字串
	 * 
	 * @param String
	 *            时间字串
	 * @return String　格式化后的字串，格式如：2003-12-02 12:12:10
	 * */
	public static String format(String dateStr) {
		Pattern pattern = Pattern
				.compile("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}.*");
		Matcher matcher = pattern.matcher(dateStr);
		if (matcher.matches()) {
			dateStr = dateStr.substring(0, 19);
		} else {
			pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}.*");
			matcher = pattern.matcher(dateStr);
			if (matcher.matches()) {
				dateStr = dateStr.substring(0, 10);
			}
		}

		return dateStr;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return int 返回这日期的年份　格式如：2010
	 */
	public static int getYear(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return　int 返回这日期的月份　格式如：11
	 */
	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int month = calendar.get(Calendar.MONTH);
		return month;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return　int 返回这日期在当月的第几天　格式如：15
	 */
	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return int　返回这日期在第几周　格式如：52
	 */
	public static int getDayOfWek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		return day;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return int　返回这日期的小时　格式如：23
	 */
	public static int getHour(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return int　返回这日期的分钟　格式如：59
	 */
	public static int getMinute(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int minute = calendar.get(Calendar.MINUTE);
		return minute;
	}

	/**
	 * History 2004-08-09 Added by xiecs 增加了根据Date对象获得年，月，日等信息的方法
	 * 获得某天的开始时间和结束时间，获得一周的开始时间和结束时间的方法
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return　int 返回这日期的秒钟　格式如：40
	 */
	public static int getSecond(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int second = calendar.get(Calendar.SECOND);
		return second;
	}

	/**
	 * 获得某天的开始时间的Date对象，一般用来作比较用
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return Date
	 */
	public static Date getStartOfDay(Date date) {
		Date startDate = null;
		try {
			startDate = DateUtil.getDate(
					DateUtil.getString(date, "yyyy-MM-dd"), "yyyy-MM-dd");
		} catch (Exception e) {
			return null;
		}
		return startDate;
	}

	/**
	 * 获得某天的结束时间的Date对象，一般用来作比较用
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return Date
	 */
	public static Date getEndOfDay(Date date) {
		Calendar calendar = Calendar.getInstance();
		Date endDate = null;
		try {
			calendar.set(DateUtil.getYear(date), DateUtil.getMonth(date),
					DateUtil.getDayOfMonth(date), 23, 59, 59);
			endDate = calendar.getTime();
		} catch (Exception e) {
			return null;
		}
		return endDate;
	}

	/**
	 * 获得某天所在的星期的第一天（星期一）的开始时间（0时0分0秒）Date对象，一般用来作比较用
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return Date
	 */
	public static Date getStartOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int n = 0;
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.MONDAY: {
			n = 0;
			break;
		}
		case Calendar.TUESDAY: {
			n = 1;
			break;
		}
		case Calendar.WEDNESDAY: {
			n = 2;
			break;
		}
		case Calendar.THURSDAY: {
			n = 3;
			break;
		}
		case Calendar.FRIDAY: {
			n = 4;
			break;
		}
		case Calendar.SATURDAY: {
			n = 5;
			break;
		}
		case Calendar.SUNDAY: {
			n = 6;
			break;
		}
		}
		Date monday = new Date(date.getTime() - 24 * 60 * 60 * 1000 * n);
		Date startDate = getStartOfDay(monday);
		return startDate;
	}

	/**
	 * 获得某天所在的星期的最后一天（星期天）的结束时间（23时59分59秒）Date对象，一般用来作比较用
	 * 
	 * @param Date
	 *            date 时间对象
	 * @return Date
	 */
	public static Date getEndOfWeek(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int n = 0;
		int day = calendar.get(Calendar.DAY_OF_WEEK);
		switch (day) {
		case Calendar.MONDAY: {
			n = 6;
			break;
		}
		case Calendar.TUESDAY: {
			n = 5;
			break;
		}
		case Calendar.WEDNESDAY: {
			n = 4;
			break;
		}
		case Calendar.THURSDAY: {
			n = 3;
			break;
		}
		case Calendar.FRIDAY: {
			n = 2;
			break;
		}
		case Calendar.SATURDAY: {
			n = 1;
			break;
		}
		case Calendar.SUNDAY: {
			n = 0;
			break;
		}
		}
		Date sunday = new Date(date.getTime() + 24 * 60 * 60 * 1000 * n);
		Date startDate = getEndOfDay(sunday);
		return startDate;
	}

	/**
	 * 判断两个日期之间相差的天数
	 * 
	 * @param String
	 *            day1 时间1 2010-01-03
	 * @param String
	 *            day1 时间2 2010-01-05
	 * @return long 相差天数
	 */
	public static long daysOf2Day(String day1, String day2) {
		try {
			day1 += " 00:00:00";
			day2 += " 00:00:00";
			long secs = secsOf2Day(day1, day2);
			return secs / (24 * 60 * 60);
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 判断两个时间之间相差的天数
	 * 
	 * @param String
	 *            day1 时间1 2010-01-03 00:00:00
	 * @param String
	 *            day1 时间2 2010-01-05 10:25:44
	 * @return long 相差天数
	 */
	public static long secsOf2Day(String day1, String day2) {
		try {
			Date date1 = getDate(day1);
			Date date2 = getDate(day2);
			long secs = Math.abs(date1.getTime() - date2.getTime()) / 1000;
			return secs;
		} catch (Exception e) {
			return -1;
		}
	}

	/**
	 * 获得到当前时间为止的天数,不足一天按一天计.
	 * 
	 * @param strDateTime
	 *            'yyyy-mm-dd hh:mm:ss'
	 * @return 天数
	 */
	public static String getDaysToNow(String strDateTime) {
		try {
			StringTokenizer strToken = new StringTokenizer(strDateTime, " ");
			StringTokenizer strTokenDate = new StringTokenizer(
					strToken.nextToken(), "-");
			StringTokenizer strTokenTime = new StringTokenizer(
					strToken.nextToken(), ":");
			int intYear = Integer.parseInt(strTokenDate.nextToken());
			int intMonth = Integer.parseInt(strTokenDate.nextToken()) - 1;
			int intDay = Integer.parseInt(strTokenDate.nextToken());
			int intHour = Integer.parseInt(strTokenTime.nextToken());
			int intMin = Integer.parseInt(strTokenTime.nextToken());
			int intSec = Integer.parseInt(strTokenTime.nextToken());

			Calendar cal = Calendar.getInstance();
			cal.set(intYear, intMonth, intDay, intHour, intMin, intSec);
			long longDays = (new Date().getTime() - cal.getTimeInMillis()) / 24
					/ 60 / 60 / 1000;
			longDays = longDays == 0 ? 1 : longDays;

			return "" + longDays;
		} catch (Exception e) {
			return "0";
		}
	}

	/**
	 * 比较两个时间的差值
	 * 
	 * @param date1
	 *            yyyy-MM-dd HH:mm:ss
	 * @param date2
	 *            yyyy-MM-dd HH:mm:ss
	 * @return int 小时
	 */
	public static long compareDatetime(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long timestamp1 = -1;
		long timestamp2 = -1;
		// 先将时间格式转换成Timestamp
		try {
			timestamp1 = df.parse(date1).getTime() / 1000;
			timestamp2 = df.parse(date2).getTime() / 1000;
		} catch (ParseException e) {
			System.out.println("时间格式 [ " + date1 + " ] 或 [ " + date2
					+ " ] 无法被解析");
			return -1;
		}
		if (timestamp1 > timestamp2)
			return (timestamp1 - timestamp2) / 3600;
		else
			return (timestamp2 - timestamp1) / 3600;

	}

	/**
	 * 得到某个数值之后的时间
	 * 
	 * @param String
	 *            yyyy-MM-dd HH:mm:ss
	 * @param int 整数
	 * @return String yyyy-MM-dd HH:mm:ss
	 */
	public static Date getDateTimesAfter(String datetimes, int day) {
		Calendar now = Calendar.getInstance();
		try {
			now.setTime(getDate(datetimes, DATETIME_PATTERN));
		} catch (ParseException e) {
			System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
			return null;
		}
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到某个数值之后的时间
	 * 
	 * @param String
	 *            yyyy-MM-dd HH:mm:ss　or yyyy-MM-dd
	 * @param int 整数
	 * @return String yyyy-MM-dd
	 */
	public static Date getDateAfter(String datetimes, int day) {
		Calendar now = Calendar.getInstance();
		try {
			now.setTime(getDate(datetimes, DATE_PATTERN));
		} catch (ParseException e) {
			System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
			return null;
		}
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 得到某个数值之前的日期
	 * 
	 * @param String
	 *            yyyy-MM-dd HH:mm:ss　or yyyy-MM-dd
	 * @param int 整数
	 * @return String yyyy-MM-dd
	 */
	public static String getDateBefore(String datetimes, int day) {
		Calendar now = Calendar.getInstance();
		try {
			now.setTime(getDate(datetimes, DATE_PATTERN));
		} catch (ParseException e) {
			System.out.println("时间格式 [ " + datetimes + " ]  无法被解析");
			return null;
		}
		now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
		return getString(now.getTime(), "yyyy-MM-dd");
	}

	/**
	 * 将某个时间的Timestamp转换成Datetime
	 * 
	 * @param long 时间数值
	 * @param String
	 *            时间格式　yyyy-MM-dd hh:mm:ss
	 * @return String yyyy-MM-dd hh:mm:ss
	 */
	public static String timestampToDate(long timestamp, String format) {
		Date date = new Timestamp(timestamp);
		DateFormat df = new SimpleDateFormat(format);
		return df.format(date);
	}

	/**
	 * 得到当前时间的数值
	 * 
	 * @return String yyyy-MM-dd hh:mm:ss
	 */
	public static long dateToTimestamp() {
		long ts = System.currentTimeMillis();
		return ts;
	}

	/**
	 * 得到指定时间之后的时间
	 * 
	 * @param String
	 *            time
	 * @param int num
	 * @return String yyyy-MM-dd hh:mm:ss
	 */
	public static String getDateTimeAfter(String times, int num) {
		if (times == null || "".equals(times))
			times = getCurrentDateTime();

		long tl = dateToTimestamp(times) + num * 1000;
		return timestampToDate(tl, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 将某个时间的Datetime转换成Timestamp
	 * 
	 * @param dateFormat
	 *            yyyy-MM-dd HH:mm:ss
	 * @return long
	 */
	public static long dateToTimestamp(String dateFormat) {
		long timestamp = -1;
		try {
			DateFormat df = new SimpleDateFormat(DATETIME_PATTERN);
			Date date = df.parse(dateFormat);
			timestamp = date.getTime();
		} catch (Exception e) {
			System.out.println("时间格式 [ " + dateFormat + " ] 无法被解析");
		}
		return timestamp;
	}

	public static long compareDatetime2(String date1, String date2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		long timestamp1 = -1;
		long timestamp2 = -1;
		// 先将时间格式转换成Timestamp
		try {
			timestamp1 = df.parse(date1).getTime();
			timestamp2 = df.parse(date2).getTime();
		} catch (ParseException e) {
			System.out.println("时间格式 [ " + date1 + " ] 或 [ " + date2
					+ " ] 无法被解析");
			return -1;
		}
		if (timestamp1 > timestamp2)
			return (timestamp1 - timestamp2);
		else
			return (timestamp2 - timestamp1);

	}

	// 对比两个日期大小
	public static boolean compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				return false;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				return true;
			} else {
				return true;
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) throws Exception {
		// System.out.println(getDaysToNow("2010-01-10 00:00:00"));
		// System.out.println(compare_date("2011-05-16 17:50:50","2011-05-16 17:50:55"));
		// System.out.println(getDateTimesAfter("2010-01-10 12:00:00",-5));
		// System.out.println(getDateAfter("2010-01-10",-5));
		// System.out.println(timestampToDate(1262575579707L,"yyyy-MM-dd HH:mm:ss"));
		System.out.println(compareDatetime("2012-02-02 17:36:46:673",
				"2012-02-03 18:47:37:392"));

		// "2010-01-20 17:58:37:319","2010-01-20 18:44:32:592" ibatis + bonecp
		// 27ss
		// "2010-01-21 14:51:49:307","2010-01-21 15:47:45:794" hib + c3p0 33ss
		// "2010-01-21 17:36:46:673","2010-01-21 18:47:37:392" ibatis + c3p0
		// 42ss

		// System.out.println(dateToTimestamp());
		// System.out.println(timestampToDate(1268231397000L-120000,"yyyy-MM-dd HH:mm:ss"));

		System.out.println(DateUtil.getDateString(getDateAfter(
				"2012-02-02 17:36:46", 30)));
	}
}

