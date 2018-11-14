package com;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@ServletComponentScan
@CrossOrigin // 允许跨越访问
@EnableTransactionManagement
@Lazy(value = false)
@ComponentScan
@EnableAutoConfiguration
@EnableConfigurationProperties
public class PhizApplication {


	// 使用jar方式打包的启动方式
	private static CountDownLatch countDownLatch = new CountDownLatch(1);

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(PhizApplication.class, args).registerShutdownHook();
		countDownLatch.await();
	}

}
