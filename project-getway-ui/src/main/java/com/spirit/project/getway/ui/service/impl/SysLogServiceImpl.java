package com.spirit.project.getway.ui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.getway.ui.client.SysLogFeingClient;
import com.spirit.project.getway.ui.service.SysLogService;
import com.spirit.project.getway.ui.vo.SysLogVO;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogFeingClient sysLogFeingClient;

	@Override
	@HystrixCommand
	@Async("sysLogAsync")
	public void recordUserVisitLog(SysLogVO sysLogVO) throws SpiritUIServiceException {
		sysLogFeingClient.addSysLog(sysLogVO);
	}

}
