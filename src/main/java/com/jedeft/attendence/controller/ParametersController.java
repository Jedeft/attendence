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
import com.jedeft.attendence.data.Parameters;
import com.jedeft.attendence.data.params.ParametersParams;
import com.jedeft.attendence.service.IParametersService;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * 
 * @Description: 考勤参数Controller
 * @author Jedeft
 * @date 2016年8月20日 下午8:43:16
 */
@RestController
public class ParametersController {

	private Logger log = Logger.getLogger("attendenceLog");
	
	@Autowired
	private IParametersService parametersService;
	
	
	@RequestMapping(value = "/parameters", method = RequestMethod.GET)
	@ApiOperation(value = "获取考勤配置", httpMethod = "GET", response = ResponseMsg.class, notes = "无需携带参数")
	public ResponseMsg getParameters() {
		ResponseMsg responseMsg = new ResponseMsg();
		
		try {
			responseMsg.data = parametersService.getParameters().toParametersView();
			responseMsg.errorCode = ErrorCode.CALL_SUCCESS.code;
			responseMsg.msg = ErrorCode.CALL_SUCCESS.name;
		} catch(ServiceException e) {
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
	
	@RequestMapping(value = "/parameters", method = RequestMethod.PUT)
	@ApiOperation(value = "设置考勤配置", httpMethod = "PUT", response = ResponseMsg.class, notes = "PUT方法，请保证参数全量")
	public ResponseMsg setParameters(@ApiParam(required = true, name = "params", value = "考勤参数") @RequestBody ParametersParams params) {
		ResponseMsg responseMsg = new ResponseMsg();
		
		try {
			Parameters parameters = null;
			if (parametersService.getParameters() != null) {
				parameters = params.toUpdateParameters();
			} else {
				parameters = params.toCreateParameters();
			}
			parametersService.setParameters(parameters);
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
