package com.spirit.project.getway.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.common.ui.constant.SpiritServiceConsts;
import com.spirit.project.getway.ui.client.fallback.UserFeignClientFallback;
import com.spirit.project.getway.ui.vo.LoginUserMenuVO;
import com.spirit.project.getway.ui.vo.LoginUserVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_API, fallback = UserFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface UserFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/login/{account}")
	public BaseResp<LoginUserVO> findByAccount(@PathVariable("account") String account);
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/login_user_menu/{userId}")
	public BaseResp<List<LoginUserMenuVO>> findUserMenuByUserId(@PathVariable("userId") Long userId);
	
}
