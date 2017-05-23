package com.spirit.project.sysmgr.ui.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.BaseResp;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.dto.resp.RespCodeEnum;
import com.spirit.project.common.ui.dto.resp.PageResult;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.client.RoleFeignClient;
import com.spirit.project.sysmgr.ui.service.RoleService;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.AuthorityRoleVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleTreeVO;
import com.spirit.project.sysmgr.ui.vo.role.RoleVO;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleFeignClient roleFeignClient;
	
	@Override
	@HystrixCommand
	public PageResult<RoleVO> findPage(PageReq pageReq) throws SpiritUIServiceException {
		PageResult<RoleVO> pageResult = new PageResult<>();
		BaseResp<PageResp<RoleVO>> resp = roleFeignClient.findPage(pageReq);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		PageResp<RoleVO> pageResp = resp.getData();
		pageResult.setRows(pageResp.getResult());
		pageResult.setTotal(pageResp.getTotalCount());
		return pageResult;
	}

	@Override
	@HystrixCommand
	public RoleVO findByRoleId(Long id) throws SpiritUIServiceException {
		BaseResp<RoleVO> resp = roleFeignClient.findByRoleId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public void deleteByRoleId(Long id) throws SpiritUIServiceException {
		BaseResp<?> resp = roleFeignClient.deleteByRoleId(id);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
	}

	@Override
	@HystrixCommand
	public RoleVO updateRole(RoleVO roleVO) throws SpiritUIServiceException {
		Long id = roleVO.getId();
		BaseResp<RoleVO> resp;
		if (id == null) {
			resp = roleFeignClient.addRole(roleVO);
		} else {
			resp = roleFeignClient.updateRole(roleVO);
		}
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		return resp.getData();
	}

	@Override
	@HystrixCommand
	public List<AuthorityRoleTreeVO> findAuthoritysByRoleId(Long roleId) throws SpiritUIServiceException {
		BaseResp<List<AuthorityRoleVO>> resp = roleFeignClient.findAuthorityRoleByRoleId(roleId);
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<AuthorityRoleTreeVO> authRoleTrees = Lists.newArrayList();
		List<AuthorityRoleVO> authorityRoles = resp.getData();
		Map<String, AuthorityRoleTreeVO> treeMap = Maps.newLinkedHashMap();
		for (AuthorityRoleVO authorityRole : authorityRoles) {
			AuthorityRoleTreeVO roleAuthTree = new AuthorityRoleTreeVO(authorityRole);
			treeMap.put("_"+authorityRole.getId(), roleAuthTree);
		}
		
		Set<String> keySet = treeMap.keySet();
		Iterator<String> iterNode = keySet.iterator();
		while(iterNode.hasNext()) {
			String key = (String) iterNode.next();
			AuthorityRoleTreeVO tempTree = treeMap.get(key);
			Long pid = tempTree.getPid();
			if(pid == null) {
				authRoleTrees.add(tempTree);
			} else {
				Set<String> childKeySet = treeMap.keySet();
				Iterator<String> iterChild = childKeySet.iterator();
				while(iterChild.hasNext()) {
					String childKey = (String) iterChild.next();
					AuthorityRoleTreeVO sameTree = treeMap.get(childKey);
					if(childKey.equals("_"+pid)) {
						if(CollectionUtils.isEmpty(sameTree.getChildren())) {
							sameTree.setChildren(Lists.newArrayList(tempTree));
						} else {
							sameTree.getChildren().add(tempTree);
						}
					}
				}
			}
		}
		return authRoleTrees;
	}

	@Override
	@HystrixCommand
	public List<RoleTreeVO> findRoleTree() throws SpiritUIServiceException {
		BaseResp<List<RoleVO>> resp = roleFeignClient.findAllRole();
		if (resp.getResultCode() != RespCodeEnum.SUCCESS.code()) {
			throw new SpiritUIServiceException(resp.getResultCode() + "");
		}
		List<RoleTreeVO> roleTreeVOs = Lists.newLinkedList();
		List<RoleVO> roleVOs = resp.getData();
		if(!CollectionUtils.isEmpty(roleVOs)) {
			for (RoleVO roleVO : roleVOs) {
				RoleTreeVO roleTreeVO = new RoleTreeVO();
				roleTreeVO.setId(roleVO.getId());
				roleTreeVO.setText(roleVO.getName());
				roleTreeVOs.add(roleTreeVO);
			}
		}
		return roleTreeVOs;
	}

}
