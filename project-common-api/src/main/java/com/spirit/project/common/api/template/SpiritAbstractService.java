package com.spirit.project.common.api.template;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;

public abstract interface SpiritAbstractService<ReqDTO, RespDTO> {
	
	public PageResp<RespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException;
	
	public RespDTO persist(ReqDTO reqDTO) throws SpiritAPIServiceException;
	
	/**
	 * 物理删除
	 * 
	 * @param id
	 * @throws SpiritAPIServiceException
	 */
	public void deleteById(Long id) throws SpiritAPIServiceException;
	
	/**
	 * 逻辑删除
	 * 
	 * reqDTO：id、updateUser必须传递，不能为空
	 * 
	 * @param reqDTO
	 * @throws SpiritAPIServiceException
	 */
	public void delete(ReqDTO reqDTO) throws SpiritAPIServiceException;
	
	public RespDTO findById(Long id) throws SpiritAPIServiceException;
	
}
