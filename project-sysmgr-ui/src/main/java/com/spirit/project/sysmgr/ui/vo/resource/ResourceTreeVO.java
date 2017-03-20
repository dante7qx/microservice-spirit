package com.spirit.project.sysmgr.ui.vo.resource;

import java.util.List;

import com.google.common.collect.Lists;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;

public class ResourceTreeVO {
	private Long id;
	private String text;
	private String iconCls;
	private String state = EasyUITreeConsts.STATE_OPEN;
	private List<ResourceTreeVO> children;
	private ResourceVO attributes;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ResourceTreeVO> getChildren() {
		if(children == null) {
			children = Lists.newArrayList();
		}
		return children;
	}

	public void setChildren(List<ResourceTreeVO> children) {
		this.children = children;
	}

	public ResourceVO getAttributes() {
		return attributes;
	}

	public void setAttributes(ResourceVO attributes) {
		this.attributes = attributes;
	}

}
