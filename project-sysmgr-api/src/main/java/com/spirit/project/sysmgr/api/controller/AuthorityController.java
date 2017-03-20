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
import com.spirit.project.sysmgr.api.dto.req.AuthorityReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.AuthorityRespDTO;
import com.spirit.project.sysmgr.api.service.AuthorityService;

@RestController
@RequestMapping("/authority")
public class AuthorityController {
private static final Logger logger = LoggerFactory.getLogger(AuthorityController.class);
	
	@Autowired
	private AuthorityService authorityService;
	
	@PostMapping("/query_by_id/{id}")
	public BaseResp<AuthorityRespDTO> queryByAuthorityId(@PathVariable Long id) {
		BaseResp<AuthorityRespDTO> result = new BaseResp<AuthorityRespDTO>();
		try {
			AuthorityRespDTO authorityResp = authorityService.findById(id);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("queryByAuthorityId id: {} error.", id, e);
		}
		return result;
	}
	
	@PostMapping("/query_by_pid/{pid}")
	public BaseResp<List<AuthorityRespDTO>> queryByAuthorityPid(@PathVariable Long pid) {
		BaseResp<List<AuthorityRespDTO>> result = new BaseResp<List<AuthorityRespDTO>>();
		try {
			List<AuthorityRespDTO> authorityResp = authorityService.findByPid(pid);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("queryByAuthorityPid pid: {} error.", pid, e);
		}
		return result;
	}
	
	@PostMapping("/query_root")
	public BaseResp<List<AuthorityRespDTO>> queryRootAuthority() {
		BaseResp<List<AuthorityRespDTO>> result = new BaseResp<List<AuthorityRespDTO>>();
		try {
			List<AuthorityRespDTO> authorityResp = authorityService.findRootAuthority();
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("queryRootAuthority error.", e);
		}
		return result;
	}
	
	@PostMapping("/add")
	public BaseResp<AuthorityRespDTO> addAuthority(@RequestBody AuthorityReqDTO authorityReq) {
		BaseResp<AuthorityRespDTO> result = new BaseResp<AuthorityRespDTO>();
		try {
			AuthorityRespDTO authorityResp = authorityService.persist(authorityReq);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("addAuthority authorityReq: {} error.", authorityReq, e);
		}
		return result;
	}
	
	@PostMapping("/update")
	public BaseResp<AuthorityRespDTO> updateAuthority(@RequestBody AuthorityReqDTO authorityReq) {
		BaseResp<AuthorityRespDTO> result = new BaseResp<AuthorityRespDTO>();
		try {
			AuthorityRespDTO authorityResp = authorityService.persist(authorityReq);
			result.setData(authorityResp);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("updateAuthority authorityReq: {} error.", authorityReq, e);
		}
		return result;
	}
	
	@DeleteMapping("/delete_by_id/{id}")
	public BaseResp<?> deleteById(@PathVariable Long id) {
		BaseResp<?> result = new BaseResp<>();
		try {
			authorityService.deleteById(id);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			logger.error("deleteById id: {} error.", id, e);
		}
		return result;
	}
}
