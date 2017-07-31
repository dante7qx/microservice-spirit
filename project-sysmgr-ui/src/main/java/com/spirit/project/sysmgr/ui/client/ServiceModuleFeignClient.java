package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.feignconfig.FeignClientConfig;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.sysmgr.ui.client.fallback.ServiceModuleFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

/**
 * 服务模块Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = ServiceModuleFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface ServiceModuleFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_page")
	BaseResp<PageResp<ServiceModuleVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_by_id/{id}")
	BaseResp<ServiceModuleVO> findByServiceModuleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_all")
	BaseResp<List<ServiceModuleVO>> findAllServiceModule();
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/add")
	BaseResp<ServiceModuleVO> addServiceModule(ServiceModuleVO serviceModuleVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/update")
	BaseResp<ServiceModuleVO> updateServiceModule(ServiceModuleVO serviceModuleVO);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/servicemodule/delete_by_id/{id}")
	BaseResp deleteByServiceModuleId(@PathVariable("id") Long id);

}
