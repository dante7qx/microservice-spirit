package com.spirit.project.sysmgr.dao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.spirit.project.sysmgr.dao.mapper")
public class SysMgrDaoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SysMgrDaoApplication.class, args);
	}
}
