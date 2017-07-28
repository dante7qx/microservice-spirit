package com.spirit.project.sysmgr.ui.vo.authority;

import java.util.List;

import com.google.common.collect.Lists;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;

import lombok.Data;

/**
 * 权限树 VO
 * 
 * @author dante
 *
 */
@Data
public class AuthorityTreeVO {
	private Long id;
	private String text;
	private String iconCls;
	private String state = EasyUITreeConsts.STATE_CLOSED;
	private List<AuthorityTreeVO> children;
	private AuthorityVO attributes;

	public List<AuthorityTreeVO> getChildren() {
		if (this.children == null) {
			this.children = Lists.newArrayList();
		}
		return children;
	}

}
