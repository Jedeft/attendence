package com.jedeft.attendence.data;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * 
 * @Description: 签到记录类
 * @author Jedeft
 * @date 2016年8月18日 下午8:46:39
 */
public class SignRecord {
	private Long id;
	private Long employee_id;
	private Date sign_date;
	private Long sign_time;
	private Timestamp create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public Date getSign_date() {
		return sign_date;
	}

	public void setSign_date(Date sign_date) {
		this.sign_date = sign_date;
	}

	public Long getSign_time() {
		return sign_time;
	}

	public void setSign_time(Long sign_time) {
		this.sign_time = sign_time;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public SignRecord(Long employee_id, Date sign_date, Long sign_time,
			Timestamp create_time) {
		super();
		this.employee_id = employee_id;
		this.sign_date = sign_date;
		this.sign_time = sign_time;
		this.create_time = create_time;
	}

	public SignRecord() {
		super();
	}

}
