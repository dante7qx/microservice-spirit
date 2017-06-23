package com.spirit.project.sysmgr.api.service;

import java.util.List;

import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritAbstractService;
import com.spirit.project.sysmgr.api.dto.req.UserModifyPasswordReqDTO;
import com.spirit.project.sysmgr.api.dto.req.UserReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserAuthRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserRespDTO;

/**
 * 用户 Service
 * 
 * @author dante
 *
 */
public interface UserService extends SpiritAbstractService<UserReqDTO, UserRespDTO> {
	
	/**
	 * 根据account登录
	 * 
	 * @param account
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public UserAuthRespDTO loginAccount(String account) throws SpiritAPIServiceException;

	/**
	 * 根据account获取用户
	 * 
	 * @param account
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public UserRespDTO findByAccount(String account) throws SpiritAPIServiceException;

	/**
	 * 根据roleId获取用户
	 * 
	 * @param roleId
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public List<UserRespDTO> findByRoleId(Long roleId) throws SpiritAPIServiceException;
	
	/**
	 * 锁定用户
	 * 
	 * @param userReqDTO
	 * @throws SpiritAPIServiceException
	 */
	public void lockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException;
	
	/**
	 * 解锁用户
	 * 
	 * @param userReqDTO
	 * @throws SpiritAPIServiceException
	 */
	public void releaseLockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException;
	
	/**
	 * 检测用户原始密码是否正确
	 * 
	 * @param userModifyPasswordReqDTO
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public Boolean checkPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException;
	
	/**
	 * 修改用户密码
	 * 
	 * @param userModifyPasswordReqDTO
	 * @throws SpiritAPIServiceException
	 */
	public void modifyPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException;
}
