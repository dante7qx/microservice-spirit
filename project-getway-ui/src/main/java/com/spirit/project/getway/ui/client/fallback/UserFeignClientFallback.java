package com.spirit.project.getway.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.getway.ui.client.UserFeignClient;
import com.spirit.project.getway.ui.vo.LoginUserMenuVO;
import com.spirit.project.getway.ui.vo.LoginUserVO;

@Component
public class UserFeignClientFallback implements UserFeignClient {
	private final static Logger logger = LoggerFactory.getLogger(UserFeignClientFallback.class);

	@Override
	public BaseResp<LoginUserVO> findByAccount(String account) {
		logger.error("findByAccount account {} hystrix.", account);
		BaseResp<LoginUserVO> resp = new BaseResp<LoginUserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<LoginUserMenuVO>> findUserMenuByUserId(Long userId) {
		logger.error("findUserMenuByUserId user {} hystrix.", userId);
		BaseResp<List<LoginUserMenuVO>> resp = new BaseResp<List<LoginUserMenuVO>>();
		LoginUserMenuVO userMenu = new LoginUserMenuVO();
		userMenu.setName("暂时不可用");
		resp.setData(Lists.newArrayList(userMenu));
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
