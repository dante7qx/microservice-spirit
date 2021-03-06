package com.spirit.project.sysmgr.ui.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.common.ui.util.LoginUserUtils;
import com.spirit.project.sysmgr.ui.service.UserService;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

/**
 * 用户管理 UI Controller
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 分页查询用户
	 * 
	 * @param pageReq
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@PostMapping(value = "/query_page")
	public PageResult<UserVO> queryUserPage(PageReq pageReq) {
		PageResult<UserVO> result = null;
		try {
			result = userService.findPage(pageReq);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryUserPage {} error.", pageReq, e);
		}
		return result;
	}

	/**
	 * 根据id获取用户
	 * 
	 * @param id
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@PostMapping(value = "/query_by_id")
	public BaseResp<UserVO> queryByUserId(Long id) {
		BaseResp<UserVO> result = new BaseResp<>();
		try {
			UserVO userVO = userService.findByUserId(id);
			result.setData(userVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByUserId userId: {} error.", id, e);
		}
		return result;
	}

	/**
	 * 根据account获取用户
	 * 
	 * @param account
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@PostMapping(value = "/query_by_account")
	public BaseResp<UserVO> queryByAccount(String account) {
		BaseResp<UserVO> result = new BaseResp<>();
		try {
			UserVO userVO = userService.findByAccount(account);
			result.setData(userVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("queryByAccount account: {} error.", account, e);
		}
		return result;
	}

	/**
	 * 根据roleId获取用户
	 * 
	 * @param roleId
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.query')")
	@PostMapping(value = "/query_by_role_id")
	public List<UserVO> queryByRoleId(Long roleId) {
		List<UserVO> result = Lists.newArrayList();
		try {
			result = userService.findByRoleId(roleId);
		} catch (SpiritUIServiceException e) {
			LOGGER.error("queryByRoleId roleId: {} error.", roleId, e);
		}
		return result;
	}

	/**
	 * 更新用户
	 * 
	 * @param userVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.update')")
	@PostMapping(value = "/update_user")
	public BaseResp<UserVO> updateUser(UserVO userVO) {
		BaseResp<UserVO> result = new BaseResp<>();
		try {
			userVO.setUpdateUser(LoginUserUtils.loginUserId());
			UserVO userVOResp = userService.updateUser(userVO);
			result.setData(userVOResp);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateUser userVO: {} error.", userVO, e);
		}
		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @param userVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.user.delete')")
	@PostMapping(value = "/delete_user")
	public BaseResp deleteUser(UserVO userVO) {
		BaseResp result = new BaseResp<>();
		try {
			userVO.setUpdateUser(LoginUserUtils.loginUserId());
			userService.deleteUser(userVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteByUserId id: {} error.", userVO, e);
		}
		return result;
	}

	/**
	 * 锁定用户
	 * 
	 * @param userVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.user.update')")
	@PostMapping(value = "/lock_user")
	public BaseResp lockUser(UserVO userVO) {
		BaseResp result = new BaseResp<>();
		try {
			userVO.setUpdateUser(LoginUserUtils.loginUserId());
			userService.lockUser(userVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("lockUser {} error.", userVO, e);
		}
		return result;
	}

	/**
	 * 解锁用户
	 * 
	 * @param userVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.user.update')")
	@PostMapping(value = "/release_lock_user")
	public BaseResp releaseLockUser(UserVO userVO) {
		BaseResp result = new BaseResp<>();
		try {
			userVO.setUpdateUser(LoginUserUtils.loginUserId());
			userService.releaseLockUser(userVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("releaseLockUser {} error.", userVO, e);
		}
		return result;
	}

	/**
	 * 检查用户密码
	 * 
	 * @param userModifyPasswordVO
	 * @return
	 */
	@PreAuthorize("hasAuthority('sysmgr.user.update')")
	@PostMapping(value = "/check_password")
	public BaseResp<Boolean> checkPassword(UserModifyPasswordVO userModifyPasswordVO) {
		BaseResp<Boolean> result = new BaseResp<>();
		try {
			userModifyPasswordVO.setUpdateUser(LoginUserUtils.loginUserId());
			result.setData(userService.checkPassword(userModifyPasswordVO));
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("checkPassword {} error.", userModifyPasswordVO, e);
		}
		return result;
	}

	/**
	 * 修改用户密码
	 * 
	 * @param userModifyPasswordVO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@PreAuthorize("hasAuthority('sysmgr.user.update')")
	@PostMapping(value = "/modify_password")
	public BaseResp modifyPassword(UserModifyPasswordVO userModifyPasswordVO) {
		BaseResp result = new BaseResp<>();
		try {
			userModifyPasswordVO.setUpdateUser(LoginUserUtils.loginUserId());
			userService.modifyPassword(userModifyPasswordVO);
		} catch (SpiritUIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("modifyPassword {} error.", userModifyPasswordVO, e);
		}
		return result;
	}
}
