package com.ekar.counter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CounterValueResponse {

	int value;
}
