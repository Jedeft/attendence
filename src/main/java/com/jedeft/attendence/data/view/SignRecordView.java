package com.jedeft.attendence.data.view;

/**
 * 
 * @Description: 页面数据返回Bean
 * @author Jedeft
 * @date 2016年8月20日 下午10:09:04
 */
public class SignRecordView {
	private Long employee_id;
	private String employee_name;
	private String start_time;
	private String end_time;
	private String week;
	private String workHours;
	// 备注：正常/迟到/早退/缺勤/加班
	private String remark;

	public Long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getWorkHours() {
		return workHours;
	}

	public void setWorkHours(String workHours) {
		this.workHours = workHours;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

}
