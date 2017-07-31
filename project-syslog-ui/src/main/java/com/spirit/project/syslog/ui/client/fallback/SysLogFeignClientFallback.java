package com.spirit.project.syslog.ui.client.fallback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.syslog.ui.client.SysLogFeignClient;
import com.spirit.project.syslog.ui.vo.syslog.SysLogVO;

@Component
public class SysLogFeignClientFallback implements SysLogFeignClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SysLogFeignClientFallback.class);
	
	@Override
	public BaseResp<PageResp<SysLogVO>> findPage(PageReq pageReq) {
		LOGGER.error("findByPage pageReq {} fallback.", pageReq);
		BaseResp<PageResp<SysLogVO>> resp = new BaseResp<PageResp<SysLogVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	
	@Override
	public BaseResp<SysLogVO> addSysLog(SysLogVO sysLogVO) {
		LOGGER.error("addSysLog sysLogVO {} fallback.", sysLogVO);
		BaseResp<SysLogVO> resp = new BaseResp<SysLogVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}


}
