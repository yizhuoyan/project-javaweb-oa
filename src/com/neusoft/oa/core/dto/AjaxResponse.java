package com.neusoft.oa.core.dto;

public class AjaxResponse {
	private static final String CODE_SUCCESS="ok",CODE_ERROR="error";
	
	/**应答的代号*/
	private String code;
	private String message;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public static AjaxResponse ok() {
		return ok(null);
	}
	public static AjaxResponse ok(Object data) {
		AjaxResponse ar=new AjaxResponse();
		ar.setCode(CODE_SUCCESS);
		ar.setData(data);
		return ar;
	}
	public static AjaxResponse fail(String message) {
		return fail(CODE_ERROR,message);
	}
	public static AjaxResponse fail(Throwable  e) {
		return fail(CODE_ERROR,e.getMessage());
	}
	public static AjaxResponse fail(String code,Throwable e) {
		return fail(CODE_ERROR,e.getMessage());
	}
	public static AjaxResponse fail(String code,String message) {
		AjaxResponse ar=new AjaxResponse();
		ar.setCode(code);
		ar.setMessage(message);
		ar.setData(null);
		return ar;
	}
	
}
