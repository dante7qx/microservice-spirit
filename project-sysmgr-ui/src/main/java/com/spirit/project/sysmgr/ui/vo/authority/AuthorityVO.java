package com.spirit.project.sysmgr.ui.vo.authority;

import lombok.Data;

/**
 * 权限 VO
 * 
 * @author dante
 *
 */
@Data
public class AuthorityVO {
	private Long id;
	private String name;
	private String code;
	private String authorityDesc;
	private Integer showOrder;
	private Long pid;
	private Long updateUser;

}
