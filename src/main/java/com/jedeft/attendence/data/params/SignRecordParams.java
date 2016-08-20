package com.jedeft.attendence.data.params;

import java.sql.Date;
import java.text.ParseException;

import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.data.SignRecord;

/**
 * 
 * @Description: 签到记录参数
 * @author Jedeft
 * @date 2016年8月20日 下午8:46:44
 */
public class SignRecordParams {
	private Long employee_id;
	private String sign_time;

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getSign_time() {
		return sign_time;
	}

	public void setSign_time(String sign_time) {
		this.sign_time = sign_time;
	}
	
	public SignRecord toSignRecord() throws ParseException {
		SignRecord signRecord = new SignRecord();
		
		signRecord.setEmployee_id(this.employee_id);
		signRecord.setSign_date(new Date(DateUtils.getTime(this.sign_time)));
		signRecord.setSign_time(DateUtils.getFromDawnTime(this.sign_time));
		signRecord.setCreate_time(DateUtils.getNow());
		
		return signRecord;
	}
}
