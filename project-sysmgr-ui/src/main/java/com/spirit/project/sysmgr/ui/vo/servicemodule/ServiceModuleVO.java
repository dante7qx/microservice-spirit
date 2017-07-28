package com.spirit.project.sysmgr.ui.vo.servicemodule;

import lombok.Data;

/**
 * 服务模块 VO
 * 
 * @author dante
 *
 */
@Data
public class ServiceModuleVO {
	private Long id;
	private String name;
	private String url;
	private Long updateUser;
	private String updateUserName;
	private String updateDate;

}
