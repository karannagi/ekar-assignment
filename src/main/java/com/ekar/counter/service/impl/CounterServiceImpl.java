package com.ekar.counter.service.impl;

import org.springframework.stereotype.Service;

import com.ekar.counter.service.CounterService;
import com.ekar.counter.store.Counter;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CounterServiceImpl implements CounterService {

	private final Counter counter;
	@Override
	public int getCurrentCounterValue() {
		return counter.getValue();
	}

	@Override
	public void updateCounterValue(int value) {
		 counter.setValue(value);
	}

}
