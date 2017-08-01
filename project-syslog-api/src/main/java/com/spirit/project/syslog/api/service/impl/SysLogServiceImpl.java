package com.spirit.project.syslog.api.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.commom.util.DateUtils;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;
import com.spirit.project.common.api.template.SpiritServiceTemplate;
import com.spirit.project.syslog.api.dto.req.SysLogReqDTO;
import com.spirit.project.syslog.api.dto.resp.SysLogRespDTO;
import com.spirit.project.syslog.api.service.SysLogService;
import com.spirit.project.syslog.dao.dao.SysLogDAO;
import com.spirit.project.syslog.dao.dao.specification.SysLogSpecification;
import com.spirit.project.syslog.dao.po.SysLogPO;

/**
 * 系统日志服务实现类
 * 
 * @author dante
 *
 */
@Service
@Transactional(readOnly = true)
public class SysLogServiceImpl extends SpiritServiceTemplate<SysLogReqDTO, SysLogRespDTO, SysLogPO> implements SysLogService {

	@Autowired
	private SysLogDAO sysLogDAO;
	
	/**
	 * 系统日志分页
	 */
	@Override
	public PageResp<SysLogRespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException {
		return super.findPage(pageReq);
	}

	/**
	 * 持久化系统日志
	 */
	@Override
	@Transactional
	public SysLogRespDTO persist(SysLogReqDTO reqDTO) throws SpiritAPIServiceException {
		return convertPoToRespDto(sysLogDAO.save(convertReqDtoToPo(reqDTO)));
	}

	/**
	 * 物理删除系统日志
	 */
	@Override
	@Transactional
	public void deleteById(Long id) throws SpiritAPIServiceException {
		// 系统日志无删除功能
	}

	/**
	 * 逻辑删除系统日志
	 */
	@Override
	@Transactional
	public void delete(SysLogReqDTO reqDTO) throws SpiritAPIServiceException {
		// 系统日志无删除功能
	}

	/**
	 * 根据id查询系统日志
	 */
	@Override
	public SysLogRespDTO findById(Long id) throws SpiritAPIServiceException {
		return convertPoToRespDto(sysLogDAO.findOne(id));
	}

	@Override
	protected SysLogPO convertReqDtoToPo(SysLogReqDTO reqDTO) {
		SysLogPO sysLogPO = new SysLogPO();
		if(reqDTO != null) {
			BeanUtils.copyProperties(reqDTO, sysLogPO);
			sysLogPO.setVisitTime(new Date(Long.parseLong(reqDTO.getVisitTime())));
			sysLogPO.setUpdateDate(DateUtils.currentDate());
		}
		return sysLogPO;
	}

	@Override
	protected SysLogRespDTO convertPoToRespDto(SysLogPO po) {
		SysLogRespDTO sysLogRespDTO = new SysLogRespDTO();
		if(po != null) {
			BeanUtils.copyProperties(po, sysLogRespDTO, "visitTime", "updateDate");
			if (po.getVisitTime() != null) {
				sysLogRespDTO.setVisitTime(DateUtils.formatDateTime(po.getVisitTime()));
			}
			if (po.getUpdateDate() != null) {
				sysLogRespDTO.setUpdateDate(DateUtils.formatDateTime(po.getUpdateDate()));
			}
		}
		return sysLogRespDTO;
	}

	@Override
	protected Specification<SysLogPO> buildSpecification(Map<String, Object> filter) {
		return SysLogSpecification.querySpecification(filter);
	}

	

}
