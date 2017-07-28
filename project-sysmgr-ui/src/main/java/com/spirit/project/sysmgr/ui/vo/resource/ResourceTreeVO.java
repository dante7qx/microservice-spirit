package com.spirit.project.sysmgr.ui.vo.resource;

import java.util.List;

import com.google.common.collect.Lists;
import com.spirit.project.common.ui.constant.EasyUITreeConsts;

import lombok.Data;

/**
 * 资源树 VO
 * 
 * @author dante
 *
 */
@Data
public class ResourceTreeVO {
	private Long id;
	private String text;
	private String iconCls;
	private String state = EasyUITreeConsts.STATE_OPEN;
	private List<ResourceTreeVO> children;
	private ResourceVO attributes;

	public List<ResourceTreeVO> getChildren() {
		if(children == null) {
			children = Lists.newArrayList();
		}
		return children;
	}

}
