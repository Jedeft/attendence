package com.jedeft.attendence.util;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Random;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.base.utils.JudgeUtils;
import com.jedeft.attendence.data.Employee;
import com.jedeft.attendence.data.SignRecord;
import com.jedeft.attendence.service.IEmployeeService;
import com.jedeft.attendence.service.ISignRecordService;


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
	
	@Autowired
	private ISignRecordService signRecordService;
	
	/**
	 * 模拟员工数据
	 */
	@Test
	public void createEmployeeData() {
		Employee employee = new Employee();
		employee.setCreate_time(new Timestamp(new java.util.Date().getTime()));
		String name = "Jedeft";
		for (int i = 0; i < 10; i++) {
			employee.setName(name + i);
			employeeService.insertOne(employee);
		}
	}
	
	/**
	 * 模拟签到记录数据
	 * 模拟时间区间为 2016-1-1 到 2016-8-18
	 * @throws ParseException 
	 */
	@Test
	public void createSignRecordData() throws ParseException {
		List<Employee> employeeList = employeeService.searchData();
		
		SignRecord signRecord = new SignRecord();
		signRecord.setCreate_time(new Timestamp(new java.util.Date().getTime()));
		
		String startDate = "2016-1-1";
		String endDate = "2016-8-18";
		String startTime = "09:00:00";
		String endTime = "18:00:00";
		Long amTime = DateUtils.getFromDawnTime(startTime);
		Long pmTime = DateUtils.getFromDawnTime(endTime);
		Date end = new Date(DateUtils.getDayTime(endDate));
		Date signDate = new Date(DateUtils.getDayTime(startDate));
		Random random = new Random();

		for(Employee employee : employeeList) {
			while(signDate.getTime() <= end.getTime()) {
				Long am = amTime;
				Long pm = pmTime;
				//上午签到
				signRecord.setEmployee_id(employee.getId());
				signRecord.setSign_date(signDate);
				//小概率出现迟到
				if (JudgeUtils.getSmallProbability()) {
					//一半概率出现严重迟到现象
					if (JudgeUtils.getBigProbability()) {
						//不严重时随机迟到30分钟内
						am += random.nextInt(30) * 60 * 1000;
					} else {
						//严重时随机迟到5小时以内
						am += (random.nextInt(5) + 1) * 60 * 60 * 1000;
					}
				}
				signRecord.setSign_time(am);
				signRecordService.insertOne(signRecord);
				//下午签到，小概率出现早退
				if (JudgeUtils.getSmallProbability()) {
					//一半概率出现严重早退现象
					if (JudgeUtils.getBigProbability()) {
						//不严重时随机早退30分钟内
						pm += random.nextInt(30) * 60 * 1000;
					} else {
						//严重时随机早退2小时以内
						pm -= (random.nextInt(2) + 1) * 60 * 60 * 1000;
					}
				}
				signRecord.setSign_time(pm);
				signRecordService.insertOne(signRecord);
				// 一天有 86400000 毫秒
				signDate = new Date(signDate.getTime() + 86400000);
			}
			signDate = new Date(DateUtils.getDayTime(startDate));
		}
		
		
	}
    
}
