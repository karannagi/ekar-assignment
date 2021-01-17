package com.ekar.counter.entity;

import java.time.Instant;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.ekar.counter.constant.TransactionType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TransactionLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private Integer counterVal;
	
	private String threadName;
	
	private Instant timestamp;
	
	@Enumerated(EnumType.STRING)
	private TransactionType transaction;
	
	private Integer producerCount;
	private Integer consumerCount;
	
	@PrePersist
	private void setTime() {
		this.timestamp = Instant.now();
	}
	
	public TransactionLog(int counterVal,String threadName,TransactionType transactionType) {
		this.counterVal = counterVal;
		this.threadName = threadName;
		this.transaction =  transactionType;
	}
	
	public TransactionLog(int producerCount,int consumerCount,String threadName,TransactionType transactionType) {
		this.producerCount = producerCount;
		this.consumerCount = consumerCount;
		this.threadName = threadName;
		this.transaction =  transactionType;
	}
}
