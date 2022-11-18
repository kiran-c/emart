package com.emart.exceptions;

public class BusinessException extends Exception {

	private String errorCode;
	private String errorMsg;

	public BusinessException(String msg)
	{
		super(msg);
	}
	
	
	public BusinessException() {
		
	}


	public String getErrorCode() {
		return errorCode;
	}
	
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	
	
}
