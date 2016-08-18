package com.jedeft.attendence.util;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jedeft.attendence.data.Employee;
import com.jedeft.attendence.service.IEmployeeService;


/**
 * 
 * @Description: 模拟测试数据
 * @author Jedeft
 * @date 2016年8月18日 下午8:16:00
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class CreateData {
	
	@Autowired
	private IEmployeeService employeeService;
	
	/**
	 * 模拟员工数据
	 */
	@Test
	public void createEmployeeData() {
		Employee employee = new Employee();
		employee.setCreate_time(new Timestamp(new Date().getTime()));
		String name = "Jedeft";
		for (int i = 0; i < 10; i++) {
			employee.setName(name + i);
			employeeService.insertOne(employee);
		}
	}
	
	
    
}
