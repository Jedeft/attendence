package com.jedeft.attendence.dao;

import com.jedeft.attendence.data.Parameters;

/**
 * 
 * @Description: 考勤参数Dao接口
 * @author Jedeft
 * @date 2016年8月20日 下午7:05:17
 */
public interface IParametersDao {
	/**
	 * 获取考勤参数
	 * 
	 * @return
	 */
	public Parameters getOne();

	/**
	 * 添加考勤参数
	 * 
	 * @param parameters
	 * @return
	 */
	public int insertOne(Parameters parameters);

	/**
	 * 更新考勤参数
	 * 
	 * @param parameters
	 * @return
	 */
	public int updateOne(Parameters parameters);
}
