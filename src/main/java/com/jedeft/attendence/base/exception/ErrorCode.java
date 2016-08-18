package com.jedeft.attendence.base.exception;

public class ErrorCode {
	public static final ErrorCode INSERT_FAIL = new ErrorCode(1001, "添加数据失败");

	public int code;
	public String name;

	public ErrorCode(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getName(int code) {
		return name;
	}
}
