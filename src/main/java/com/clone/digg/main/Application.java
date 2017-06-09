package com.clone.digg.main;

import java.io.IOException;
import java.util.concurrent.Executor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
@EnableAsync
@ComponentScan(basePackages = { "com.clone" })
public class Application extends WebMvcConfigurerAdapter {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public Executor asyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(50);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("DiggClone-Thread-");
		executor.initialize();
		return executor;
	}

}
