package com.spirit.project.sysmgr.ui.service;

import java.util.List;

import com.spirit.project.common.ui.dto.req.EasyUIDragTreeReq;
import com.spirit.project.common.ui.exception.SpiritUIServiceException;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityTreeVO;
import com.spirit.project.sysmgr.ui.vo.authority.AuthorityVO;

public interface AuthorityService {
	
	public AuthorityVO findByAuthorityId(Long id) throws SpiritUIServiceException;
	
	public List<AuthorityTreeVO> findAuthorityTrees() throws SpiritUIServiceException;
	
	public void deleteByAuthorityId(Long id) throws SpiritUIServiceException;
	
	public AuthorityVO updateAuthority(AuthorityVO authorityVO) throws SpiritUIServiceException;
	
	public void updateAuthorityWhenDrag(EasyUIDragTreeReq dragTreeReq) throws SpiritUIServiceException;
	
	
}
