package com.share.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 统一管理线程池
 * @author wk
 *
 */
@Configuration
public class ThreadPoolCnfig {
	
	//设置默认线程池大小
	static final int  DEFLOULD_SIZE = 5;
	
	@Bean
	public ExecutorService newFixedThreadPool() {
		return  Executors.newFixedThreadPool(DEFLOULD_SIZE);
	}
	
	@Bean
	public ExecutorService newSingleThreadPool() {
		return  Executors.newSingleThreadExecutor();
	}
	
	@Bean
	public ExecutorService newCachedThreadPool() {
		return Executors.newCachedThreadPool();
	}

}
