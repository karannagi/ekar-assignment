package com.ekar.counter.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ekar.counter.dto.CounterValueResponse;
import com.ekar.counter.exception.EKARException;
import com.ekar.counter.helper.EKARCounterHelper;
import com.ekar.counter.service.BoundedBufferService;
import com.ekar.counter.service.CounterService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/counter")
public class CounterController {

	private final BoundedBufferService bounderBufferService;
	private final CounterService counterService;

	@PostMapping("/submit")
	public ResponseEntity<Void> createThreads(@RequestParam("producers")  int producers,@RequestParam("consumers") int consumers) throws EKARException {
		EKARCounterHelper.checkRequestParams(producers,consumers);
		bounderBufferService.submitThreadCreation(producers, consumers);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping
	public ResponseEntity<Void> updateCounter(@RequestParam("counter") int counter){
		counterService.updateCounterValue(counter);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<CounterValueResponse> getCounterValue(){
		return new ResponseEntity<>(CounterValueResponse.builder().value(counterService.getCurrentCounterValue()).build(),HttpStatus.OK);
	}
}
