package com.ekar.counter.store.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.ekar.counter.store.Counter;

@Component
@Primary
public class CounterAtomicImpl implements Counter{

	/**
	 * Creating atomic integer so that the increment and decrement can be done atomically
	 */
	private AtomicInteger counter = new AtomicInteger(50);
	
	public int increment() {
		return counter.incrementAndGet();
	}
	
	public int decrement() {
		return counter.decrementAndGet();
	}
	
	public void setValue(int value) {
		counter.getAndSet(value);
	}
	
	public int getValue() {
		return counter.get();
	}
}
