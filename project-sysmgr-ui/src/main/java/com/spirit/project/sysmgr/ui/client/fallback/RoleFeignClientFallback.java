package com.spirit.project.sysmgr.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.sysmgr.ui.client.RoleFeignClient;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

@Component
public class RoleFeignClientFallback implements RoleFeignClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleFeignClientFallback.class);

	@Override
	public BaseResp<PageResp<RoleVO>> findPage(PageReq pageReq) {
		LOGGER.error("findByPage pageReq {} fallback.", pageReq);
		BaseResp<PageResp<RoleVO>> resp = new BaseResp<PageResp<RoleVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<RoleVO> findByRoleId(Long id) {
		LOGGER.error("findByRoleId id {} fallback.", id);
		BaseResp<RoleVO> resp = new BaseResp<RoleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<RoleVO>> findAllRole() {
		LOGGER.error("findAllRole fallback.");
		BaseResp<List<RoleVO>> resp = new BaseResp<List<RoleVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<RoleVO> addRole(RoleVO roleVO) {
		LOGGER.error("addRole roleVO {} fallback.", roleVO);
		BaseResp<RoleVO> resp = new BaseResp<RoleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<RoleVO> updateRole(RoleVO roleVO) {
		LOGGER.error("updateRole roleVO {} fallback.", roleVO);
		BaseResp<RoleVO> resp = new BaseResp<RoleVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> deleteByRoleId(Long id) {
		LOGGER.error("deleteByRoleId id {} fallback.", id);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<AuthorityRoleVO>> findAuthorityRoleByRoleId(Long id) {
		LOGGER.error("findAuthorityRoleByRoleId id {} fallback.", id);
		BaseResp<List<AuthorityRoleVO>> resp = new BaseResp<List<AuthorityRoleVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
