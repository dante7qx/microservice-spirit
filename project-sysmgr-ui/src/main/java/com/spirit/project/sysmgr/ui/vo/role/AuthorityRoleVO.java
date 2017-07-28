package com.spirit.project.sysmgr.ui.vo.role;

import lombok.Data;

/**
 * 权限角色 VO
 * 
 * @author dante
 *
 */
@Data
public class AuthorityRoleVO {
	private Long id;
	private Long pid;
	private String name;
	private String code;
	private String authorityDesc;
	private Integer showOrder;
	private Long roleId;
	private Boolean hasRelRole = false;

}
