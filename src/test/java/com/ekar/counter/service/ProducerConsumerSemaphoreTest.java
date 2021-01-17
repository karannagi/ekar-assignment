package com.ekar.counter.service;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ekar.counter.service.impl.ConsumerServiceImpl;
import com.ekar.counter.service.impl.ProducerServiceImpl;
import com.ekar.counter.store.Counter;
import com.ekar.counter.store.impl.CounterSemaphoreImpl;

@ExtendWith(MockitoExtension.class)
public class ProducerConsumerSemaphoreTest {



	private  Counter counter;

	@Mock
	private  TransactionLogService transactionLogService;


	private ProducerServiceImpl producerService;
	private ConsumerServiceImpl consumerService;

	@BeforeEach
	public  void init() {
		counter = new CounterSemaphoreImpl();
		producerService = new ProducerServiceImpl(counter, transactionLogService);
		consumerService = new ConsumerServiceImpl(counter, transactionLogService);
				
	}


	@Test
	public void testIncrement() throws InterruptedException {
		int currentValue = counter.getValue();
		producerService.produce();
		int nextValue = counter.getValue();
		
		assertEquals(currentValue+1,nextValue);
	}
	@Test
	public void testDecrement() throws InterruptedException {
		int currentValue = counter.getValue();
		consumerService.consume();;
		int nextValue = counter.getValue();
		
		assertEquals(currentValue-1,nextValue);
	}
	
	@Test
	public void testIncremetDecrement() throws InterruptedException {
		int currentValue = counter.getValue();
		producerService.produce();
		consumerService.consume();
		int nextValue = counter.getValue();
		
		assertEquals(currentValue,nextValue);
	}

}
