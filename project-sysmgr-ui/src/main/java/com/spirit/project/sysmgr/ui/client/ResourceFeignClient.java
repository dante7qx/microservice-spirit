package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.sysmgr.ui.client.fallback.ResourceFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.resource.ResourceVO;

/**
 * 资源Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = ResourceFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface ResourceFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/resource/query_by_id/{id}")
	BaseResp<ResourceVO> findByResourceId(@PathVariable("id") Long id);

	@RequestMapping(method = RequestMethod.POST, value = "/resource/query_by_pid/{pid}")
	BaseResp<List<ResourceVO>> findByResourcePid(@PathVariable("pid") Long pid);

	@RequestMapping(method = RequestMethod.POST, value = "/resource/query_root")
	BaseResp<List<ResourceVO>> findRootResource();

	@RequestMapping(method = RequestMethod.POST, value = "/resource/add")
	BaseResp<ResourceVO> addResource(ResourceVO resourceVO);

	@RequestMapping(method = RequestMethod.POST, value = "/resource/update")
	BaseResp<ResourceVO> updateResource(ResourceVO resourceVO);

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/resource/delete_by_id/{id}")
	BaseResp deleteByResourceId(@PathVariable("id") Long id);

}
