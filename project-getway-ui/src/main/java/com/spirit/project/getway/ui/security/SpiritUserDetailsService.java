package com.spirit.project.getway.ui.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.security.SpiritLoginUser;
import com.spirit.project.common.ui.security.SpiritPrincipal;
import com.spirit.project.getway.ui.service.UserService;
import com.spirit.project.getway.ui.vo.LoginUserVO;

@Service
public class SpiritUserDetailsService implements UserDetailsService {
	
	private Logger LOGGER = LoggerFactory.getLogger(SpiritUserDetailsService.class);
	
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
		LoginUserVO loginUserVO = null;
		try {
			loginUserVO = userService.findByAccount(account);
			if(loginUserVO == null) {
				throw new UsernameNotFoundException("用户名["+account+"]不存在！");
			}
		} catch (SpiritUIServiceException e) {
			LOGGER.error("loadUserByUsername error, Account:{}", account, e);
			throw new UsernameNotFoundException("用户名["+account+"]认证失败！", e);
		}
		return convertToSpiritPrincipal(loginUserVO);
	}

	private SpiritPrincipal convertToSpiritPrincipal(LoginUserVO loginUserVO) {
		SpiritLoginUser spiritLoginUser = new SpiritLoginUser();
		BeanUtils.copyProperties(loginUserVO, spiritLoginUser);
		return new SpiritPrincipal(spiritLoginUser, loginUserVO.getPassword());
	}
}
