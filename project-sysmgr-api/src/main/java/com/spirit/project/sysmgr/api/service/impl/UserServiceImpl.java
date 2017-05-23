package com.spirit.project.sysmgr.api.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.spirit.project.commom.util.EncryptUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritServiceTemplate;
import com.spirit.project.sysmgr.api.constant.UserConsts;
import com.spirit.project.sysmgr.api.dto.req.UserModifyPasswordReqDTO;
import com.spirit.project.sysmgr.api.dto.req.UserReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserAuthRespDTO;
import com.spirit.project.sysmgr.api.dto.resp.UserRespDTO;
import com.spirit.project.sysmgr.api.service.UserService;
import com.spirit.project.sysmgr.dao.dao.UserDAO;
import com.spirit.project.sysmgr.dao.dao.specification.UserSpecification;
import com.spirit.project.sysmgr.dao.po.AuthorityPO;
import com.spirit.project.sysmgr.dao.po.RolePO;
import com.spirit.project.sysmgr.dao.po.UserPO;

/**
 * 用户服务实现类
 * 
 * @author dante
 *
 */
@Service
@Transactional(readOnly = true)
public class UserServiceImpl extends SpiritServiceTemplate<UserReqDTO, UserRespDTO, UserPO> implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public PageResp<UserRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return super.findPage(pageReq);
	}

	@Override
	public UserRespDTO findByAccount(String account) throws SpiritAPIServiceException {
		try {
			UserPO userPO = userDAO.findByAccount(account);
			return convertPoToRespDto(userPO);
		} catch (SpiritDaoException e) {
			throw new SpiritAPIServiceException(e);
		}
	}

	@Override
	public UserAuthRespDTO loginAccount(String account) throws SpiritAPIServiceException {
		UserAuthRespDTO userAuthRespDTO = null;
		try {
			UserPO userPO = userDAO.findByAccount(account);
			userAuthRespDTO = new UserAuthRespDTO();
			BeanUtils.copyProperties(userPO, userAuthRespDTO);
			Set<RolePO> roles = userPO.getRoles();
			if (CollectionUtils.isEmpty(roles)) {
				return userAuthRespDTO;
			}
			for (RolePO rolePo : roles) {
				Set<AuthorityPO> authoritys = rolePo.getAuthoritys();
				if (!CollectionUtils.isEmpty(authoritys)) {
					for (AuthorityPO authorityPO : authoritys) {
						userAuthRespDTO.getAuthoritys().add(authorityPO.getCode());
					}
				}
			}
		} catch (SpiritDaoException e) {
			throw new SpiritAPIServiceException(e);
		}
		return userAuthRespDTO;
	}

	@Override
	public List<UserRespDTO> findByRoleId(Long roleId) throws SpiritAPIServiceException {
		List<UserRespDTO> userResps = Lists.newArrayList();
		List<UserPO> users = userDAO.findAll(UserSpecification.queryUserByRoleId(roleId));
		if (!CollectionUtils.isEmpty(users)) {
			for (UserPO userPO : users) {
				UserRespDTO userResp = new UserRespDTO();
				BeanUtils.copyProperties(userPO, userResp);
				userResps.add(userResp);
			}
		}
		return userResps;
	}

	@Override
	@Transactional
	public UserRespDTO persist(UserReqDTO userReqDTO) throws SpiritAPIServiceException {
		UserPO delUserPO = null;
		try {
			delUserPO = userDAO.findByAccount(userReqDTO.getAccount());
		} catch (SpiritDaoException e) {
			throw new SpiritAPIServiceException(e);
		}
		if (delUserPO != null && UserConsts.STATUS_DEL.equals(delUserPO.getStatus())) {
			return recoverDelUser(userReqDTO, delUserPO);
		}
		UserPO userPO = convertReqDtoToPo(userReqDTO);
		Long id = userPO.getId();
		if (id == null) {
			userPO.setPassword(EncryptUtils.encrypt(userReqDTO.getPassword()));
			userPO.setStatus(UserConsts.STATUS_NORMAL);
		} else {
			UserPO oldUserPo = userDAO.findOne(id);
			userPO.setPassword(oldUserPo.getPassword());
			userPO.setStatus(oldUserPo.getStatus());
		}
		userDAO.save(userPO);
		return convertPoToRespDto(userPO);
	}

	/**
	 * 恢复已删除的用户
	 * 
	 * @param userReqDTO
	 * @param delUserPO
	 * @return
	 */
	private UserRespDTO recoverDelUser(UserReqDTO userReqDTO, UserPO delUserPO) {
		Long id = delUserPO.getId();
		UserPO delUser = convertReqDtoToPo(userReqDTO);
		delUser.setId(id);
		delUser.setPassword(EncryptUtils.encrypt(userReqDTO.getPassword()));
		delUser.setStatus(UserConsts.STATUS_NORMAL);
		userDAO.save(delUser);
		return convertPoToRespDto(delUser);
	}

	@Override
	@Transactional
	public void delete(UserReqDTO userReqDTO) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(userReqDTO.getId());
		userPO.setStatus(UserConsts.STATUS_DEL);
		userPO.setUpdateDate(DateUtils.currentDate());
		userPO.setUpdateUser(new UserPO(userReqDTO.getUpdateUser()));
		userDAO.save(userPO);
		userDAO.deleteUserRoleByUserId(userReqDTO.getId());
	}

	@Override
	@Transactional
	public void lockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(userReqDTO.getId());
		userPO.setStatus(UserConsts.STATUS_LOCK);
		userPO.setUpdateDate(DateUtils.currentDate());
		userPO.setUpdateUser(new UserPO(userReqDTO.getUpdateUser()));
		userDAO.save(userPO);
	}

	@Override
	@Transactional
	public void releaseLockUser(UserReqDTO userReqDTO) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(userReqDTO.getId());
		userPO.setStatus(UserConsts.STATUS_NORMAL);
		userPO.setUpdateDate(DateUtils.currentDate());
		userPO.setUpdateUser(new UserPO(userReqDTO.getUpdateUser()));
		userDAO.save(userPO);
	}

	@Override
	public UserRespDTO findById(Long id) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(id);
		return convertPoToRespDto(userPO);
	}

	@Override
	public Boolean checkPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(userModifyPasswordReqDTO.getId());
		String oldPassword = userModifyPasswordReqDTO.getOldPassword();
		String dbUserPassword = userPO.getPassword();
		return EncryptUtils.match(oldPassword, dbUserPassword);
	}

	@Override
	@Transactional
	public void modifyPassword(UserModifyPasswordReqDTO userModifyPasswordReqDTO) throws SpiritAPIServiceException {
		UserPO userPO = userDAO.findOne(userModifyPasswordReqDTO.getId());
		String oldPassword = userModifyPasswordReqDTO.getOldPassword();
		String dbUserPassword = userPO.getPassword();
		if (!EncryptUtils.match(oldPassword, dbUserPassword)) {
			throw new SpiritAPIServiceException("原始密码错误");
		}
		String newPassword = userModifyPasswordReqDTO.getNewPassword();
		userPO.setPassword(EncryptUtils.encrypt(newPassword));
		if (userModifyPasswordReqDTO.getUpdateUser() != null) {
			userPO.setUpdateUser(new UserPO(userModifyPasswordReqDTO.getUpdateUser()));
		}
		userPO.setUpdateDate(DateUtils.currentDate());
	}

	@Override
	protected UserPO convertReqDtoToPo(UserReqDTO userReqDto) {
		UserPO userPO = new UserPO();
		BeanUtils.copyProperties(userReqDto, userPO, "updateUser");
		userPO.setUpdateDate(DateUtils.currentDate());
		if (userReqDto.getUpdateUser() != null) {
			userPO.setUpdateUser(new UserPO(userReqDto.getUpdateUser()));
		}
		Set<Long> roleIds = userReqDto.getRoleIds();
		if (!CollectionUtils.isEmpty(roleIds)) {
			Set<RolePO> roles = Sets.newHashSet();
			for (Long roleId : roleIds) {
				if (roleId < 0) {
					continue;
				}
				roles.add(new RolePO(roleId));
			}
			userPO.setRoles(roles);
		}
		return userPO;
	}

	@Override
	protected UserRespDTO convertPoToRespDto(UserPO userPO) {
		UserRespDTO userRespDTO = new UserRespDTO();
		if (userPO != null) {
			BeanUtils.copyProperties(userPO, userRespDTO, "updateUser", "updateDate");
			if (userPO.getUpdateUser() != null) {
				userRespDTO.setUpdateUserName(userPO.getUpdateUser().getName());
			}
			if (userPO.getUpdateDate() != null) {
				userRespDTO.setUpdateDate(DateUtils.formatDateTime(userPO.getUpdateDate()));
			}
			Set<RolePO> roles = userPO.getRoles();
			if (!CollectionUtils.isEmpty(roles)) {
				for (RolePO role : roles) {
					userRespDTO.getRoleIds().add(role.getId());
				}
			}
		}
		return userRespDTO;
	}

	@Override
	protected Specification<UserPO> buildSpecification(Map<String, Object> filter) {
		return UserSpecification.querySpecification(filter);
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		// 物理删除用户，用户采用逻辑删除，本方法不做实现
	}

}
