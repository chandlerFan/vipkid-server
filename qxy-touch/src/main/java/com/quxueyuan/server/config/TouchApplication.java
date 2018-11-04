package com.quxueyuan.server.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

/**
 * h5启动入口
 * @author: liuwei
 * @version: 1.0
 * @date: 2018/7/15 13:28
 */
@Slf4j
@EnableAutoConfiguration
@SpringBootApplication
//@EnableRedisHttpSession
@ServletComponentScan
@ComponentScan(basePackages = "com.quxueyuan")
@EntityScan(basePackages = "com.quxueyuan")
public class TouchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TouchApplication.class, args);
		log.info("======================TouchApplication Start Success=====================");
	}
}
