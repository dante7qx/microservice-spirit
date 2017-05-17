package com.spirit.project.common.ui.util;

import com.spirit.project.commom.dto.resp.PageResp;
import com.spirit.project.common.ui.dto.resp.PageResult;

public class PageResultConvertUtils {
	
	public static PageResult<?> convertPageRespToPageResult(PageResp<?> pageResp) {
		PageResult<?> pageResult = new PageResult<>(pageResp.getResult(), pageResp.getTotalCount());
		return pageResult;
	}
	
	public static void main(String[] args) {
		// for maven package
	}
}
