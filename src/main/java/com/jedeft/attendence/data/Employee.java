package com.jedeft.attendence.data;

import java.sql.Timestamp;

/**
 * 
 * @Description: 员工类
 * @author Jedeft
 * @date 2016年8月18日 下午8:35:54
 */
public class Employee {
	private Long id;
	private String name;
	private Timestamp create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Timestamp getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
}
