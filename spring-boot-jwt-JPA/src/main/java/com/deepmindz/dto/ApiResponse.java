package com.deepmindz.dto;

import java.util.HashMap;
import java.util.Map;
public class ApiResponse {
	
	private int status;
	private String message;
	Map<String,String> data = new HashMap<>();
	
	
	public ApiResponse(int status, String message, Map<String, String> data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	public ApiResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	

}
