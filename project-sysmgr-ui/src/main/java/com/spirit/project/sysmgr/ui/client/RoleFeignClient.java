package com.spirit.project.sysmgr.ui.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.sysmgr.ui.client.fallback.RoleFeignClientFallback;
import com.spirit.project.sysmgr.ui.constant.SpiritServiceConsts;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

@FeignClient(name = SpiritServiceConsts.PROJECT_SYSMGR_SERVER_NAME, fallback = RoleFeignClientFallback.class)
public interface RoleFeignClient {

	@RequestMapping(method = RequestMethod.POST, value = "/role/query_page")
	public BaseResp<PageResp<RoleVO>> findPage(PageReq pageReq);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_by_id/{id}")
	public BaseResp<RoleVO> findByRoleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_all_role")
	public BaseResp<List<RoleVO>> findAllRole();
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/query_authority_role_by_id/{id}")
	public BaseResp<List<AuthorityRoleVO>> findAuthorityRoleByRoleId(@PathVariable("id") Long id);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/add")
	public BaseResp<RoleVO> addRole(RoleVO roleVO);
	
	@RequestMapping(method = RequestMethod.POST, value = "/role/update")
	public BaseResp<RoleVO> updateRole(RoleVO roleVO);
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/role/delete_by_id/{id}")
	public BaseResp<?> deleteByRoleId(@PathVariable("id") Long id);
}
