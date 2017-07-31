package com.spirit.project.syslog.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.session.data.redis.RedisFlushMode;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.spirit.project.common.ui.constant.SpiritSessionConfigConsts;

@SpringBootApplication
@EnableFeignClients
@EnableZuulProxy
@EnableRedisHttpSession(redisNamespace = SpiritSessionConfigConsts.REDIS_NAMESPACE, maxInactiveIntervalInSeconds = SpiritSessionConfigConsts.MAX_INACTIVE_INTERVAL_IN_SECONDS, redisFlushMode = RedisFlushMode.IMMEDIATE)
public class SysLogUIApplication {
	public static void main(String[] args) {
		SpringApplication.run(SysLogUIApplication.class, args);
	}
}
