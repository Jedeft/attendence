package com.jedeft.attendence.data;

import java.sql.Timestamp;
import java.text.ParseException;

import com.jedeft.attendence.base.utils.DateUtils;
import com.jedeft.attendence.data.view.ParametersView;

/**
 * 
 * @Description: 与数据库交互Bean
 * @author Jedeft
 * @date 2016年8月20日 下午6:38:59
 */
public class Parameters {
	private Long start_time;
	private Long end_time;
	private Long late_time;
	private Long early_time;
	private Long min_work_time;
	private Timestamp create_time;
	private Timestamp update_time;

	public Long getStart_time() {
		return start_time;
	}

	public void setStart_time(Long start_time) {
		this.start_time = start_time;
	}

	public Long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Long end_time) {
		this.end_time = end_time;
	}

	public Long getLate_time() {
		return late_time;
	}

	public void setLate_time(Long late_time) {
		this.late_time = late_time;
	}

	public Long getEarly_time() {
		return early_time;
	}

	public void setEarly_time(Long early_time) {
		this.early_time = early_time;
	}

	public Long getMin_work_time() {
		return min_work_time;
	}

	public void setMin_work_time(Long min_work_time) {
		this.min_work_time = min_work_time;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}

	public Timestamp getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	
	public ParametersView toParametersView() throws ParseException {
		ParametersView parametersView = new ParametersView();
		
		parametersView.setStart_time(DateUtils.convertClock2String(this.start_time));
		parametersView.setEnd_time(DateUtils.convertClock2String(this.end_time));
		parametersView.setLate_time((int) (this.late_time / 1000 / 60));
		parametersView.setEarly_time((int) (this.early_time / 1000 / 60));
		parametersView.setMin_work_time((int) (this.min_work_time / 1000 / 60 / 60));
		
		return parametersView;
	}

}
