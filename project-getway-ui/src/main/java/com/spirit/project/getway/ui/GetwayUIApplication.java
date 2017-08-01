package com.spirit.project.getway.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.spirit.project.getway.ui.filter.UserVisitRecordFilter;
import com.spirit.project.getway.ui.prop.SpiritProperties;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
@EnableConfigurationProperties(SpiritProperties.class)
public class GetwayUIApplication {
	
	@Bean
	public UserVisitRecordFilter userVisitRecordFilter() {
		return new UserVisitRecordFilter();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(GetwayUIApplication.class, args);
	}
}
