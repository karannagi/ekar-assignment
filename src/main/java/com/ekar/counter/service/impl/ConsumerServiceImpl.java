package com.ekar.counter.service.impl;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ekar.counter.service.ConsumerService;
import com.ekar.counter.service.TransactionLogService;
import com.ekar.counter.store.Counter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@AllArgsConstructor
@Slf4j
public class ConsumerServiceImpl implements ConsumerService{

	private final Counter counter;	
	private final TransactionLogService transactionLogService;
	@Override
	@Async
	@Transactional
	public void consume() {
		
			int changedValue = counter.decrement();
			transactionLogService.checkBoundaryReached(changedValue, Thread.currentThread().getName());
			log.info("Consumed Value {} for thread: {} at {}",changedValue,Thread.currentThread().getName(),Instant.now());
	}

}
