package com.ekar.counter.service.impl;

import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ekar.counter.service.ProducerService;
import com.ekar.counter.service.TransactionLogService;
import com.ekar.counter.store.Counter;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProducerServiceImpl implements ProducerService {
	
	private final Counter counter;
	private final TransactionLogService transactionLogService;

	@Override
	@Async
	@Transactional
	public void produce() {
			int changedValue = counter.increment();
			transactionLogService.checkBoundaryReached(changedValue, Thread.currentThread().getName());
			log.info("Produced Value {} for thread: {} at {}",changedValue,Thread.currentThread().getName(),Instant.now());

	}

}
