package com.jblog.vo;

public class JsonResult {
	
	//필드
	private String result ; // 'success' or 'fail' 
	private Object data;  // Json Result 'success'일때 결과값(data)
	private String failMsg; // Json Result 'fail'일때 메세지(msg)
	
	public JsonResult() {
	}

	public JsonResult(String result, Object data, String failMsg) {		
		this.result = result;
		this.data = data;
		this.failMsg = failMsg;
	}
	
	
	
	public String getResult() {
		return result;
	}

	public Object getData() {
		return data;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void success(Object data) {
		this.result = "success";
		this.data = data;		
	}
	
	public void fail(String msg) {
		this.result = "fail";
		this.failMsg = msg;
	}
	
	@Override
	public String toString() {
		return "JsonResult [result=" + result + ", data=" + data + ", failMsg=" + failMsg + "]";
	}
	
	

}
