package com.cjk.entity;

import java.util.LinkedHashMap;

public class JsonResult extends LinkedHashMap<String, Object>{
	public static final String CODE = "code";
	public static final String MESSAGE = "msg";
	public static final String DATA = "data";

	public JsonResult(){}
	
	public JsonResult(ResultCode resultCode){
		super.put(CODE, resultCode.getCode());
		super.put(MESSAGE, resultCode.getMsg());
	}
	
	public JsonResult(String code, String msg, String data){
		super.put(CODE, code);
		super.put(MESSAGE, msg);
		super.put(DATA, data);
	}
	
	public JsonResult setCode(String code){
		super.put(CODE, code);
		return this;
	}
	public Object getCode(){
		return super.get(CODE);
	}
	public void setMsg(String msg){
		super.put(MESSAGE, msg);
	}
	public Object getMsg(){
		return super.get(MESSAGE);
	}
	public JsonResult setData(Object data){
		super.put(DATA, data);
		return this;
	}
	public Object getData(){
		return super.get(DATA);
	}
	
	public JsonResult setResultCode(ResultCode resultCode){
		super.put(CODE, resultCode.getCode());
		super.put(MESSAGE, resultCode.getMsg());
		return this;
	}
	
	public static JsonResult success(){
		return new JsonResult().setResultCode(ResultCode.SUCCESS);
	}
	
	public static JsonResult fail(){
		return new JsonResult().setResultCode(ResultCode.FAIL);
	}
}
