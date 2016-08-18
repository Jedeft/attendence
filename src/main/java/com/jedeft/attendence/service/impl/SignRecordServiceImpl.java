package com.jedeft.attendence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedeft.attendence.base.exception.ErrorCode;
import com.jedeft.attendence.base.exception.ServiceException;
import com.jedeft.attendence.dao.ISignRecordDao;
import com.jedeft.attendence.data.SignRecord;
import com.jedeft.attendence.service.ISignRecordService;

/**
 * 
 * @Description: 签到记录Service实现类
 * @author Jedeft
 * @date 2016年8月18日 下午8:55:14
 */
@Service
public class SignRecordServiceImpl implements ISignRecordService{
	
	@Autowired
	private ISignRecordDao signRecordDao;
	
	@Override
	public void insertOne(SignRecord signRecord) {
		if (signRecordDao.insertOne(signRecord) < 1) {
			throw new ServiceException(ErrorCode.INSERT_FAIL);
		}
	}
	
}
