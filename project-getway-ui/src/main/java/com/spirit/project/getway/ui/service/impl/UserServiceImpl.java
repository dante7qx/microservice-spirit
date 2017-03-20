package com.spirit.project.getway.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.getway.ui.client.UserFeignClient;
import com.spirit.project.getway.ui.service.UserService;
import com.spirit.project.getway.ui.vo.LoginUserMenuVO;
import com.spirit.project.getway.ui.vo.LoginUserVO;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	@HystrixCommand
	public LoginUserVO findByAccount(String account) throws SpiritUIServiceException {
		LoginUserVO loginUserVO = null;
		BaseResp<LoginUserVO> loginUserResp = userFeignClient.findByAccount(account);
		if(loginUserResp.getResultCode() == RespCodeEnum.SUCCESS.code()) {
			loginUserVO = loginUserResp.getData();
		}
		return loginUserVO;
	}

	@Override
	@HystrixCommand
	public List<LoginUserMenuVO> findUserResourceByUserId(Long userId) throws SpiritUIServiceException {
		List<LoginUserMenuVO> userMenus = null;
		BaseResp<List<LoginUserMenuVO>> userMenuResp = userFeignClient.findUserMenuByUserId(userId);
		if(userMenuResp.getResultCode() == RespCodeEnum.SUCCESS.code()) {
			userMenus = userMenuResp.getData();
		}
		return userMenus;
	}

}
