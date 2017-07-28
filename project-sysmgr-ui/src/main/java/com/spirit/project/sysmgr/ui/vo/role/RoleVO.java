package com.spirit.project.sysmgr.ui.vo.role;

import java.util.Set;

import lombok.Data;

/**
 * 角色 VO
 * 
 * @author dante
 *
 */
@Data
public class RoleVO {
	private Long id;
	private String name;
	private String roleDesc;
	private Set<Long> authorityIds;
	private Long updateUser;
	private String updateUserName;
	private String updateDate;

}
