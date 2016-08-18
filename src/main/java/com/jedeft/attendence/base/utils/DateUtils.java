package com.jedeft.attendence.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 
 * @Description: 日期转换常用工具
 * @author Jedeft
 * @date 2016年8月18日 下午9:17:20
 */
public class DateUtils {
	
	public static final SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");

	public static final SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	/**
	 * 根据yyyy-MM-dd日期获取Long值
	 * @param day
	 * @return
	 * @throws ParseException
	 */
	public static Long getDayTime(String day) throws ParseException {
		return dateSdf.parse(day).getTime();
	}
	
	/**
	 * 根据hh-mm-ss时间获取距离凌晨毫秒数
	 * @param Time
	 * @return
	 * @throws ParseException 
	 */
	public static Long getFromDawnTime(String time) throws ParseException {
		Long start = getDayTime("2016-1-1");
		Long end = timeSdf.parse("2016-1-1 " + time).getTime();
		return end - start;
	}
}
