package com.spirit.project.sysmgr.ui.service;

import java.util.List;

import com.spirit.project.common.ui.dto.req.EasyUIDragTreeReq;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceTreeVO;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceVO;

public interface ResourceService {
	public ResourceVO findByResourceId(Long id) throws SpiritUIServiceException;

	public List<ResourceTreeVO> findResourceTrees() throws SpiritUIServiceException;

	public void deleteByResourceId(Long id) throws SpiritUIServiceException;

	public ResourceVO updateResource(ResourceVO resourceVO) throws SpiritUIServiceException;

	public void updateResourceWhenDrag(EasyUIDragTreeReq dragTreeReq) throws SpiritUIServiceException;
}
