-- 系统管理数据库
CREATE DATABASE IF NOT EXISTS `microservice-spirit-sysmgr` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `microservice-spirit-sysmgr`;

-- 用户角色表
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role (
  id bigint(20) NOT NULL, 
  user_id bigint(20) NOT NULL,
  role_id bigint(20) NOT NULL
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='用户角色表' ;

-- 角色权限表
DROP TABLE IF EXISTS t_role_authority;
CREATE TABLE t_role_authority (
  id bigint(20) NOT NULL, 
  role_id bigint(20) NOT NULL,
  authority_id bigint(20) NOT NULL
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT='角色权限表' ;

-- 用户表
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user (  
  id bigint(20) NOT NULL,
  account varchar(32) NOT NULL COMMENT '帐号',
  name varchar(64) NOT NULL COMMENT '用户名',
  password varchar(256) NOT NULL COMMENT '密码',
  last_password_update_time datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '密码最后修改时间',
  email varchar(128) NOT NULL DEFAULT '' COMMENT '邮箱',
  status varchar(6) NOT NULL DEFAULT 'NORMAL' COMMENT '状态，NORMAL: 正常。LOCK: 锁定。DEL: 删除。',
  ldap_user bool NOT NULL DEFAULT 0 COMMENT '是否域帐号',
  update_user bigint(20) NOT NULL default 1 COMMENT '更新人',
  update_date datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '更新时间',
  CONSTRAINT t_user_un_account UNIQUE KEY (account) 
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci
COMMENT='用户表' ;

-- 资源表
DROP TABLE IF EXISTS t_resource;
CREATE TABLE t_resource (  
  id bigint(20) NOT NULL,  
  name varchar(64) NOT NULL COMMENT '资源名称',
  url varchar(128) NOT NULL COMMENT '访问URL', 
  service_module_id bigint(20) NOT NULL COMMENT '所属服务模块',
  pid bigint(20) NULL COMMENT '父节点',
  authority_id bigint(20) NOT NULL COMMENT '权限Id',
  full_id varchar(128) NOT NULL DEFAULT '' COMMENT '全层级Id',
  show_order int NOT NULL DEFAULT 1 COMMENT '显示顺序',
  resource_desc varchar(128) NOT NULL DEFAULT '' COMMENT '资源描述',
  update_user bigint(20) not null DEFAULT 1 COMMENT '更新人',
  update_date datetime not null DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci
COMMENT='资源表' ;

-- 角色表
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role (  
  id bigint(20) NOT NULL,   
  name varchar(64) NOT NULL COMMENT '角色名称',
  role_desc varchar(128) NOT NULL DEFAULT '' COMMENT '角色描述',
  update_user bigint(20) not null DEFAULT 1 COMMENT '更新人',
  update_date datetime not null DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci
COMMENT '角色表' ;

-- 权限表
DROP TABLE IF EXISTS t_authority;
CREATE TABLE t_authority (  
  id bigint(20) NOT NULL,  
  code varchar(64) NOT NULL COMMENT '权限编码', 
  name varchar(64) NOT NULL COMMENT '权限名称',
  authority_desc varchar(128) NOT NULL DEFAULT '' COMMENT '权限描述', 
  pid bigint(20) NULL COMMENT '父节点',
  show_order int NOT NULL DEFAULT 1 COMMENT '显示顺序',
  update_user bigint(20) not null DEFAULT 1 COMMENT '更新人',
  update_date datetime not null DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间'
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8 
COLLATE=utf8_general_ci
COMMENT '权限表' ;

-- 微服务模块表
DROP TABLE IF EXISTS t_service_module;
CREATE TABLE t_service_module (
  id bigint(20) NOT NULL, 
  name varchar(32) NOT NULL COMMENT '服务名称',
  url varchar(32) NOT NULL COMMENT '服务URL', 
  update_user bigint(20) NOT NULL default 1 COMMENT '更新人',
  update_date datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '更新时间'
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT '服务模块表' ;


-- 系统日志数据库
CREATE DATABASE IF NOT EXISTS `microservice-spirit-syslog` DEFAULT CHARSET utf8 COLLATE utf8_general_ci;

USE `microservice-spirit-syslog`;

DROP TABLE IF EXISTS t_sys_log;
CREATE TABLE t_sys_log (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  ip varchar(32) NOT NULL COMMENT '用户IP', 
  user_account varchar(32) NOT NULL COMMENT '用户帐号', 
  request_url varchar(256) NOT NULL COMMENT '请求URL', 
  request_method varchar(16) NOT NULL COMMENT '请求方法', 
  request_parameter longtext COMMENT '请求参数', 
  visit_time datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '访问时间', 
  update_date datetime NOT NULL default CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (id)
) 
ENGINE=InnoDB 
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
COMMENT '系统日志表' ;
