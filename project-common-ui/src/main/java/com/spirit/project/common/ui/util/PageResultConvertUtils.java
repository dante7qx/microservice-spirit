package com.spirit.project.common.ui.util;

import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.ui.dto.resp.PageResult;

/**
 * 分页转换工具类
 * 
 * @author dante
 *
 */
public class PageResultConvertUtils {
	
	private PageResultConvertUtils() {
		throw new IllegalAccessError("PageResultConvertUtils 工具类，不能实例化！");
	}
	
	/**
	 * 将 PageResp 转化为 PageResult
	 * 
	 * @param pageResp
	 * @return
	 */
	public static PageResult<? extends Object> convertPageRespToPageResult(PageResp<? extends Object> pageResp) {
		PageResult<? extends Object> pageResult = new PageResult<>(pageResp.getResult(), pageResp.getTotalCount());
		return pageResult;
	}
	
	public static void main(String[] args) {
		// for maven package
	}
}
