package com.spirit.project.sysmgr.ui.vo.role;

import java.util.List;

import org.springframework.util.CollectionUtils;

import com.spirit.project.common.ui.constant.EasyUITreeConsts;

import lombok.Data;

/**
 * 权限角色树 VO
 * 
 * @author dante
 *
 */
@Data
public class AuthorityRoleTreeVO {

	private Long id;
	private String text;
	private String state = EasyUITreeConsts.STATE_CLOSED;
	private Long pid;
	private Boolean checked;
	private List<AuthorityRoleTreeVO> children;
	
	public AuthorityRoleTreeVO() {
		// 默认构造函数
	}

	public AuthorityRoleTreeVO(AuthorityRoleVO authorityRole) {
		this.id = authorityRole.getId();
		this.text = authorityRole.getName();
		this.state = authorityRole.getPid() == null ? EasyUITreeConsts.STATE_OPEN : EasyUITreeConsts.STATE_CLOSED;
		this.pid = authorityRole.getPid();
		this.checked = authorityRole.getHasRelRole();
	}
	
	public String getState() {
		if(CollectionUtils.isEmpty(children)) {
			state = EasyUITreeConsts.STATE_OPEN;
		}
		return state;
	}

}
