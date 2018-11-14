package com.phiz.common.thread;

import java.util.concurrent.Callable;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * <pre>
 * <br>.</br>

 * <br>Project:hzfare-core</br>
 * <br>ClassName:com.hz.fare.common.ThreadPool</br>
 * <br>Description:</br> 
 *   ----------------------------------------------------------------------
 * <br>Author:</b> <b>淳峰    1569812004@qq.com</br>
 * <br>Date:</b> <b>2018年8月6日 上午9:15:31</br>
 *   ----------------------------------------------------------------------
 * <br>Changelog:</br>
 *   Ver   Date                  Author              Detail
 *   ----------------------------------------------------------------------
 *   1.0   2018年8月6日 上午9:15:31   <b>淳峰    1569812004@qq.com</br>
 *         new file.
 * </pre>
 */
@Configuration
@EnableCaching
@ConfigurationProperties(prefix="thread") //接收application.yml中的myProps下面的属性  
public class ThreadPool {

	@Value("${thread.coreSize}")
	private  int coreSize;
	
	@Value("${thread.maxSize}")
	private int maxSize;
	
	@Value("${thread.keepAliveTime}")
	private int keepAliveTime;
	
	@Value("${thread.queueCapacity}")
	private int queueCapacity;
	
	private ThreadPoolTaskExecutor executor;
	
	@PostConstruct
	public void init() {
		executor = new ThreadPoolTaskExecutor();
		executor.setKeepAliveSeconds(keepAliveTime);
		executor.setCorePoolSize(coreSize);//核心线程池数
		executor.setMaxPoolSize(maxSize); // 最大线程
		executor.setQueueCapacity(queueCapacity);//队列容量
//		executor.setAllowCoreThreadTimeOut(true);
		executor.setRejectedExecutionHandler(new java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
	}
	
	public void excute(Runnable runnable) {
		executor.execute(runnable);
	}
	
	
	public <T> T submit(Callable<T> runnable) {
		try {
			return executor.submit(runnable).get();
		} catch (Exception e) {
			return null;
		}
	}
	
	
}
