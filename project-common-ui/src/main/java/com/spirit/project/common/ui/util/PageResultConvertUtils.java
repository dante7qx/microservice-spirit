package com.spirit.project.common.ui.util;

import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.ui.dto.resp.PageResult;

/**
 * 分页转换工具类
 * 
 * @author dante
 *
 */
public final class PageResultConvertUtils {
	
	private PageResultConvertUtils() {
		throw new IllegalAccessError("PageResultConvertUtils 工具类，不能实例化！");
	}
	
	/**
	 * 将 PageResp 转化为 EasyUI PageResult
	 * 
	 * @param pageResp
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static PageResult convertPageRespToPageResult(PageResp pageResp) {
		PageResult pageResult = new PageResult(pageResp.getResult(), pageResp.getTotalCount());
		return pageResult;
	}
	
	public static void main(String[] args) {
		// for maven package
	}
}
