package com.spirit.project.syslog.ui.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.syslog.ui.client.SysLogFeignClient;
import com.spirit.project.syslog.ui.service.SysLogService;
import com.spirit.project.syslog.ui.vo.syslog.SysLogVO;

/**
 * 系统日志服务实现类
 * 
 * @author dante
 *
 */
@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private SysLogFeignClient serviceModuleFeignClient;

	@Override
	@HystrixCommand
	public PageResult<SysLogVO> findPage(PageReq pageReq) throws SpiritUIServiceException {
		PageResult<SysLogVO> pageResult = new PageResult<>();
		BaseResp<PageResp<SysLogVO>> resp = serviceModuleFeignClient.findPage(pageReq);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		PageResp<SysLogVO> pageResp = resp.getData();
		pageResult.setRows(pageResp.getResult());
		pageResult.setTotal(pageResp.getTotalCount());
		return pageResult;
	}

}
