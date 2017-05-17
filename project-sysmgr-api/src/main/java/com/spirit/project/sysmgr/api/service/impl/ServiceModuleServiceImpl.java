package com.spirit.project.sysmgr.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.google.common.collect.Lists;
import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritServiceTemplate;
import com.spirit.project.sysmgr.api.dto.req.ServiceModuleReqDTO;
import com.spirit.project.sysmgr.api.dto.resp.ServiceModuleRespDTO;
import com.spirit.project.sysmgr.api.service.ServiceModuleService;
import com.spirit.project.sysmgr.dao.dao.ServiceModuleDAO;
import com.spirit.project.sysmgr.dao.dao.specification.ServiceModuleSpecification;
import com.spirit.project.sysmgr.dao.po.ServiceModulePO;
import com.spirit.project.sysmgr.dao.po.UserPO;

@Service
@Transactional(readOnly = true)
public class ServiceModuleServiceImpl extends SpiritServiceTemplate<ServiceModuleReqDTO, ServiceModuleRespDTO, ServiceModulePO> implements ServiceModuleService {

	@Autowired
	private ServiceModuleDAO serviveModuleDAO;
	
	@Override
	public PageResp<ServiceModuleRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return super.findPage(pageReq);
	}

	@Override
	@Transactional
	public ServiceModuleRespDTO persist(ServiceModuleReqDTO reqDTO) throws SpiritAPIServiceException {
		return convertPoToRespDto(serviveModuleDAO.save(convertReqDtoToPo(reqDTO)));
	}

	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		serviveModuleDAO.delete(id);
	}

	@Override
	public ServiceModuleRespDTO findById(Long id) throws SpiritAPIServiceException {
		return convertPoToRespDto(serviveModuleDAO.findOne(id));
	}

	@Override
	public List<ServiceModuleRespDTO> findServiceModuleResps() throws SpiritAPIServiceException {
		List<ServiceModuleRespDTO> dtos = Lists.newLinkedList();
		List<ServiceModulePO> pos = serviveModuleDAO.findAll(new Sort(Direction.ASC, "name"));
		if(!CollectionUtils.isEmpty(pos)) {
			for (ServiceModulePO po : pos) {
				dtos.add(convertPoToRespDto(po));
			}
		}
		return dtos;
	}
	
	@Override
	protected Specification<ServiceModulePO> buildSpecification(Map<String, Object> filter) {
		return ServiceModuleSpecification.querySpecification(filter);
	}

	@Override
	protected ServiceModulePO convertReqDtoToPo(ServiceModuleReqDTO reqDTO) {
		ServiceModulePO serviveModulePO = new ServiceModulePO();
		if(reqDTO != null) {
			BeanUtils.copyProperties(reqDTO, serviveModulePO, "updateUser");
			serviveModulePO.setUpdateDate(DateUtils.currentDate());
			if(reqDTO.getUpdateUser() != null) {
				serviveModulePO.setUpdateUser(new UserPO(reqDTO.getUpdateUser()));
			}
		}
		return serviveModulePO;
	}
	
	@Override
	protected ServiceModuleRespDTO convertPoToRespDto(ServiceModulePO po) {
		ServiceModuleRespDTO serviceModuleRespDTO = new ServiceModuleRespDTO();
		if(po != null) {
			BeanUtils.copyProperties(po, serviceModuleRespDTO, "updateUser", "updateDate");
			if (po.getUpdateUser() != null) {
				serviceModuleRespDTO.setUpdateUserName(po.getUpdateUser().getName());
			}
			if (po.getUpdateDate() != null) {
				serviceModuleRespDTO.setUpdateDate(DateUtils.formatDateTime(po.getUpdateDate()));
			}
		}
		return serviceModuleRespDTO;
	}

	@Override
	public void delete(ServiceModuleReqDTO reqDTO) throws SpiritAPIServiceException {
	}

}
