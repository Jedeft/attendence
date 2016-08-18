package com.jedeft.attendence.base.utils;

/**
 * 
 * @Description: 根据随机数判断返回true或false
 * @author Jedeft
 * @date 2016年8月18日 下午10:05:28
 */
public class JudgeUtils {
	public static boolean getSmallProbability() {
		Integer random = (int)(10 * Math.random()) + 1;
		if (random == 1) {
			//十分之一概率
			return true;
		}
		return false;
	}
	
	public static boolean getBigProbability() {
		Integer random = (int)(10 * Math.random()) + 1;
		if (random <= 5) {
			//二分之一概率
			return true;
		}
		return false;
	}
}
