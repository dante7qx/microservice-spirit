package com.spirit.project.getway.ui.vo;

import java.util.List;

import com.google.common.collect.Lists;

public class LoginUserMenuVO {
	private Long id;
	private String name;
	private String url;
	private List<LoginUserMenuVO> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<LoginUserMenuVO> getChildren() {
		if (this.children == null) {
			this.children = Lists.newLinkedList();
		}
		return children;
	}

	public void setChildren(List<LoginUserMenuVO> children) {
		this.children = children;
	}

}
