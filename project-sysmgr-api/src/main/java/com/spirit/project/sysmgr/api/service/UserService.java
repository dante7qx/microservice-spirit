package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.UserModifyPasswordReqDTO;
import com.spirit.project.sysmgr.api.dto.req.UserReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserAuthRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserRespDTO;

public interface UserService extends SpiritAbstractService<UserReqDTO, UserRespDTO> {
	public UserAuthRespDTO loginAccount(String account) throws SpiritAPIServiceException;

	public UserRespDTO findByAccount(String account) throws SpiritAPIServiceException;

	public List<UserRespDTO> findByRoleId(Long roleId) throws SpiritAPIServiceException;
	
	public void lockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException;
	
	public void releaseLockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException;
	
	public Boolean checkPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException;
	
	public void modifyPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException;
}
