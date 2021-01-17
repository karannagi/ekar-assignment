package com.ekar.counter.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekar.counter.entity.TransactionLog;

public interface TransactionLogRepo extends JpaRepository<TransactionLog, Long>{

}
