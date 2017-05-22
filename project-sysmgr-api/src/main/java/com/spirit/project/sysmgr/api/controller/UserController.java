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

import com.alibaba.druid.util.StringUtils;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.dto.req.UserModifyPasswordReqDTO;
import com.spirit.project.sysmgr.api.dto.req.UserReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserRespDTO;
import com.spirit.project.sysmgr.api.service.UserService;

/**
 * 用户 REST API 
 * 
 * @author dante
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 分页查询用户
	 * 
	 * @param pageReq
	 * @return
	 */
	@PostMapping("/query_page")
	public BaseResp<PageResp<UserRespDTO>> queryUserPage(@RequestBody PageReq pageReq) {
		BaseResp<PageResp<UserRespDTO>> result = new BaseResp<PageResp<UserRespDTO>>();
		try {
			PageResp<UserRespDTO> pageResp = userService.findPage(pageReq);
			result.setData(pageResp);
		} catch (SpiritAPIServiceException e) {
			LOGGER.error("queryUserPage error.", e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 根据id获取用户
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping(value = "/query_by_id/{id}")
	public BaseResp<UserRespDTO> queryByUserId(@PathVariable Long id) {
		BaseResp<UserRespDTO> result = new BaseResp<UserRespDTO>();
		try {
			UserRespDTO userResp = userService.findById(id);
			result.setData(userResp);
		} catch (Exception e) {
			LOGGER.error("queryByUserId userId: {} error.", id, e);
			result.setResultCode(RespCodeEnum.FAILURE.code());
		}
		return result;
	}
	
	/**
	 * 新增用户
	 * 
	 * @param userReqDto
	 * @return
	 */
	@PostMapping("/add")
	public BaseResp<UserRespDTO> addUser(@RequestBody UserReqDTO userReqDto) {
		BaseResp<UserRespDTO> result = new BaseResp<UserRespDTO>();
		if(!checkParam(userReqDto, true)) {
			result.setResultCode(RespCodeEnum.LACK_PARAM.code());
			return result;
		}
		try {
			UserRespDTO userRespDto = userService.persist(userReqDto);
			result.setData(userRespDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("addUser user: {} error.", userReqDto, e);
		}
		return result;
	}
	
	/**
	 * 更新用户
	 * 
	 * @param userReqDto
	 * @return
	 */
	@PostMapping("/update")
	public BaseResp<UserRespDTO> updateUser(@RequestBody UserReqDTO userReqDto) {
		BaseResp<UserRespDTO> result = new BaseResp<UserRespDTO>();
		if(!checkParam(userReqDto, false)) {
			result.setResultCode(RespCodeEnum.LACK_PARAM.code());
			return result;
		}
		try {
			UserRespDTO userRespDto = userService.persist(userReqDto);
			result.setData(userRespDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("updateUser user: {} error.", userReqDto, e);
		}
		return result;
	}
	
	/**
	 * 删除用户（逻辑删除）
	 * 
	 * @param userReqDto
	 * @return
	 */
	@DeleteMapping(value = "/delete")
	public BaseResp<?> deleteUser(@RequestBody UserReqDTO userReqDto) {
		BaseResp<?> result = new BaseResp<>();
		try {
			userService.delete(userReqDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("deleteUser userReqDto: {} error.", userReqDto, e);
		}
		return result;
	}
	
	/**
	 * 锁定用户
	 * 
	 * @param userReqDto
	 * @return
	 */
	@PostMapping(value = "/lock_user")
	public BaseResp<?> lockUser(@RequestBody UserReqDTO userReqDto) {
		BaseResp<?> result = new BaseResp<>();
		try {
			userService.lockUser(userReqDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("lockUser userReqDto: {} error.", userReqDto, e);
		}
		return result;
	}
	
	@PostMapping(value = "/release_lock_user")
	public BaseResp<?> releaseLockUser(@RequestBody UserReqDTO userReqDto) {
		BaseResp<?> result = new BaseResp<>();
		try {
			userService.releaseLockUser(userReqDto);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("releaseLockUser userReqDto: {} error.", userReqDto, e);
		}
		return result;
	}
	
	@PostMapping(value = "/check_password")
	public BaseResp<Boolean> checkPassword(@RequestBody UserModifyPasswordReqDTO userModifyPasswordReqDTO) {
		BaseResp<Boolean> result = new BaseResp<Boolean>();
		try {
			result.setData(userService.checkPassword(userModifyPasswordReqDTO));
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("userModifyPasswordReqDTO error.", userModifyPasswordReqDTO, e);
		}
		return result;
	}
	
	@PostMapping(value = "/modify_password")
	public BaseResp<?> modifyPassword(@RequestBody UserModifyPasswordReqDTO userModifyPasswordReqDTO) {
		BaseResp<?> result = new BaseResp<>();
		try {
			if(!checkPasswordParam(userModifyPasswordReqDTO)) {
				result.setResultCode(RespCodeEnum.LACK_PARAM.code());
				return result;
			}
			userService.modifyPassword(userModifyPasswordReqDTO);
		} catch (SpiritAPIServiceException e) {
			result.setResultCode(RespCodeEnum.FAILURE.code());
			LOGGER.error("modifyPassword error.", userModifyPasswordReqDTO, e);
		}
		return result;
	}
	
	@PostMapping(value = "/query_by_account/{account}")
	public BaseResp<UserRespDTO> queryByAccount(@PathVariable String account) {
		BaseResp<UserRespDTO> result = new BaseResp<UserRespDTO>();
		try {
			UserRespDTO userResp = userService.findByAccount(account);
			result.setData(userResp);
		} catch (Exception e) {
			LOGGER.error("queryByAccount account: {} error.", account, e);
		}
		return result;
	}
	
	@PostMapping(value = "/query_by_role_id/{roleId}")
	public BaseResp<List<UserRespDTO>> queryByRoleId(@PathVariable Long roleId) {
		BaseResp<List<UserRespDTO>> result = new BaseResp<List<UserRespDTO>>();
		try {
			List<UserRespDTO> userResps = userService.findByRoleId(roleId);
			result.setData(userResps);
		} catch (Exception e) {
			LOGGER.error("queryByRoleId roleId: {} error.", roleId, e);
		}
		return result;
	}
	
	/**
	 * 用户参数校验
	 * 
	 * @param userReqDTO
	 * @return
	 */
	private boolean checkParam(UserReqDTO userReqDTO, boolean isNew) {
		if(isNew) {
			if(StringUtils.isEmpty(userReqDTO.getPassword())) {
				return false;
			}
		} else if(userReqDTO.getId() == null) {
			return false;
		}
		if(StringUtils.isEmpty(userReqDTO.getAccount())) {
			return false;
		} else if(StringUtils.isEmpty(userReqDTO.getName())) {
			return false;
		} else if(StringUtils.isEmpty(userReqDTO.getEmail())) {
			return false;
		} else if(userReqDTO.getUpdateUser() == null) {
			return false;
		}
		return true;
	}
	
	/**
	 * 修改密码参数校验
	 * 
	 * @param userModifyPasswordReqDTO
	 * @return
	 */
	private boolean checkPasswordParam(UserModifyPasswordReqDTO userModifyPasswordReqDTO) {
		if(userModifyPasswordReqDTO.getId() == null) {
			return false;
		} else if(userModifyPasswordReqDTO.getUpdateUser() == null) {
			return false;
		} else if(StringUtils.isEmpty(userModifyPasswordReqDTO.getOldPassword())) {
			return false;
		} else if(StringUtils.isEmpty(userModifyPasswordReqDTO.getNewPassword())) {
			return false;
		}
		return true;
	}
}
