package com.jedeft.attendence.data.view;

/**
 * 
 * @Description: 页面数据返回Bean
 * @author Jedeft
 * @date 2016年8月20日 下午7:46:46
 */
public class ParametersView {
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

}
