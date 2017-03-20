
-- 用户角色表
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
	id bigint(20) NOT NULL, 
	user_id bigint(20) NOT NULL,
	role_id bigint(20) NOT NULL
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- 角色权限表
DROP TABLE IF EXISTS t_role_authority;
CREATE TABLE t_role_authority (
	id bigint(20) NOT NULL, 
	role_id bigint(20) NOT NULL,
	authority_id bigint(20) NOT NULL
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (  
	id bigint(20) NOT NULL,
	account varchar(32) NOT NULL, 
	name varchar(64) NOT NULL, 
	password varchar(256) NOT NULL,
	email varchar(128) NOT NULL DEFAULT '', 
	status varchar(6) NOT NULL DEFAULT 'NORMAL' COMMENT 'NORMAL: 正常。LOCK: 锁定。DEL: 删除。',
	update_user bigint(20) NOT NULL default 1,
	update_date datetime NOT NULL default now(),
	CONSTRAINT t_user_un_account UNIQUE KEY (account) 
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;

-- 资源表
DROP TABLE IF EXISTS t_resource;
CREATE TABLE t_resource (  
	id bigint(20) NOT NULL,  
	name varchar(64) NOT NULL, 
	url varchar(128) NOT NULL, 
	service_module_id bigint(20) NOT NULL, -- 微服务模块
	pid bigint(20) NULL, 
	authority_id bigint(20) NOT NULL, 
	full_id varchar(128) NOT NULL DEFAULT '',
	show_order int NOT NULL DEFAULT 1,
	resource_desc varchar(128) NOT NULL DEFAULT '',
	update_user bigint(20) not null DEFAULT 1,
	update_date datetime not null DEFAULT now()
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci; 

-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (  
	id bigint(20) NOT NULL,   
	name varchar(64) NOT NULL, 
	role_desc varchar(128) NOT NULL DEFAULT '',
	update_user bigint(20) not null DEFAULT 1,
	update_date datetime not null DEFAULT now()
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;

-- 权限表
DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority (  
	id bigint(20) NOT NULL,  
	code varchar(64) NOT NULL, 
	name varchar(64) NOT NULL, 
	authority_desc varchar(100) NOT NULL DEFAULT '',  
	pid bigint(20) NULL,
	show_order int NOT NULL DEFAULT 1,
	update_user bigint(20) not null DEFAULT 1,
	update_date datetime not null DEFAULT now()
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci;

-- 微服务模块表
DROP TABLE IF EXISTS t_service_module;
CREATE TABLE t_service_module (
	id bigint(20) NOT NULL, 
	name varchar(32) NOT NULL,
	url varchar(32) NOT NULL,
	update_user bigint(20) NOT NULL default 1,
	update_date datetime NOT NULL default now()
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;