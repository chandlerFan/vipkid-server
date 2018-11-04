package com.quxueyuan.server.config;

import com.quxueyuan.server.datasource.DynamicDataSourceRegister;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * admin启动的入口
 * @author: liuwei
 * @version: 1.0
 * @date: 2018/7/16 13:59
 */
@Slf4j
@Import(DynamicDataSourceRegister.class)
@EnableAutoConfiguration
@SpringBootApplication
//@EnableRedisHttpSession
@ServletComponentScan
@ComponentScan(basePackages = "com.quxueyuan")
@EntityScan(basePackages = "com.quxueyuan")
@MapperScan("com.quxueyuan.server.dao")
public class AdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminApplication.class, args);
		log.info("======================AdminApplication Start Success=====================");
	}
}
