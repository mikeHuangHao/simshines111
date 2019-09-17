package com.simcam.adminservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author hh
 * 后台管理模块
 */
@SpringBootApplication
@MapperScan("com.simcam.adminservice.mapper")
public class AdminServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdminServiceApplication.class, args);
	}

}
