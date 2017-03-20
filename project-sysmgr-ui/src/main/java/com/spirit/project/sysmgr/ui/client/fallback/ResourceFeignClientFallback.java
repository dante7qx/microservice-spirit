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

	private final static Logger logger = LoggerFactory.getLogger(ResourceFeignClientFallback.class);
	
	@Override
	public BaseResp<ResourceVO> findByResourceId(Long id) {
		logger.error("findByResourceId id {} fallback.", id);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<ResourceVO>> findByResourcePid(Long pid) {
		logger.error("findByResourcePid pid {} fallback.", pid);
		BaseResp<List<ResourceVO>> resp = new BaseResp<List<ResourceVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<List<ResourceVO>> findRootResource() {
		logger.error("findRootResource fallback.");
		BaseResp<List<ResourceVO>> resp = new BaseResp<List<ResourceVO>>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ResourceVO> addResource(ResourceVO resourceVO) {
		logger.error("addResource resourceVO {} fallback.", resourceVO);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<ResourceVO> updateResource(ResourceVO resourceVO) {
		logger.error("updateResource resourceVO {} fallback.", resourceVO);
		BaseResp<ResourceVO> resp = new BaseResp<ResourceVO>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

	@Override
	public BaseResp<?> deleteByResourceId(Long id) {
		logger.error("deleteByResourceId id {} fallback.", id);
		BaseResp<?> resp = new BaseResp<>();
		resp.setResultCode(RespCodeEnum.REMOTE_FAILURE.code());
		return resp;
	}

}
