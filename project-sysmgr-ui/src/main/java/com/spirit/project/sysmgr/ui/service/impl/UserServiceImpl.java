package com.spirit.project.sysmgr.ui.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.client.UserFeignClient;
import com.spirit.project.sysmgr.ui.service.UserService;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserFeignClient userFeignClient;

	@Override
	@HystrixCommand
	public PageResult<UserVO> findPage(PageReq pageReq) throws SpiritUIServiceException {
		PageResult<UserVO> pageResult = new PageResult<UserVO>();
		BaseResp<PageResp<UserVO>> resp = userFeignClient.findPage(pageReq);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		PageResp<UserVO> pageResp = resp.getData();
		pageResult.setRows(pageResp.getResult());
		pageResult.setTotal(pageResp.getTotalCount());
		return pageResult;
	}

	@Override
	@HystrixCommand
	public UserVO findByUserId(Long id) throws SpiritUIServiceException {
		BaseResp<UserVO> resp = userFeignClient.findByUserId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public UserVO findByAccount(String account) throws SpiritUIServiceException {
		UserVO userVO = null;
		BaseResp<UserVO> resp = userFeignClient.findByAccount(account);
		if (resp.getResultCode() == RespCodeEnum.SUCCESS.code()) {
			userVO = resp.getData();
		} else {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return userVO;
	}

	@Override
	@HystrixCommand
	public void deleteUser(UserVO userVO) throws SpiritUIServiceException {
		BaseResp<? extends Object> resp = userFeignClient.deleteUser(userVO);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	@HystrixCommand
	public UserVO updateUser(UserVO userVO) throws SpiritUIServiceException {
		Long id = userVO.getId();
		BaseResp<UserVO> resp = null;
		if (id == null) {
			resp = userFeignClient.addUser(userVO);
		} else {
			resp = userFeignClient.updateUser(userVO);
		}
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public List<UserVO> findByRoleId(Long roleId) throws SpiritUIServiceException {
		BaseResp<List<UserVO>> resp = userFeignClient.findByRoleId(roleId);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public void lockUser(UserVO userVO) throws SpiritUIServiceException {
		BaseResp<? extends Object> resp = userFeignClient.lockUser(userVO);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	public void releaseLockUser(UserVO userVO) throws SpiritUIServiceException {
		BaseResp<? extends Object> resp = userFeignClient.releaseLockUser(userVO);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	public Boolean checkPassword(UserModifyPasswordVO userModifyPasswordVO) throws SpiritUIServiceException {
		BaseResp<Boolean> resp = userFeignClient.checkPassword(userModifyPasswordVO);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	public void modifyPassword(UserModifyPasswordVO userModifyPasswordVO) throws SpiritUIServiceException {
		BaseResp<? extends Object> resp = userFeignClient.modifyPassword(userModifyPasswordVO);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

}
