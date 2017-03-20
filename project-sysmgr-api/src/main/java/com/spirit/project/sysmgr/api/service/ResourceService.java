package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.ResourceReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ResourceRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserResourceRespDTO;

public interface ResourceService extends SpiritAbstractService<ResourceReqDTO, ResourceRespDTO>{
	
	/**
	 * 获取当前登录用户的所有菜单
	 * 
	 * @param userId
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public List<UserResourceRespDTO> findUserResourceByUserId(Long userId) throws SpiritAPIServiceException;
	
	public List<ResourceRespDTO> findRootResource() throws SpiritAPIServiceException;

	public List<ResourceRespDTO> findByPid(Long pid) throws SpiritAPIServiceException;
}
