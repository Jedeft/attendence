package com.jedeft.attendence.controller;

import java.text.ParseException;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jedeft.attendence.base.exception.ErrorCode;
import com.jedeft.attendence.base.exception.ServiceException;
import com.jedeft.attendence.base.response.ResponseMsg;
import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.data.params.SignRecordParams;
import com.jedeft.attendence.service.IEmployeeService;
import com.jedeft.attendence.service.ISignRecordService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @Description: 考勤记录Controller
 * @author Jedeft
 * @date 2016年8月20日 下午8:44:22
 */
@RestController
public class SignRecordController {

	private Logger log = Logger.getLogger("attendenceLog");

	@Autowired
	private ISignRecordService signRecordService;

	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping(value = "/signRecord", method = RequestMethod.POST)
	@ApiOperation(value = "打卡/补打卡", httpMethod = "POST", response = ResponseMsg.class, notes = "时间参数格式为：yyyy-MM-dd HH:mm:ss")
	public ResponseMsg punchCard(
			@ApiParam(required = true, name = "params", value = "打卡信息") @RequestBody SignRecordParams params) {
		ResponseMsg responseMsg = new ResponseMsg();

		try {
			if (employeeService.getOneById(params.getEmployee_id()) == null) {
				responseMsg.errorCode = 6376;
				responseMsg.msg = "没有ID为：" + params.getEmployee_id() + "的员工";
				return responseMsg;
			}
			signRecordService.insertOne(params.toSignRecord());
			responseMsg.data = params;
			responseMsg.errorCode = ErrorCode.CALL_SUCCESS.code;
			responseMsg.msg = ErrorCode.CALL_SUCCESS.name;
		} catch (ServiceException e) {
			ErrorCode code = e.getErrorCode();
			responseMsg.errorCode = code.code;
			responseMsg.msg = code.name;
		} catch (ParseException e) {
			log.error(e);
			responseMsg.errorCode = ErrorCode.ERROR_TIME_FORMAT.code;
			responseMsg.msg = ErrorCode.ERROR_TIME_FORMAT.name;
		}

		return responseMsg;
	}
	
	@RequestMapping(value = "/signRecord/{employee_id}", method = RequestMethod.GET)
	@ApiOperation(value = "根据员工ID获取一个员工某个月的考勤表", httpMethod = "GET", response = ResponseMsg.class, notes = "时间参数格式为：yyyy-MM，不传时间参数时默认值为当月")
	public ResponseMsg searchSignRecord(
			@ApiParam(required = true, name = "employee_id", value = "员工ID") @PathVariable Long employee_id,
			@ApiParam(required = false, name = "month", value = "月份") @RequestParam(value="month", required = false) String month) {
		ResponseMsg responseMsg = new ResponseMsg();

		try {
			if (month == null) {
				month = DateUtils.getDateStr(new Date().getTime());
			}
			responseMsg.data = signRecordService.searchData(employee_id, month);
			responseMsg.errorCode = ErrorCode.CALL_SUCCESS.code;
			responseMsg.msg = ErrorCode.CALL_SUCCESS.name;
		} catch (ServiceException e) {
			ErrorCode code = e.getErrorCode();
			responseMsg.errorCode = code.code;
			responseMsg.msg = code.name;
		} catch (ParseException e) {
			log.error(e);
			responseMsg.errorCode = ErrorCode.ERROR_TIME_FORMAT.code;
			responseMsg.msg = ErrorCode.ERROR_TIME_FORMAT.name;
		} 

		return responseMsg;
	}
}
