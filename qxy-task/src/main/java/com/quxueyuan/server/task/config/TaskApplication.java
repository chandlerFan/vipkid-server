package com.quxueyuan.server.task.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = "com.quxueyuan.server.task")
@ServletComponentScan
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskApplication.class, args);
		log.info("======================TaskApplication Start Success=====================");
	}

}
