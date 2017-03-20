package com.spirit.project.sysmgr.ui.service;

import java.util.List;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

public interface UserService {
	
	public PageResult<UserVO> findPage(PageReq pageReq) throws SpiritUIServiceException;
	
	public UserVO findByUserId(Long id) throws SpiritUIServiceException;
	
	public UserVO findByAccount(String account) throws SpiritUIServiceException; 
	
	public List<UserVO> findByRoleId(Long roleId) throws SpiritUIServiceException;
	
	public void deleteUser(UserVO userVO) throws SpiritUIServiceException;
	
	public UserVO updateUser(UserVO userVO) throws SpiritUIServiceException;
	
	public void lockUser(UserVO userVO) throws SpiritUIServiceException;
	
	public void releaseLockUser(UserVO userVO) throws SpiritUIServiceException;
	
	public Boolean checkPassword(UserModifyPasswordVO userModifyPasswordVO) throws SpiritUIServiceException;
	
	public void modifyPassword(UserModifyPasswordVO userModifyPasswordVO) throws SpiritUIServiceException;
	
}
