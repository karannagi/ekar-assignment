package com.ekar.counter.service.impl;

import org.springframework.stereotype.Service;

import com.ekar.counter.constant.TransactionType;
import com.ekar.counter.entity.TransactionLog;
import com.ekar.counter.repo.TransactionLogRepo;
import com.ekar.counter.service.TransactionLogService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class TransactionLogServiceImpl implements TransactionLogService {
	
	private final TransactionLogRepo transactionLogRepo;

	@Override
	public void checkBoundaryReached(int value, String threadName) {
		if(value == 100) {
			transactionLogRepo.save(new TransactionLog(value, threadName,TransactionType.ReachedHundred));	
			log.info("Value reached {} by thread {}",value,threadName);
		}
		else if(value == 0) {
			transactionLogRepo.save(new TransactionLog(value, threadName,TransactionType.ReachedZero));	
			log.info("Value reached {} by thread {}",value,threadName);
		}
		
	}

	@Override
	public void logProdConsRequest(int producerCount, int consumerCount) {
		transactionLogRepo.save(new TransactionLog(producerCount, consumerCount, Thread.currentThread().getName(),TransactionType.ProducerConsumerRequest));
		
	}

}
