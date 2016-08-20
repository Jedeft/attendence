package com.jedeft.attendence.base.exception;

public class ErrorCode {
	
	public static final ErrorCode CALL_SUCCESS = new ErrorCode(0, "操作成功");
	
	public static final ErrorCode INSERT_FAIL = new ErrorCode(1001, "添加数据失败");
	
	public static final ErrorCode ERROR_TIME_FORMAT = new ErrorCode(2001, "错误的时间格式");

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
