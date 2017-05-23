package com.spirit.project.getway.ui.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spirit.project.common.ui.security.SpiritLoginUser;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.getway.ui.constant.SecurityConsts;

/**
 * 当session过期时，ajax请求跳转到登录页
 * 
 * @author dante
 *
 */
@Controller
public class SessionController {

	/**
	 * 处理session过期
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/session-timeout")
	public void sessionTimeout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// Ajax 超时处理
		if ("XMLHttpRequest".equalsIgnoreCase(request.getHeader("x-requested-with"))) {
			// 设置超时标识
			response.getWriter().print("timeout");
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.getWriter().close();
		} else {
			SpiritLoginUser loginUser = LoginUserUtils.loginUser();
			if (loginUser != null) {
				response.sendRedirect(request.getContextPath() + SecurityConsts.INDEX_PAGE);
			} else {
				response.sendRedirect(request.getContextPath() + SecurityConsts.LOGIN_PAGE);
			}

		}
	}
}
