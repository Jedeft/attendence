package com.jedeft.attendence.dao;

import java.util.List;
import java.util.Map;

import com.jedeft.attendence.data.SignRecord;


/**
 * 
 * @Description: 签到记录dao层接口
 * @author Jedeft
 * @date 2016年8月18日 下午9:04:49
 */
public interface ISignRecordDao {
	/**
	 * 检索员工签到信息
	 * @param params
	 * @return
	 */
	public List<Map<String, Object>> searchData(Map<String, Object> params);

	/**
	 * 添加一条签到记录
	 * @param signRecord
	 * @return
	 */
	public int insertOne(SignRecord signRecord);
}
