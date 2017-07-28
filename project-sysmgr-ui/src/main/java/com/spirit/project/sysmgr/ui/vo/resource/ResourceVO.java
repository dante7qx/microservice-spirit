package com.spirit.project.sysmgr.ui.vo.resource;

import lombok.Data;

/**
 * 资源 VO
 * 
 * @author dante
 *
 */
@Data
public class ResourceVO {
	private Long id;
	private String name;
	private String url;
	private Long authorityId;
	private Long serviceModuleId;
	private String serviceModuleName;
	private String serviceModuleUrl;
	private String fullId;
	private Integer showOrder;
	private Long pid;
	private Long updateUser;

}
