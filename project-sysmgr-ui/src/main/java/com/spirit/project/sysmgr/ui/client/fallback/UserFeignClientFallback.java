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
	private final static Logger logger = LoggerFactory.getLogger(UserFeignClientFallback.class);

	@Override
	public BaseResp<PageResp<UserVO>> findPage(PageReq pageReq) {
		logger.error("findByPage pageReq {} fallback.", pageReq);
		BaseResp<PageResp<UserVO>> resp = new BaseResp<PageResp<UserVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> findByUserId(Long id) {
		logger.error("findByUserId id {} fallback.", id);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<UserVO>> findByRoleId(Long roleId) {
		logger.error("findByRoleId roleId {} fallback.", roleId);
		BaseResp<List<UserVO>> resp = new BaseResp<List<UserVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> addUser(UserVO userVO) {
		logger.error("addUser userVO {} fallback.", userVO);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> updateUser(UserVO userVO) {
		logger.error("updateUser userVO {} fallback.", userVO);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> deleteUser(UserVO userVO) {
		logger.error("deleteUser {} fallback.", userVO);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<UserVO> findByAccount(String account) {
		logger.error("findByAccount account {} fallback.", account);
		BaseResp<UserVO> resp = new BaseResp<UserVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> lockUser(UserVO userVO) {
		logger.error("lockUser {} fallback.", userVO);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> releaseLockUser(UserVO userVO) {
		logger.error("releaseLockUser {} fallback.", userVO);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}
	
	@Override
	public BaseResp<Boolean> checkPassword(UserModifyPasswordVO userModifyPasswordVO) {
		logger.error("checkPassword {} fallback.", userModifyPasswordVO);
		BaseResp<Boolean> resp = new BaseResp<>();
		resp.setData(false);
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> modifyPassword(UserModifyPasswordVO userModifyPasswordVO) {
		logger.error("modifyPassword {} fallback.", userModifyPasswordVO);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
