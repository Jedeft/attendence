package com.jedeft.attendence.service;

import com.jedeft.attendence.data.Parameters;

/**
 * 
 * @Description: 考勤参数Service接口
 * @author Jedeft
 * @date 2016年8月20日 下午7:01:10
 */
public interface IParametersService {
	/**
	 * 设置考勤参数
	 * 
	 * @param parametersParams
	 */
	public void setParameters(Parameters parameters);

	/**
	 * 获取考勤参数
	 * 
	 * @return
	 */
	public Parameters getParameters();
}
