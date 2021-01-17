package com.ekar.counter.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.ekar.counter.dto.ErrorResponseDTO;
import com.ekar.counter.exception.EKARException;
import com.ekar.counter.exception.EKARExceptionConstants;

import lombok.extern.slf4j.Slf4j;

/**
 *Global exception handler, the exceptions thrown are caught, logged and then returned to the user
 */
@ControllerAdvice
@Slf4j

public class GlobalExceptionHandler {
	
	@ExceptionHandler({EKARException.class})
	public final ResponseEntity<ErrorResponseDTO> handleException(EKARException ex, WebRequest request) {
		log.error("Exception occured while processing request, errorCode: {}, errorDescription: {}",ex.getErrorCode(),ex.getMessage());

		return new ResponseEntity<ErrorResponseDTO>(
				ErrorResponseDTO.builder().errorCode(ex.getErrorCode()).errorDescription(ex.getMessage()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ Exception.class})
	public final ResponseEntity<ErrorResponseDTO> handleException(Exception ex, WebRequest request) {

		return new ResponseEntity<ErrorResponseDTO>(
				ErrorResponseDTO.builder().errorCode(EKARExceptionConstants.UKNOWN_ERROR.getErrorCode()).errorDescription(EKARExceptionConstants.UKNOWN_ERROR.getErrorDescription()).build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
