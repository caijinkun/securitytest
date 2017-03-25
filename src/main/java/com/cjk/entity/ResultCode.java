package com.cjk.entity;

public enum ResultCode {
	SUCCESS(0, "success"),
	FAIL(-1, "fail"),
	
	Kaptcha_INVALID(10001, "验证码错误"),
	LOGIN_INVALID(10002, "用户名或密码错误"),
	USER_NOT_FOUND(10003, "用户名不存在"),
	PASS_INVALID(10004, "密码错误"),
	LOGIN_EXPIRE(10005, "登录超时"),
	PARAM_INVALID(10006, "参数错误")
	;
		
	
	private int code;
	private String msg;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ResultCode(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
}
