package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.feignconfig.FeignClientConfig;
import com.spirit.project.sysmgr.ui.client.fallback.AuthorityFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = AuthorityFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface AuthorityFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_by_id/{id}")
	public BaseResp<AuthorityVO> findByAuthorityId(@PathVariable("id") Long id); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_by_pid/{pid}")
	public BaseResp<List<AuthorityVO>> findByAuthorityPid(@PathVariable("pid") Long pid); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_root")
	public BaseResp<List<AuthorityVO>> findRootAuthority(); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/add")
	public BaseResp<AuthorityVO> addAuthority(AuthorityVO authorityVO); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/update")
	public BaseResp<AuthorityVO> updateAuthority(AuthorityVO authorityVO); 

	@RequestMapping(method = RequestMethod.DELETE, value = "/authority/delete_by_id/{id}")
	public BaseResp<? extends Object> deleteByAuthorityId(@PathVariable("id") Long id);
}
