package com.spirit.project.sysmgr.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.sysmgr.ui.client.UserFeignClient;
import com.spirit.project.sysmgr.ui.vo.user.UserModifyPasswordVO;
import com.spirit.project.sysmgr.ui.vo.user.UserVO;

@Component
public class UserFeignClientFallback implements UserFeignClient {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserFeignClientFallback.class);

	@Override
	public BaseResp<PageResp<UserVO>> findPage(PageReq pageReq) {
		LOGGER.error("findByPage pageReq {} fallback.", pageReq);
		BaseResp<PageResp<UserVO>> resp = new BaseResp<PageResp<UserVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> findByUserId(Long id) {
		LOGGER.error("findByUserId id {} fallback.", id);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<UserVO>> findByRoleId(Long roleId) {
		LOGGER.error("findByRoleId roleId {} fallback.", roleId);
		BaseResp<List<UserVO>> resp = new BaseResp<List<UserVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> addUser(UserVO userVO) {
		LOGGER.error("addUser userVO {} fallback.", userVO);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> updateUser(UserVO userVO) {
		LOGGER.error("updateUser userVO {} fallback.", userVO);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> deleteUser(UserVO userVO) {
		LOGGER.error("deleteUser {} fallback.", userVO);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> findByAccount(String account) {
		LOGGER.error("findByAccount account {} fallback.", account);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> lockUser(UserVO userVO) {
		LOGGER.error("lockUser {} fallback.", userVO);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> releaseLockUser(UserVO userVO) {
		LOGGER.error("releaseLockUser {} fallback.", userVO);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}
	
	@Override
	public BaseResp<Boolean> checkPassword(UserModifyPasswordVO userModifyPasswordVO) {
		LOGGER.error("checkPassword {} fallback.", userModifyPasswordVO);
		BaseResp<Boolean> resp = new BaseResp<>();
		resp.setData(false);
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> modifyPassword(UserModifyPasswordVO userModifyPasswordVO) {
		LOGGER.error("modifyPassword {} fallback.", userModifyPasswordVO);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
