package com.spirit.project.getway.ui.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.spirit.project.common.ui.security.SpiritLoginUser;
import com.spirit.project.common.ui.util.LoginUserUtils;

@Controller
public class SessionController {
	
	@RequestMapping(value = "/session-timeout")
    public void sessionTimeout(HttpServletRequest request,HttpServletResponse response) throws IOException {  
        if (request.getHeader("x-requested-with") != null  
                && request.getHeader("x-requested-with").equalsIgnoreCase(  
                        "XMLHttpRequest")) { // ajax 超时处理  
            response.getWriter().print("timeout");  //设置超时标识
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().close();
        } else {
        	SpiritLoginUser loginUser = LoginUserUtils.loginUser();
        	if(loginUser != null) {
        		response.sendRedirect(request.getContextPath() + "/");  
        	} else {
        		response.sendRedirect(request.getContextPath() + "/login");  
        	}
             
        }
    }
}
