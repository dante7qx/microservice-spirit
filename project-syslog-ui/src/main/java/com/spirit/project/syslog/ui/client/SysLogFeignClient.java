package com.spirit.project.syslog.ui.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.syslog.ui.client.fallback.SysLogFeignClientFallback;
import com.spirit.project.syslog.ui.constant.SpiritServiceConsts;
import com.spirit.project.syslog.ui.vo.syslog.SysLogVO;

/**
 * 系统日志模块Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSLOG_SERVER_NAME, fallback = SysLogFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface SysLogFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/syslog/query_page")
	BaseResp<PageResp<SysLogVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/add")
	BaseResp<SysLogVO> addSysLog(SysLogVO serviceModuleVO);
	

}
