package com.jedeft.attendence.data.params;

import java.text.ParseException;

import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.data.Parameters;

/**
 * 
 * @Description: 考勤设置参数
 * @author Jedeft
 * @date 2016年8月20日 下午6:35:32
 */
public class ParametersParams {
	private String start_time;
	private String end_time;
	// 迟到时间，单位：分钟
	private Integer late_time;
	// 早退时间，单位：分钟
	private Integer early_time;
	// 最小工作时间，工作时间小于当前值为缺勤
	private Integer min_work_time;

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

	public Integer getLate_time() {
		return late_time;
	}

	public void setLate_time(Integer late_time) {
		this.late_time = late_time;
	}

	public Integer getEarly_time() {
		return early_time;
	}

	public void setEarly_time(Integer early_time) {
		this.early_time = early_time;
	}

	public Integer getMin_work_time() {
		return min_work_time;
	}

	public void setMin_work_time(Integer min_work_time) {
		this.min_work_time = min_work_time;
	}

	public Parameters toUpdateParameters() throws ParseException {
		Parameters parameters = this.conversion();
		parameters.setUpdate_time(DateUtils.getNow());

		return parameters;
	}

	public Parameters toCreateParameters() throws ParseException {
		Parameters parameters = this.conversion();
		parameters.setCreate_time(DateUtils.getNow());

		return parameters;
	}

	private Parameters conversion() throws ParseException {
		Parameters parameters = new Parameters();
		parameters.setStart_time(DateUtils.getFromDawnTime(this.start_time));
		parameters.setEnd_time(DateUtils.getFromDawnTime(this.end_time));
		parameters.setLate_time((long) (this.late_time * 60 * 1000));
		parameters.setEarly_time((long) (this.early_time * 60 * 1000));
		parameters.setMin_work_time((long) (this.min_work_time * 60 * 60 * 1000));

		return parameters;
	}
}
