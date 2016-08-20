package com.jedeft.attendence.base.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Description: 日期转换常用工具
 * @author Jedeft
 * @date 2016年8月18日 下午9:17:20
 */
public class DateUtils {
	
	public static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static final SimpleDateFormat clockSdf = new SimpleDateFormat("HH:mm:ss");
	
	public static final SimpleDateFormat weekSdf = new SimpleDateFormat("EEEE");
	
	/**
	 * 根据yyyy-MM-dd日期获取Long值
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Long getDate(String date) throws ParseException {
		return dateSdf.parse(date).getTime();
	}
	
	/**
	 * 根据Long值获取yyyy-MM-dd日期
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String getDateStr(Long date) throws ParseException {
		return dateSdf.format(date);
	}
	
	/**
	 * 根据yyyy-MM-dd HH:mm:ss日期获取Long值
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Long getTime(String time) throws ParseException {
		return timeSdf.parse(time).getTime();
	}
	
	/**
	 * 根据Long值获取yyyy-MM-dd HH:mm:ss日期
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String getTimeStr(Long time) throws ParseException {
		return timeSdf.format(time);
	}
	
	/**
	 * 根据HH-mm-ss时间获取距离凌晨毫秒数
	 * @param Time 格式可为yyyy-MM-ss HH:mm:ss   也可为HH:mm:ss
	 * @return
	 * @throws ParseException 
	 */
	public static Long getFromDawnTime(String time) throws ParseException {
		Long start = 0L;
		Long end = 0L;
		if (time.contains("-")) {
			start = dateSdf.parse(time).getTime();
			end = timeSdf.parse(time).getTime();
		} else {
			start = getDate("2016-1-1");
			end = timeSdf.parse("2016-1-1 " + time).getTime();
		}
		return end - start;
	}
	
	/**
	 * 获取当前时间
	 * @return
	 */
	public static Timestamp getNow() {
		return new Timestamp(new Date().getTime());
	}
	
	/**
	 * 转换距离凌晨毫秒数为String时钟时间字符串
	 * @return
	 * @throws ParseException 
	 */
	public static String convertClock2String(Long time) throws ParseException{
		Long day = getDate("2016-1-1");
		return clockSdf.format(new Date(day + time));
	}
	
	/**
	 * 根据yyyy-MM格式月份，获取当月他天数
	 * @return
	 * @throws ParseException 
	 */
	public static Integer getMonthDays(String date) throws ParseException {
		String[] dateArray = date.split("-");
		if (dateArray == null) {
			throw new ParseException("时间格式错误", 87);
		}
		String year = dateArray[0];
		String month = dateArray[1];
		switch (Integer.valueOf(month)) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 2:
				return isLeapYear(Integer.valueOf(year)) ? 29 : 28;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			default:
				return 0;
		}
	}
	
	/**
	 * 根据日期获得星期数
	 * @param date
	 * @return
	 */
	public static String getWeek(Date date) {
		return weekSdf.format(date);
	}
	
	/**
	 * 判断是否为闰年
	 * @param year
	 * @return
	 */
	private static boolean isLeapYear(Integer year) {
		if (year % 100 == 0) {
			if (year % 400 == 0) {
				return true;
			}
		} else {
			if (year % 4 == 0) {
				return true;
			}
		}
		return false;
	}
}
