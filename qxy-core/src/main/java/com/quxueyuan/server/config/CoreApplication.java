package com.quxueyuan.server.config;

import com.quxueyuan.server.datasource.DynamicDataSourceRegister;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 核心服务启动入口
 * @author: liuwei
 * @version: 1.0
 * @date: 2018/7/15 13:28
 */
@Slf4j
@Import(DynamicDataSourceRegister.class)
@EnableAutoConfiguration
@EnableScheduling
@ComponentScan(basePackages = "com.quxueyuan")
@MapperScan("com.quxueyuan.server.dao")
@SpringBootApplication
public class CoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoreApplication.class, args);
		log.info("======================CoreApplication Start Success=====================");
	}
}
