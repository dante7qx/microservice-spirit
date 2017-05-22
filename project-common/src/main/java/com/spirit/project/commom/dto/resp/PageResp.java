package com.spirit.project.commom.dto.resp;

import java.util.List;

import com.google.common.collect.Lists;

public class PageResp<T> {

	private List<T> result;

	private int pageNo;

	private int pageSize;

	private int totalPage;

	private long totalCount;
	
	public PageResp() {
		// TODO Auto-generated constructor stub
	}

	public List<T> getResult() {
		if(result == null) {
			this.result = Lists.newArrayList();
		}
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

}
