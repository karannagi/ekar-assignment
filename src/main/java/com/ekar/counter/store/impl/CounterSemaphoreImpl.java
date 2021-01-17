package com.ekar.counter.store.impl;

import java.util.concurrent.Semaphore;

import org.springframework.stereotype.Component;

import com.ekar.counter.exception.EKARException;
import com.ekar.counter.exception.EKARExceptionConstants;
import com.ekar.counter.store.Counter;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CounterSemaphoreImpl implements Counter {

	private int counter = 50;
	/**
	 * Creating semaphore with only 1 permit, so only 1 thread can update the integer value at a given time.
	 */
	private Semaphore semaphore = new Semaphore(1);
	@Override
	public int increment() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			log.error("There is some error {}",e);
		}
		int  currentVal = counter;
		currentVal++;
		counter = currentVal;
		semaphore.release();
		return currentVal;
	}

	@Override
	public int decrement() {
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			log.error("There is some error {}",e);
		}
		int  currentVal = counter;
		currentVal--;
		counter = currentVal;
		semaphore.release();
		return currentVal;
	}

	@Override
	public void setValue(int value)  {
		
		try {
			semaphore.acquire();
		} catch (InterruptedException e) {
			log.error("There is some error {}",e);
		}
		counter = value;
		semaphore.release();
		

	}

	@Override
	public int getValue() {
		return counter;
	}

}
