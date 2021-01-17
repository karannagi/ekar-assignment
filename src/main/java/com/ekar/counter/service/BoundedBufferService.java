package com.ekar.counter.service;

public interface BoundedBufferService {

	public void submitThreadCreation(int producers, int consumers);
}
