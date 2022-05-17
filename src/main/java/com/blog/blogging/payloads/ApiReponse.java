package com.blog.blogging.payloads;

public class ApiReponse {
	private String message;
	private boolean success;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public ApiReponse(String message, boolean success) {
		super();
		this.message = message;
		this.success = success;
	}
	
	

}
