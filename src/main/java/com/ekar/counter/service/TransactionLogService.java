package com.ekar.counter.service;

public interface TransactionLogService {

	/*
	 * This method checks if the boundary conditions have been reached by the given thread
	 * */
	public void checkBoundaryReached(int value,String threadName);
	
	/*
	 * This methods logs the consumer producer request
	 * */
	public void logProdConsRequest(int producerCount,int consumerCount);
}
