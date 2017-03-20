package com.spirit.project.sysmgr.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.dto.req.ResourceReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ResourceRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserResourceRespDTO;
import com.spirit.project.sysmgr.api.service.ResourceService;
import com.spirit.project.sysmgr.dao.dao.ResourceDAO;
import com.spirit.project.sysmgr.dao.dao.specification.ResourceSpecification;
import com.spirit.project.sysmgr.dao.po.AuthorityPO;
import com.spirit.project.sysmgr.dao.po.ResourcePO;
import com.spirit.project.sysmgr.dao.po.ServiceModulePO;
import com.spirit.project.sysmgr.dao.po.UserPO;

@Service
@Transactional(readOnly = true)
public class ResourceServiceImpl implements ResourceService {

	private final static Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

	@Autowired
	private ResourceDAO resourceDAO;

	@Override
	public PageResp<ResourceRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return null;
	}

	@Override
	public List<ResourceRespDTO> findRootResource() throws SpiritAPIServiceException {
		List<ResourceRespDTO> resourceRespDTOs = Lists.newArrayList();
		try {
			List<ResourcePO> pos = resourceDAO.findRootResource();
			for (ResourcePO po : pos) {
				resourceRespDTOs.add(convertPoToRespDto(po));
			}
		} catch (SpiritDaoException e) {
			logger.error("ResourceDAO findRootResource error.", e);
			throw new SpiritAPIServiceException("ResourceDAO findRootResource error.", e);
		}
		return resourceRespDTOs;
	}

	@Override
	public List<ResourceRespDTO> findByPid(Long pid) throws SpiritAPIServiceException {
		List<ResourceRespDTO> resourceRespDTOs = Lists.newArrayList();
		try {
			List<ResourcePO> pos = resourceDAO.findByPid(pid);
			for (ResourcePO po : pos) {
				resourceRespDTOs.add(convertPoToRespDto(po));
			}
		} catch (SpiritDaoException e) {
			logger.error("ResourceDAO findByPid {} error.", pid, e);
			throw new SpiritAPIServiceException("ResourceDAO findByPid error.", e);
		}
		return resourceRespDTOs;
	}

	@Override
	@Transactional
	public ResourceRespDTO persist(ResourceReqDTO resourceReqDTO) throws SpiritAPIServiceException {
		ResourcePO resourcePO = convertReqDtoToPo(resourceReqDTO);
		resourceDAO.save(resourcePO);
		StringBuilder fullIdBuilder = new StringBuilder(resourcePO.getId() + "");
		this.buildFullId(resourceReqDTO.getPid(), fullIdBuilder);
		resourcePO.setFullId(fullIdBuilder.toString());
		resourceDAO.save(resourcePO);
		return convertPoToRespDto(resourcePO);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		List<ResourcePO> resources = null;
		try {
			resources = resourceDAO.findByPid(id);
		} catch (SpiritDaoException e) {
			logger.error("ResourceDAO findByParentId {} error.", id, e);
			throw new SpiritAPIServiceException("ResourceDAO findByParentId error.", e);
		}
		if (!CollectionUtils.isEmpty(resources)) {
			resourceDAO.deleteInBatch(resources);
		}
		resourceDAO.delete(id);
	}

	private void buildFullId(Long pid, StringBuilder fullIdBuilder) {
		if (pid == null || pid != null && pid < 0) {
			return;
		}
		fullIdBuilder.append("-").append(pid);
		ResourcePO parentResource = resourceDAO.findOne(pid);
		if (parentResource.getParentResource() != null) {
			buildFullId(parentResource.getParentResource().getId(), fullIdBuilder);
		}
	}

	@Override
	public ResourceRespDTO findById(Long id) throws SpiritAPIServiceException {
		return convertPoToRespDto(resourceDAO.findOne(id));
	}

	@Override
	public List<UserResourceRespDTO> findUserResourceByUserId(Long userId) throws SpiritAPIServiceException {
		List<UserResourceRespDTO> userResources = Lists.newLinkedList();
		List<Long> pids = Lists.newArrayList();
		try {
			pids = resourceDAO.findAllParentId();
		} catch (SpiritDaoException e) {
			logger.error("ResourceDAO findAllParentId {} error.", e);
			throw new SpiritAPIServiceException("ResourceDAO findAllParentId error.", e);
		}
		List<ResourcePO> resourcePOs = resourceDAO.findAll(ResourceSpecification.findResourceTreeByUserId(userId));

		Map<Long, UserResourceRespDTO> menuTreeMap = Maps.newLinkedHashMap();
		for (ResourcePO resource : resourcePOs) {
			menuTreeMap.put(resource.getId(), convertPoToUserResourceRespDTO(resource));
		}
		List<UserResourceRespDTO> menus = buildUserResourceTree(pids, menuTreeMap);
		for (UserResourceRespDTO menu : menus) {
			if (!menu.getChildren().isEmpty() || !pids.contains(menu.getPid())) {
				userResources.add(menu);
			}
		}
		return userResources;
	}

	protected ResourcePO convertReqDtoToPo(ResourceReqDTO resourceReqDTO) {
		ResourcePO resourcePO = new ResourcePO();
		if (resourceReqDTO != null) {
			Long pid = resourceReqDTO.getPid();
			BeanUtils.copyProperties(resourceReqDTO, resourcePO, "updateUser");
			resourcePO.setUpdateDate(DateUtils.currentDate());
			if (pid != null && pid > 0) {
				resourcePO.setParentResource(new ResourcePO(pid));
			}
			if (resourceReqDTO.getServiceModuleId() != null) {
				resourcePO.setServiceModule(new ServiceModulePO(resourceReqDTO.getServiceModuleId()));
			}
			if (resourceReqDTO.getAuthorityId() != null) {
				resourcePO.setAuthority(new AuthorityPO(resourceReqDTO.getAuthorityId()));
			}
			if (resourceReqDTO.getUpdateUser() != null) {
				resourcePO.setUpdateUser(new UserPO(resourceReqDTO.getUpdateUser()));
			}
		}
		return resourcePO;
	}

	protected ResourceRespDTO convertPoToRespDto(ResourcePO resourcePO) {
		ResourceRespDTO resourceRespDTO = new ResourceRespDTO();
		BeanUtils.copyProperties(resourcePO, resourceRespDTO);
		resourceRespDTO.setServiceModuleId(resourcePO.getServiceModule().getId());
		resourceRespDTO.setAuthorityId(resourcePO.getAuthority().getId());
		ResourcePO parentResourcePO = resourcePO.getParentResource();
		if (parentResourcePO != null) {
			resourceRespDTO.setPid(parentResourcePO.getId());
		}
		ServiceModulePO serviceModulePO = resourcePO.getServiceModule();
		if(serviceModulePO != null) {
			resourceRespDTO.setServiceModuleId(serviceModulePO.getId());
			resourceRespDTO.setServiceModuleName(serviceModulePO.getName());
			resourceRespDTO.setServiceModuleUrl(serviceModulePO.getUrl());
		}
		return resourceRespDTO;
	}

	protected UserResourceRespDTO convertPoToUserResourceRespDTO(ResourcePO resourcePO) {
		UserResourceRespDTO userResourceRespDTO = new UserResourceRespDTO();
		StringBuilder urlBuilder = new StringBuilder();
		BeanUtils.copyProperties(resourcePO, userResourceRespDTO);
		ServiceModulePO serviveModulePO = resourcePO.getServiceModule();
		if (serviveModulePO != null) {
			urlBuilder.append(serviveModulePO.getUrl()).append("/").append(userResourceRespDTO.getUrl());
			userResourceRespDTO.setUrl(urlBuilder.toString());
		}
		ResourcePO parentResourcePO = resourcePO.getParentResource();
		if (parentResourcePO != null) {
			userResourceRespDTO.setPid(parentResourcePO.getId());
		}
		return userResourceRespDTO;
	}

	private List<UserResourceRespDTO> buildUserResourceTree(List<Long> pids,
			Map<Long, UserResourceRespDTO> menuTreeMap) {
		List<UserResourceRespDTO> menus = Lists.newLinkedList();
		Set<Long> keySet = menuTreeMap.keySet();
		for (Long key : keySet) {
			UserResourceRespDTO menu = menuTreeMap.get(key);
			Long parentId = menu.getPid();
			if (parentId == null) {
				menus.add(menu);
			} else {
				UserResourceRespDTO m = menuTreeMap.get(parentId);
				if (m != null) {
					m.getChildren().add(menu);
				}
			}
		}

		if (!CollectionUtils.isEmpty(pids)) {
			for (Long pid : pids) {
				removeEmptyMenu(pid, menuTreeMap);
			}
		}
		return menus;
	}

	private void removeEmptyMenu(Long pid, Map<Long, UserResourceRespDTO> menuTreeMap) {
		UserResourceRespDTO menu = menuTreeMap.get(pid);
		if (menu == null || !CollectionUtils.isEmpty(menu.getChildren())) {
			return;
		}
		Long parentId = menu.getPid();
		UserResourceRespDTO subMenu = menuTreeMap.get(parentId);
		if (subMenu != null) {
			subMenu.getChildren().remove(menu);
			removeEmptyMenu(subMenu.getId(), menuTreeMap);
		}
	}

	@Override
	public void delete(ResourceReqDTO reqDTO) throws SpiritAPIServiceException {
	}

}
