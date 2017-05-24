package com.spirit.project.common.api.template;

import com.spirit.project.commom.dto.req.PageReq;
import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.api.exception.SpiritAPIServiceException;

/**
 * 抽象API业务接口，所有业务Service接口需要继承此接口
 * 
 * @author dante
 *
 * @param <ReqDTO>
 * @param <RespDTO>
 */
public abstract interface SpiritAbstractService<ReqDTO, RespDTO> {
	
	/**
	 * 分页查询
	 * 
	 * @param pageReq
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public PageResp<RespDTO> findPage(PageReq pageReq) throws SpiritAPIServiceException;
	
	/**
	 * 持久化（新增、更新）
	 * 
	 * @param reqDTO
	 * @return
	 * @throws SpiritAPIServiceException
	 */
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
	
	/**
	 * 根据id（主键）查询
	 * 
	 * @param id
	 * @return
	 * @throws SpiritAPIServiceException
	 */
	public RespDTO findById(Long id) throws SpiritAPIServiceException;
	
}
