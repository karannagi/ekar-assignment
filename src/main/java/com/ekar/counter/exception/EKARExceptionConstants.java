package com.ekar.counter.exception;

import lombok.Getter;

@Getter
public enum EKARExceptionConstants {
	REQUEST_VALUES_CANNOT_BE_LESS_THAN_ZERO("EKAR-EX-001","The request param cannot be less than 0"),
	INTERRUPTED_EXCEPTION("EKAR-EX-002","Process got interruped, please check logs"),
	UKNOWN_ERROR("EKAR-EX-005","Unkown Exception")
	
	;
	
	private final String errorDescription;
	private final String errorCode;
	
	EKARExceptionConstants(String errorCode,String errorDescription) {
		this.errorCode=errorCode;
		this.errorDescription=errorDescription;
	}
}
