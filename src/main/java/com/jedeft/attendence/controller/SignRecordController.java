package com.jedeft.attendence.controller;

import java.text.ParseException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jedeft.attendence.base.exception.ErrorCode;
import com.jedeft.attendence.base.exception.ServiceException;
import com.jedeft.attendence.base.response.ResponseMsg;
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
	public ResponseMsg setParameters(
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
}
