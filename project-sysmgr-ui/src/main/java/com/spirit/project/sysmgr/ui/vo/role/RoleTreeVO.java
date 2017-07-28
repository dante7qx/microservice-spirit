package com.spirit.project.sysmgr.ui.vo.role;

import java.util.List;

import com.spirit.project.common.ui.constant.EasyUITreeConsts;

import lombok.Data;

/**
 * 角色树 VO
 * 
 * @author dante
 *
 */
@Data
public class RoleTreeVO {
	private Long id;
	private String text;
	private String state = EasyUITreeConsts.STATE_OPEN;
	private List<RoleTreeVO> children;
	
}
