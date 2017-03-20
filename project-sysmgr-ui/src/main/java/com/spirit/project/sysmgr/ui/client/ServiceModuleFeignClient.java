package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.sysmgr.ui.client.fallback.ServiceModuleFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.servicemodule.ServiceModuleVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = ServiceModuleFeignClientFallback.class)
public interface ServiceModuleFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_page")
	public BaseResp<PageResp<ServiceModuleVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_by_id/{id}")
	public BaseResp<ServiceModuleVO> findByServiceModuleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/query_all")
	public BaseResp<List<ServiceModuleVO>> findAllServiceModule();
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/add")
	public BaseResp<ServiceModuleVO> addServiceModule(ServiceModuleVO serviceModuleVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/servicemodule/update")
	public BaseResp<ServiceModuleVO> updateServiceModule(ServiceModuleVO serviceModuleVO);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/servicemodule/delete_by_id/{id}")
	public BaseResp<?> deleteByServiceModuleId(@PathVariable("id") Long id);

}
