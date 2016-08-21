package com.jedeft.attendence.service.impl;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jedeft.attendence.base.exception.ErrorCode;
import com.jedeft.attendence.base.exception.ServiceException;
import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.dao.IEmployeeDao;
import com.jedeft.attendence.dao.IParametersDao;
import com.jedeft.attendence.dao.ISignRecordDao;
import com.jedeft.attendence.data.Employee;
import com.jedeft.attendence.data.Parameters;
import com.jedeft.attendence.data.SignRecord;
import com.jedeft.attendence.data.view.SignRecordView;
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
	
	@Autowired
	private IParametersDao parametersDao;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Override
	public void insertOne(SignRecord signRecord) {
		if (signRecordDao.insertOne(signRecord) < 1) {
			throw new ServiceException(ErrorCode.INSERT_FAIL);
		}
	}

	@Override
	public List<SignRecordView> searchData(Long employee_id, String date) throws ParseException {
		Parameters parameters = parametersDao.getOne();
		// 上班时间，临界点时间1分钟以内不算迟到
		Long startTime = parameters.getStart_time() + parameters.getLate_time() + 60 * 1000;
		Long endTime = parameters.getEnd_time() - parameters.getEarly_time();
		// 最少工作时间
		Long minTime = parameters.getMin_work_time();
		
		int number = DateUtils.getMonthDays(date);
		String[] dateArray = date.split("-");
		String monthDate = dateArray[0] + "-" + dateArray[1];
		String monthFirstDay = monthDate + "-" + 1;
		String monthLastDay = monthDate + "-" + number;
		Map<String, Object> params = new HashMap<>();
		params.put("employee_id", employee_id);
		params.put("startDate", monthFirstDay);
		params.put("endDate", monthLastDay);
		List<Map<String, Object>> recordList = signRecordDao.searchData(params);
		List<SignRecordView> list = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.00");
		for(Map<String, Object> record : recordList){
			String dayDate = record.get("sign_date").toString();
			String week = DateUtils.getWeek(new Date(DateUtils.getDate(dayDate)));
			Long start = (Long)record.get("min_time");
    		Long end = (Long)record.get("max_time");
    		String workHours = null;
    		String remark = null;
    		SignRecordView signRecordView = new SignRecordView();
    		signRecordView.setEmployee_id((Long)record.get("employee_id"));
			signRecordView.setEmployee_name(record.get("employee_name").toString());
			signRecordView.setStart_time(DateUtils.getTimeStr(DateUtils.getDate(dayDate) + start));
			signRecordView.setEnd_time(DateUtils.getTimeStr(DateUtils.getDate(dayDate) + end));
			if (start.compareTo(end) != 0) {
		    	Long temphours = end - start;
		    	if (!week.contains("六") && !week.contains("日")) {
		    		//周内上班
		    		if (temphours >= minTime) {
		    			if (start < startTime && end >= endTime) {
		    				remark = "正常";
		    			} else if (start >= startTime && end < endTime) {
		    				remark = "迟到/早退";
		    			} else if (start >= startTime) {
		    				remark = "迟到";
		    			} else if (end < endTime) {
		    				remark = "早退";
		    			}
		    		} else {
		    			remark = "缺勤";
		    		}
		    	} else {
		    		remark = "加班";
		    	}
		    	workHours = df.format(Double.valueOf(temphours.toString()) / 1000 / 60 / 60);
			} else {
				remark = "打卡异常（一天只打了一次卡）";
				workHours = "0";
			}
			signRecordView.setRemark(remark);
    		signRecordView.setWorkHours(workHours);
    		signRecordView.setWeek(week);
	    	list.add(signRecordView);
        }
		return list;
	}

	@Override
	public List<SignRecordView> searchAbnormality(String date)
			throws ParseException {
		Parameters parameters = parametersDao.getOne();
		// 上班时间，临界点时间1分钟以内不算迟到
		Long startTime = parameters.getStart_time() + parameters.getLate_time() + 60 * 1000;
		Long endTime = parameters.getEnd_time() - parameters.getEarly_time();
		// 最少工作时间
		Long minTime = parameters.getMin_work_time();
		
		int number = DateUtils.getMonthDays(date);
		String[] dateArray = date.split("-");
		String monthDate = dateArray[0] + "-" + dateArray[1];
		String monthFirstDay = monthDate + "-" + 1;
		String monthLastDay = monthDate + "-" + number;
		List<Employee> employeeList = employeeDao.searchData();
		
		List<SignRecordView> list = new ArrayList<>();
		for (Employee employee : employeeList) {
			Map<String, Object> params = new HashMap<>();
			params.put("employee_id", employee.getId());
			params.put("startDate", monthFirstDay);
			params.put("endDate", monthLastDay);
			List<Map<String, Object>> recordList = signRecordDao.searchData(params);
			DecimalFormat df = new DecimalFormat("#.00");
			for(Map<String, Object> record : recordList){
				String dayDate = record.get("sign_date").toString();
				String week = DateUtils.getWeek(new Date(DateUtils.getDate(dayDate)));
				Long start = (Long)record.get("min_time");
	    		Long end = (Long)record.get("max_time");
	    		String workHours = null;
	    		String remark = null;
	    		SignRecordView signRecordView = new SignRecordView();
	    		signRecordView.setEmployee_id((Long)record.get("employee_id"));
				signRecordView.setEmployee_name(record.get("employee_name").toString());
				signRecordView.setStart_time(DateUtils.getTimeStr(DateUtils.getDate(dayDate) + start));
				signRecordView.setEnd_time(DateUtils.getTimeStr(DateUtils.getDate(dayDate) + end));
				if (start.compareTo(end) != 0) {
			    	Long temphours = end - start;
			    	if (!week.contains("六") && !week.contains("日")) {
			    		//周内上班
			    		if (temphours >= minTime) {
			    			if (start < startTime && end >= endTime) {
			    				remark = "正常";
			    				continue;
			    			} else if (start >= startTime && end < endTime) {
			    				remark = "迟到/早退";
			    			} else if (start >= startTime) {
			    				remark = "迟到";
			    			} else if (end < endTime) {
			    				remark = "早退";
			    			}
			    		} else {
			    			remark = "缺勤";
			    		}
			    	} else {
			    		continue;
			    	}
			    	workHours = df.format(Double.valueOf(temphours.toString()) / 1000 / 60 / 60);
				} else {
					remark = "打卡异常（一天只打了一次卡）";
					workHours = "0";
				}
				signRecordView.setRemark(remark);
	    		signRecordView.setWorkHours(workHours);
	    		signRecordView.setWeek(week);
		    	list.add(signRecordView);
	        }
		}
		return list;
	}
	
}
