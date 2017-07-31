package com.spirit.project.getway.ui.service;

import java.util.List;

import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.getway.ui.vo.LoginUserMenuVO;
import com.spirit.project.getway.ui.vo.LoginUserVO;

/**
 * 用户认证 Service
 * 
 * @author dante
 *
 */
public interface UserService {
	
	public LoginUserVO findByAccount(String account) throws SpiritUIServiceException;
	
	public List<LoginUserMenuVO> findUserResourceByUserId(Long userId) throws SpiritUIServiceException;
}
