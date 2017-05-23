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

/**
 * 权限Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = AuthorityFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface AuthorityFeignClient {
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_by_id/{id}")
	BaseResp<AuthorityVO> findByAuthorityId(@PathVariable("id") Long id); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_by_pid/{pid}")
	BaseResp<List<AuthorityVO>> findByAuthorityPid(@PathVariable("pid") Long pid); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/query_root")
	BaseResp<List<AuthorityVO>> findRootAuthority(); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/add")
	BaseResp<AuthorityVO> addAuthority(AuthorityVO authorityVO); 
	
	@RequestMapping(method = RequestMethod.POST, value = "/authority/update")
	BaseResp<AuthorityVO> updateAuthority(AuthorityVO authorityVO); 

	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/authority/delete_by_id/{id}")
	BaseResp deleteByAuthorityId(@PathVariable("id") Long id);
}
