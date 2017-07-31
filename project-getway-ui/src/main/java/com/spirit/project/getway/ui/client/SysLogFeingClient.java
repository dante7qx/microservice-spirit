package com.spirit.project.getway.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.common.ui.constant.SpiritServiceConsts;
import com.spirit.project.getway.ui.client.fallback.SysLogFeignClientFallback;
import com.spirit.project.getway.ui.vo.SysLogVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSLOG_API, fallback = SysLogFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface SysLogFeingClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/syslog/add")
	public BaseResp<SysLogVO> addSysLog(SysLogVO roleVO); 
	
}
