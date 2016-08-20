package com.jedeft.attendence.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedeft.attendence.dao.IParametersDao;
import com.jedeft.attendence.data.Parameters;
import com.jedeft.attendence.service.IParametersService;

/**
 * 
 * @Description: 考勤参数Service实现类
 * @author Jedeft
 * @date 2016年8月20日 下午7:04:18
 */
@Service
public class ParametersServiceImpl implements IParametersService {

	@Autowired
	private IParametersDao parametersDao;

	@Override
	public void setParameters(Parameters parameters) {
		if (parameters.getUpdate_time() != null) {
			// 更新
			parametersDao.updateOne(parameters);
		} else {
			// 新增
			parametersDao.insertOne(parameters);
		}
	}

	@Override
	public Parameters getParameters() {
		return parametersDao.getOne();
	}

}
