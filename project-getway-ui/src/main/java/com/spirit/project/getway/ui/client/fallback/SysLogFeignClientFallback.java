package com.spirit.project.getway.ui.client.fallback;

import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.getway.ui.client.SysLogFeingClient;
import com.spirit.project.getway.ui.vo.SysLogVO;

@Component
public class SysLogFeignClientFallback implements SysLogFeingClient {

	@Override
	public BaseResp<SysLogVO> addSysLog(SysLogVO roleVO) {
		BaseResp<SysLogVO> resp = new BaseResp<SysLogVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
