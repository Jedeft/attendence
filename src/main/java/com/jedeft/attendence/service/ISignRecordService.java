package com.jedeft.attendence.service;

import com.jedeft.attendence.data.SignRecord;

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
}
