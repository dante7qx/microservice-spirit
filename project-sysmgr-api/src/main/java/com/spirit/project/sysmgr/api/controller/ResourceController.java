package com.spirit.project.sysmgr.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.dto.req.ResourceReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ResourceRespDTO;
import com.spirit.project.sysmgr.api.service.ResourceService;

/**
 * 资源 REST API
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceController.class);

	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 根据id获取资源
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/query_by_id/{id}")
	public BaseResp<ResourceRespDTO> queryByResourceId(@PathVariable Long id) {
		BaseResp<ResourceRespDTO> result = new BaseResp<>();
		try {
			ResourceRespDTO resourceResp = resourceService.findById(id);
			result.setData(resourceResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByResourceId id: {} error.", id, e);
		}
		return result;
	}
	
	/**
	 * 获取指定pid下的资源
	 * 
	 * @param pid
	 * @return
	 */
	@PostMapping("/query_by_pid/{pid}")
	public BaseResp<List<ResourceRespDTO>> queryByResourcePid(@PathVariable Long pid) {
		BaseResp<List<ResourceRespDTO>> result = new BaseResp<>();
		try {
			List<ResourceRespDTO> resourceResp = resourceService.findByPid(pid);
			result.setData(resourceResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByResourcePid pid: {} error.", pid, e);
		}
		return result;
	}
	
	/**
	 * 获取所有根资源节点
	 * 
	 * @return
	 */
	@PostMapping("/query_root")
	public BaseResp<List<ResourceRespDTO>> queryRootResource() {
		BaseResp<List<ResourceRespDTO>> result = new BaseResp<>();
		try {
			List<ResourceRespDTO> resourceResp = resourceService.findRootResource();
			result.setData(resourceResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryRootResource error.", e);
		}
		return result;
	}
	
	/**
	 * 新增资源
	 * 
	 * @param resourceReq
	 * @return
	 */
	@PostMapping("/add")
	public BaseResp<ResourceRespDTO> addResource(@RequestBody ResourceReqDTO resourceReq) {
		BaseResp<ResourceRespDTO> result = new BaseResp<>();
		try {
			ResourceRespDTO authorityResp = resourceService.persist(resourceReq);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("addResource resourceReq: {} error.", resourceReq, e);
		}
		return result;
	}
	
	/**
	 * 更新资源
	 * 
	 * @param resourceReq
	 * @return
	 */
	@PostMapping("/update")
	public BaseResp<ResourceRespDTO> updateResource(@RequestBody ResourceReqDTO resourceReq) {
		BaseResp<ResourceRespDTO> result = new BaseResp<>();
		try {
			ResourceRespDTO authorityResp = resourceService.persist(resourceReq);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateResource resourceReq: {} error.", resourceReq, e);
		}
		return result;
	}
	
	/**
	 * 根据id删除资源（物理删除）
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete_by_id/{id}")
	@SuppressWarnings("rawtypes")
	public BaseResp deleteById(@PathVariable Long id) {
		BaseResp result = new BaseResp<>();
		try {
			resourceService.deleteById(id);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteById id: {} error.", id, e);
		}
		return result;
	}
}
