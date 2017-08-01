package com.spirit.project.getway.ui.zuulfilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.spirit.project.getway.ui.prop.SpiritProperties;

/**
 * 系统安全检查 Filter，第一优先级执行
 * 
 * @author dante
 *
 */
@Component
public class SysSafeCheckFilter extends ZuulFilter {
	
	@Autowired
	private SpiritProperties spiritProperties;

	@Override
	public boolean shouldFilter() {
		return spiritProperties.getSafeCheck();
	}

	@Override
	public Object run() {
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}
