package com.ekar.counter;

import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication
@EnableAsync
public class EkarAssignmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkarAssignmentApplication.class, args);
	}

	  @Bean
	  public Executor taskExecutor() {
	    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
	    executor.setCorePoolSize(Runtime.getRuntime().availableProcessors());
	    executor.setMaxPoolSize(Runtime.getRuntime().availableProcessors());
	    executor.setQueueCapacity(4000);
	    executor.setThreadNamePrefix("EKAR-Counter-");
	    executor.initialize();
	    return executor;
	  }
}
