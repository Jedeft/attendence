package com.jedeft.attendence.service;

import com.jedeft.attendence.data.Employee;

/**
 * 
 * @Description: 员工Service接口
 * @author Jedeft
 * @date 2016年8月18日 下午7:31:32
 */
public interface IEmployeeService {
	
	/**
	 * 添加一条员工信息
	 * @param employee
	 */
	public void insertOne(Employee employee);
}
