package com.spirit.project.getway.ui.service;

import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.getway.ui.vo.SysLogVO;

/**
 * 系统日志 Service
 * 
 * @author dante
 *
 */
public interface SysLogService {
	
	public void recordUserVisitLog(SysLogVO sysLogVO) throws SpiritUIServiceException;
	
}
