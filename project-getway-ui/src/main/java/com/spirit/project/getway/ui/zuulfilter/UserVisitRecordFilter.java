package com.spirit.project.getway.ui.zuulfilter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.security.SpiritLoginUser;
import com.spirit.project.common.ui.util.IPUtils;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.getway.ui.service.SysLogService;
import com.spirit.project.getway.ui.util.RequestParamterUtils;
import com.spirit.project.getway.ui.util.SysLogUtils;

/**
 * 用户请求日志记录 Filter
 * 
 * @author dante
 *
 */
@Component
public class UserVisitRecordFilter extends ZuulFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserVisitRecordFilter.class);
	
	@Autowired
	private SysLogService sysLogService;

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		final String ip = IPUtils.getIpAddr(request);
		final String requestUri = request.getRequestURI();
		if(!requestUri.matches(".*(?<!.gif|.jpg|.png|.bmp|.jpeg|.tiff|.js|.css)$") || "127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip)) {
			return null;
		}
		
        final String method = request.getMethod();
        final String requestDate = DateUtils.getCurrentDateWithMilliSecond();
        SpiritLoginUser loginUser = LoginUserUtils.loginUser();
        if(loginUser != null) {
        	try {
    			sysLogService.recordUserVisitLog(SysLogUtils.buildSysVisitLog(ip, loginUser.getAccount(), requestUri, method, RequestParamterUtils.getRequestQueryString(request)));
    		} catch (SpiritUIServiceException e) {
    			LOGGER.error("系统日志记录失败，{}", loginUser.getAccount() + " --> " + requestUri, e);
    		}
        } else {
        	LOGGER.info("IP [{}] at [{}] request [{}] method [{}]", ip, requestDate, requestUri, method);
        }
        
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 2;
	}
	
}
