package com.jedeft.attendence.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedeft.attendence.base.exception.ErrorCode;
import com.jedeft.attendence.base.exception.ServiceException;
import com.jedeft.attendence.dao.IEmployeeDao;
import com.jedeft.attendence.data.Employee;
import com.jedeft.attendence.service.IEmployeeService;

/**
 * 
 * @Description: 员工Service实现类
 * @author Jedeft
 * @date 2016年8月18日 下午7:33:28
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeDao employeeDao;
	
	@Override
	public void insertOne(Employee employee) {
		if (employeeDao.insertOne(employee) < 1) {
			throw new ServiceException(ErrorCode.INSERT_FAIL);
		}
	}

	@Override
	public List<Employee> searchData() {
		return employeeDao.searchData();
	}

	@Override
	public Employee getOneById(Long id) {
		return employeeDao.getOneById(id);
	}

}
