package com.ekar.counter.exception;

import lombok.Getter;

@Getter
public class EKARException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6388827334740735234L;

	private final String errorCode;
	public EKARException(String errorCode,String errorDescription) {
		super(errorDescription);
		this.errorCode = errorCode;
	}
	
	public EKARException(EKARExceptionConstants exception) {
		super(exception.getErrorDescription());
		this.errorCode =  exception.getErrorCode();
	}

}
