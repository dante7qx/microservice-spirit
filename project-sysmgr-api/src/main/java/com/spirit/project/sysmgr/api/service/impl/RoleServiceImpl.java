package com.spirit.project.sysmgr.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritServiceTemplate;
import com.spirit.project.sysmgr.api.dto.req.RoleReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.AuthorityRoleRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.RoleRespDTO;
import com.spirit.project.sysmgr.api.service.RoleService;
import com.spirit.project.sysmgr.dao.bo.AuthorityRoleBO;
import com.spirit.project.sysmgr.dao.dao.RoleDAO;
import com.spirit.project.sysmgr.dao.dao.specification.RoleSpecification;
import com.spirit.project.sysmgr.dao.mapper.AuthorityMapper;
import com.spirit.project.sysmgr.dao.po.AuthorityPO;
import com.spirit.project.sysmgr.dao.po.RolePO;
import com.spirit.project.sysmgr.dao.po.UserPO;

/**
 * 角色服务实现类
 * 
 * @author dante
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends SpiritServiceTemplate<RoleReqDTO, RoleRespDTO, RolePO> implements RoleService {

	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public PageResp<RoleRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return super.findPage(pageReq);
	}

	@Override
	@Transactional
	public RoleRespDTO persist(RoleReqDTO roleReqDTO) throws SpiritAPIServiceException {
		RolePO rolePO = roleDAO.save(convertReqDtoToPo(roleReqDTO));
		return convertPoToRespDto(rolePO);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		roleDAO.delete(id);
	}

	@Override
	public RoleRespDTO findById(Long id) throws SpiritAPIServiceException {
		return convertPoToRespDto(roleDAO.findOne(id));
	}

	@Override
	public List<AuthorityRoleRespDTO> findAuthorityRoleByRoleId(Long roleId) throws SpiritAPIServiceException {
		List<AuthorityRoleRespDTO> authorityRoleRespDtos = Lists.newArrayList();
		try {
//			List<AuthorityRoleBO> authorityRoleBOs = JpaEntityConvertUtils.castEntity(authorityDAO.findAuthorityRoleByRoleId(roleId), AuthorityRoleBO.class);
			List<AuthorityRoleBO> authorityRoleBOs = authorityMapper.findAuthorityRoleByRoleId(roleId);
			if (!CollectionUtils.isEmpty(authorityRoleBOs)) {
				for (AuthorityRoleBO authorityRoleBO : authorityRoleBOs) {
					AuthorityRoleRespDTO authorityRoleRespDTO = new AuthorityRoleRespDTO();
					BeanUtils.copyProperties(authorityRoleBO, authorityRoleRespDTO);
					authorityRoleRespDtos.add(authorityRoleRespDTO);
				}
			}
		} catch (SpiritDaoException e) {
			throw new SpiritAPIServiceException(e);
		}
		return authorityRoleRespDtos;
	}

	@Override
	public List<RoleRespDTO> findAllRoles() throws SpiritAPIServiceException {
		List<RoleRespDTO> roleRespDTOs = Lists.newLinkedList();
		List<RolePO> rolePOs = roleDAO.findAll(new Sort(Direction.ASC, "name"));
		if (!CollectionUtils.isEmpty(rolePOs)) {
			for (RolePO rolePO : rolePOs) {
				roleRespDTOs.add(convertPoToRespDto(rolePO));
			}
		}
		return roleRespDTOs;
	}

	@Override
	protected RolePO convertReqDtoToPo(RoleReqDTO roleReqDTO) {
		RolePO rolePO = new RolePO();
		if (roleReqDTO != null) {
			BeanUtils.copyProperties(roleReqDTO, rolePO, "updateUser");
			rolePO.setUpdateDate(DateUtils.currentDate());
			if (roleReqDTO.getUpdateUser() != null) {
				rolePO.setUpdateUser(new UserPO(roleReqDTO.getUpdateUser()));
			}
			Set<Long> authorityIds = roleReqDTO.getAuthorityIds();
			if (!CollectionUtils.isEmpty(authorityIds)) {
				Set<AuthorityPO> authorityPOs = Sets.newHashSet();
				for (Long authorityId : authorityIds) {
					authorityPOs.add(new AuthorityPO(authorityId));
				}
				rolePO.setAuthoritys(authorityPOs);
			}
		}
		return rolePO;
	}

	@Override
	protected RoleRespDTO convertPoToRespDto(RolePO rolePO) {
		RoleRespDTO roleRespDTO = new RoleRespDTO();
		if (rolePO != null) {
			BeanUtils.copyProperties(rolePO, roleRespDTO, "updateUser", "updateDate");
			if (rolePO.getUpdateUser() != null) {
				roleRespDTO.setUpdateUserName(rolePO.getUpdateUser().getName());
			}
			if (rolePO.getUpdateDate() != null) {
				roleRespDTO.setUpdateDate(DateUtils.formatDateTime(rolePO.getUpdateDate()));
			}
			Set<AuthorityPO> authoritys = rolePO.getAuthoritys();
			if (!CollectionUtils.isEmpty(authoritys)) {
				for (AuthorityPO authority : authoritys) {
					roleRespDTO.getAuthorityIds().add(authority.getId());
				}
			}
		}
		return roleRespDTO;
	}

	@Override
	protected Specification<RolePO> buildSpecification(Map<String, Object> filter) {
		return RoleSpecification.querySpecification(filter);
	}

	@Override
	public void delete(RoleReqDTO reqDTO) throws SpiritAPIServiceException {
		// 逻辑删除，此功能使用物理删除，故本方法不做实现
	}
}
