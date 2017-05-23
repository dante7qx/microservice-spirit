package com.spirit.project.sysmgr.api.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.spirit.project.commom.dao.exception.SpiritDaoException;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.sysmgr.api.dto.req.AuthorityReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.AuthorityRespDTO;
import com.spirit.project.sysmgr.api.service.AuthorityService;
import com.spirit.project.sysmgr.dao.dao.AuthorityDAO;
import com.spirit.project.sysmgr.dao.po.AuthorityPO;
import com.spirit.project.sysmgr.dao.po.UserPO;

@Service
@Transactional(readOnly = true)
public class AuthorityServiceImpl implements AuthorityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorityServiceImpl.class);

	@Autowired
	private AuthorityDAO authorityDAO;

	@Override
	public PageResp<AuthorityRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return null;
	}

	@Override
	public List<AuthorityRespDTO> findRootAuthority() throws SpiritAPIServiceException {
		List<AuthorityRespDTO> authorityRespDTOs = Lists.newArrayList();
		try {
			List<AuthorityPO> pos = authorityDAO.findRootAuthority();
			for (AuthorityPO po : pos) {
				authorityRespDTOs.add(convertPoToRespDto(po));
			}
		} catch (SpiritDaoException e) {
			LOGGER.error("AuthorityDAO findRootAuthority error.", e);
			throw new SpiritAPIServiceException("AuthorityDAO findRootAuthority error.", e);
		}
		return authorityRespDTOs;
	}

	@Override
	public List<AuthorityRespDTO> findByPid(Long pid) throws SpiritAPIServiceException {
		List<AuthorityRespDTO> authorityRespDTOs = Lists.newArrayList();
		try {
			List<AuthorityPO> pos = authorityDAO.findByParentId(pid);
			for (AuthorityPO po : pos) {
				authorityRespDTOs.add(convertPoToRespDto(po));
			}
		} catch (SpiritDaoException e) {
			LOGGER.error("AuthorityDAO findByPid {} error.", pid, e);
			throw new SpiritAPIServiceException("AuthorityDAO findByPid error.", e);
		}
		return authorityRespDTOs;
	}

	@Override
	@Transactional
	public AuthorityRespDTO persist(AuthorityReqDTO authorityReqDTO) throws SpiritAPIServiceException {
		return convertPoToRespDto(authorityDAO.save(convertReqDtoToPo(authorityReqDTO)));
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		List<AuthorityPO> authoritys = null;
		try {
			authoritys = authorityDAO.findByParentId(id);
		} catch (SpiritDaoException e) {
			LOGGER.error("AuthorityDAO findByParentId {} error.", id, e);
			throw new SpiritAPIServiceException("AuthorityDAO findByParentId error.", e);
		}

		if (!CollectionUtils.isEmpty(authoritys)) {
			authorityDAO.deleteInBatch(authoritys);
		}
		authorityDAO.delete(id);

	}

	@Override
	public AuthorityRespDTO findById(Long id) throws SpiritAPIServiceException {
		AuthorityPO authorityPO = authorityDAO.findOne(id);
		return convertPoToRespDto(authorityPO);
	}

	protected AuthorityPO convertReqDtoToPo(AuthorityReqDTO authorityReqDTO) {
		AuthorityPO authorityPO = new AuthorityPO();
		if (authorityReqDTO != null) {
			BeanUtils.copyProperties(authorityReqDTO, authorityPO, "updateUser");
			authorityPO.setUpdateDate(DateUtils.currentDate());
			if (authorityReqDTO.getUpdateUser() != null) {
				authorityPO.setUpdateUser(new UserPO(authorityReqDTO.getUpdateUser()));
			}
			if(authorityReqDTO.getPid() != null) {
				authorityPO.setParentAuthority(new AuthorityPO(authorityReqDTO.getPid()));
			}
		}
		return authorityPO;
	}

	protected AuthorityRespDTO convertPoToRespDto(AuthorityPO authorityPO) {
		AuthorityRespDTO authorityRespDTO = new AuthorityRespDTO();
		BeanUtils.copyProperties(authorityPO, authorityRespDTO);
		AuthorityPO parentAuthorityPO = authorityPO.getParentAuthority();
		if(parentAuthorityPO != null) {
			authorityRespDTO.setPid(parentAuthorityPO.getId());
		}
		return authorityRespDTO;
	}

	@Override
	public void delete(AuthorityReqDTO reqDTO) throws SpiritAPIServiceException {
	}

}
