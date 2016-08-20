package com.jedeft.attendence.service;

import java.text.ParseException;
import java.util.List;

import com.jedeft.attendence.data.SignRecord;
import com.jedeft.attendence.data.view.SignRecordView;

/**
 * 
 * @Description: 签到记录Service接口
 * @author Jedeft
 * @date 2016年8月18日 下午8:44:29
 */
public interface ISignRecordService {
	/**
	 * 添加一条签到信息
	 * @param signRecord
	 */
	public void insertOne(SignRecord signRecord);
	
	/**
	 * 根据员工ID以及月份时间来获取一个员工某个月的考勤表
	 * @param employee_id
	 * @param date
	 * @return
	 */
	public List<SignRecordView> searchData(Long employee_id, String date) throws ParseException;
}
