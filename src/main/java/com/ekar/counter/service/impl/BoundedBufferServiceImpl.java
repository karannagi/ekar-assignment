package com.ekar.counter.service.impl;

import java.lang.Thread.UncaughtExceptionHandler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ekar.counter.service.BoundedBufferService;
import com.ekar.counter.service.ConsumerService;
import com.ekar.counter.service.ProducerService;
import com.ekar.counter.service.TransactionLogService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Service
@Slf4j
public class BoundedBufferServiceImpl implements BoundedBufferService {

	private final ProducerService producerService;
	private final ConsumerService consumerService;
	private final TransactionLogService transactionLogService;

	@Override
	@Async
	public void submitThreadCreation(int producers, int consumers) {
		transactionLogService.logProdConsRequest(producers, consumers);
		
		Thread consumerThread = getConsumerThread(consumers);
		Thread producerThread = getProducerThreads(producers);
		Thread.UncaughtExceptionHandler h = getExceptionHandler();
		
		consumerThread.setUncaughtExceptionHandler(h);
		producerThread.setUncaughtExceptionHandler(h);
		
		consumerThread.start();
		producerThread.start();


	}

	private UncaughtExceptionHandler getExceptionHandler() {
		return new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread th, Throwable ex) {
				log.error("Uncaught exception: for thread {} and exception {}",th.getName(), ex.getMessage());
			}
		};
	}

	private Thread getProducerThreads(int producers) {
		return new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i =0; i< producers;i++) {
					producerService.produce();
				}

			}
		});
	}

	private Thread getConsumerThread(int consumers) {
		return new Thread(new Runnable() {

			@Override
			public void run() {
				for(int i =0; i< consumers;i++) {
					consumerService.consume();
				}

			}
		});
	}

}
