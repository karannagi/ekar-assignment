package com.ekar.counter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponseDTO {

	private String errorCode;
	private String errorDescription;
}
