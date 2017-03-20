package com.spirit.project.sysmgr.ui.vo.authority;

import java.util.List;

import com.google.common.collect.Lists;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;

public class AuthorityTreeVO {
	private Long id;
	private String text;
	private String iconCls;
	private String state = EasyUITreeConsts.STATE_CLOSED;
	private List<AuthorityTreeVO> children;
	private AuthorityVO attributes;

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

	public List<AuthorityTreeVO> getChildren() {
		if (this.children == null) {
			this.children = Lists.newArrayList();
		}
		return children;
	}

	public void setChildren(List<AuthorityTreeVO> children) {
		this.children = children;
	}

	public AuthorityVO getAttributes() {
		return attributes;
	}

	public void setAttributes(AuthorityVO attributes) {
		this.attributes = attributes;
	}

}
