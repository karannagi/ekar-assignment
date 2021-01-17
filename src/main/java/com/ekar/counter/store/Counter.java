package com.ekar.counter.store;

/**
 * This interface describes the counter store to be implemented and the necessary methods required
 * The underlying implementation can be done via DB, or in memory using necessary concurrent handling
 */
public interface Counter {

	public int increment();
	public int decrement();
	public void setValue(int value);
	public int getValue();
}
