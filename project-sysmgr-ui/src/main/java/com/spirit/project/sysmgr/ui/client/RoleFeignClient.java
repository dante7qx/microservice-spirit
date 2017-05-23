package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.feignconfig.FeignClientConfig;
import com.spirit.project.sysmgr.ui.client.fallback.RoleFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

/**
 * 角色Feign Client
 * 
 * @author dante
 *
 */
@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = RoleFeignClientFallback.class, configuration=FeignClientConfig.class)
public interface RoleFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/role/query_page")
	BaseResp<PageResp<RoleVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_by_id/{id}")
	BaseResp<RoleVO> findByRoleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_all_role")
	BaseResp<List<RoleVO>> findAllRole();
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_authority_role_by_id/{id}")
	BaseResp<List<AuthorityRoleVO>> findAuthorityRoleByRoleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/add")
	BaseResp<RoleVO> addRole(RoleVO roleVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/update")
	BaseResp<RoleVO> updateRole(RoleVO roleVO);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(method = RequestMethod.DELETE, value = "/role/delete_by_id/{id}")
	BaseResp deleteByRoleId(@PathVariable("id") Long id);
}
