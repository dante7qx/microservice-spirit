package com.spirit.project.getway.ui.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.spirit.project.commom.util.EncryptUtils;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.getway.ui.constant.SecurityConsts;
import com.spirit.project.getway.ui.exception.KaptchaException;
import com.spirit.project.getway.ui.prop.SpiritProperties;
import com.spirit.project.getway.ui.service.LdapAuthenticationService;
import com.spirit.project.getway.ui.service.SysLogService;
import com.spirit.project.getway.ui.service.UserService;
import com.spirit.project.getway.ui.util.SysLogUtils;
import com.spirit.project.getway.ui.vo.LoginUserVO;

/**
 * 自定义登录认证过滤器
 * 
 * @author dante
 *
 */
@RefreshScope
public class SpiritLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SpiritLoginFilter.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private LdapAuthenticationService ldapAuthenticationService;
	@Autowired
	private SysLogService sysLogService;
	@Autowired
	private SpiritProperties spiritProperties;

	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		if (!request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException(
					"Authentication method not supported: " + request.getMethod());
		}
		checkKaptcha(request);
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		if (username == null) {
			throw new UsernameNotFoundException("用户名不能为空");
		}
		if (password == null) {
			throw new UsernameNotFoundException("密码不能为空");
		}
		username = username.trim();
		LoginUserVO loginUser = null;
		try {
			loginUser = userService.findByAccount(username);
		} catch (SpiritUIServiceException e) {
			throw new UsernameNotFoundException("系统错误，请稍后重试！", e);
		}
		if(loginUser == null) {
			throw new UsernameNotFoundException("用户名["+username+"]不存在！");
		}
		if(SecurityConsts.STATUS_LOCK.equalsIgnoreCase(loginUser.getStatus())) {
			throw new UsernameNotFoundException("用户名["+username+"]被锁定！");
		} else if(SecurityConsts.STATUS_DEL.equalsIgnoreCase(loginUser.getStatus())) {
			throw new UsernameNotFoundException("用户名["+username+"]已被删除！");
		}
		if(loginUser.getLdapUser()) {
			if(!ldapAuthenticationService.authenticate(username, password)) {
				throw new UsernameNotFoundException("用户名或密码错误");
			}
			password = loginUser.getPassword();
		} else if(!EncryptUtils.match(password, loginUser.getPassword())) {
			throw new UsernameNotFoundException("密码错误");
		}
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
				username, password);
		super.setDetails(request, authRequest);
		Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
		if(authentication.isAuthenticated()) {
			try {
				sysLogService.recordUserVisitLog(SysLogUtils.buildSysLoginLog(username, loginUser.getLdapUser(), request));
			} catch (SpiritUIServiceException e) {
				LOGGER.error("系统日志记录失败，{}", username, e);
			}
		}
		return authentication;
	}
	
	/**
	 * 验证码校验
	 * 
	 * @param request
	 */
	private void checkKaptcha(HttpServletRequest request) {
		if(spiritProperties.getKaptcha()) {
			String kaptchaCode = request.getParameter("kaptcha");
			String genKaptcha = obtainGenKaptcha(request);
			if(!genKaptcha.equalsIgnoreCase(kaptchaCode)) {
				throw new KaptchaException("验证码错误");
			}
		}
	}
	
	private String obtainGenKaptcha(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	}
	
}
