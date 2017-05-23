package com.spirit.project.sysmgr.ui.client.fallback;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.sysmgr.ui.client.ResourceFeignClient;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceVO;

@Component
public class ResourceFeignClientFallback implements ResourceFeignClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ResourceFeignClientFallback.class);
	
	@Override
	public BaseResp<ResourceVO> findByResourceId(Long id) {
		LOGGER.error("findByResourceId id {} fallback.", id);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<ResourceVO>> findByResourcePid(Long pid) {
		LOGGER.error("findByResourcePid pid {} fallback.", pid);
		BaseResp<List<ResourceVO>> resp = new BaseResp<List<ResourceVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<ResourceVO>> findRootResource() {
		LOGGER.error("findRootResource fallback.");
		BaseResp<List<ResourceVO>> resp = new BaseResp<List<ResourceVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ResourceVO> addResource(ResourceVO resourceVO) {
		LOGGER.error("addResource resourceVO {} fallback.", resourceVO);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ResourceVO> updateResource(ResourceVO resourceVO) {
		LOGGER.error("updateResource resourceVO {} fallback.", resourceVO);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<? extends Object> deleteByResourceId(Long id) {
		LOGGER.error("deleteByResourceId id {} fallback.", id);
		BaseResp<? extends Object> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
