package com.spirit.project.syslog.ui.service;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.syslog.ui.vo.syslog.SysLogVO;

/**
 * 系统日志 Service
 * 
 * @author dante
 *
 */
public interface SysLogService {
	
	/**
	 * 分页查询系统日志
	 * 
	 * @param pageReq
	 * @return
	 * @throws SpiritUIServiceException
	 */
	public PageResult<SysLogVO> findPage(PageReq pageReq) throws SpiritUIServiceException;

}
