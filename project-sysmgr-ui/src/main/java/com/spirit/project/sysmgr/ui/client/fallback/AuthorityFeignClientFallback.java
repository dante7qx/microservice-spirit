package com.spirit.project.sysmgr.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.sysmgr.ui.client.AuthorityFeignClient;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityVO;

@Component
public class AuthorityFeignClientFallback implements AuthorityFeignClient {

	private final static Logger logger = LoggerFactory.getLogger(AuthorityFeignClientFallback.class);

	@Override
	public BaseResp<AuthorityVO> findByAuthorityId(Long id) {
		logger.error("findByAuthorityId id {} fallback.", id);
		BaseResp<AuthorityVO> resp = new BaseResp<AuthorityVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<AuthorityVO>> findByAuthorityPid(Long pid) {
		logger.error("findByAuthorityId pid {} fallback.", pid);
		BaseResp<List<AuthorityVO>> resp = new BaseResp<List<AuthorityVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<AuthorityVO>> findRootAuthority() {
		logger.error("queryRootAuthority fallback.");
		BaseResp<List<AuthorityVO>> resp = new BaseResp<List<AuthorityVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<AuthorityVO> addAuthority(AuthorityVO authorityVO) {
		logger.error("addAuthority authorityVO {} fallback.", authorityVO);
		BaseResp<AuthorityVO> resp = new BaseResp<AuthorityVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<AuthorityVO> updateAuthority(AuthorityVO authorityVO) {
		logger.error("updateAuthority authorityVO {} fallback.", authorityVO);
		BaseResp<AuthorityVO> resp = new BaseResp<AuthorityVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> deleteByAuthorityId(Long id) {
		logger.error("deleteByAuthorityId id {} fallback.", id);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
