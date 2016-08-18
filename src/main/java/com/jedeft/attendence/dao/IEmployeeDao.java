package com.jedeft.attendence.dao;

import com.jedeft.attendence.data.Employee;


/**
 * 
 * @Description: 员工dao层接口
 * @author Jedeft
 * @date 2016年8月18日 下午7:35:36
 */
public interface IEmployeeDao {
	/**
	 * 添加一条员工信息
	 * @param employee
	 * @return
	 */
	public int insertOne(Employee employee);
}
