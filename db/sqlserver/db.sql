-- 用户角色表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_user_role]') and type in (N'U')) drop table [dbo].[t_user_role];
create table t_user_role ( 
	Id bigint identity(1,1) constraint pk_t_user_role_id primary key (id), 
	user_id bigint NOT NULL,
	role_id bigint NOT NULL
);

-- 角色权限表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_role_authority]') and type in (N'U')) drop table [dbo].[t_role_authority];
create table t_role_authority ( 
	Id bigint identity(1,1) constraint pk_t_role_authority_id primary key (id), 
	role_id bigint NOT NULL,
	authority_id bigint NOT NULL
);

-- 用户表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_user]') and type in (N'U')) drop table [dbo].[t_user];
create table t_user ( 
	Id bigint identity(1,1) constraint pk_t_user_id primary key (id), 
	account varchar(32) NOT NULL, 
	name varchar(64) NOT NULL, 
	password varchar(256) NOT NULL,
	email varchar(128) NOT NULL DEFAULT '', 
	status varchar(6) NOT NULL DEFAULT 'NORMAL',
	update_user bigint NOT NULL default 1,
	update_date datetime NOT NULL default getdate()
);
alter table t_user add constraint un_t_user_account unique (account);

-- 资源表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_resource]') and type in (N'U')) drop table [dbo].[t_resource];
create table t_resource ( 
	Id bigint identity(1,1) constraint pk_t_resource_id primary key (id), 
	name varchar(64) NOT NULL, 
	url varchar(128) NOT NULL, 
	service_module_id bigint NOT NULL, -- 微服务模块
	pid bigint NULL, 
	authority_id bigint NOT NULL, 
	full_id varchar(128) NOT NULL DEFAULT '',
	show_order int NOT NULL DEFAULT 1,
	resource_desc varchar(128) NOT NULL DEFAULT '',
	update_user bigint not null DEFAULT 1,
	update_date datetime not null DEFAULT getdate()
);

-- 角色表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_role]') and type in (N'U')) drop table [dbo].[t_role];
create table t_role ( 
	Id bigint identity(1,1) constraint pk_t_role_id primary key (id), 
	name varchar(64) NOT NULL, 
	role_desc varchar(128) NOT NULL DEFAULT '',
	update_user bigint not null DEFAULT 1,
	update_date datetime not null DEFAULT getdate()
);

-- 权限表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_authority]') and type in (N'U')) drop table [dbo].[t_authority];
create table t_authority ( 
	Id bigint identity(1,1) constraint pk_t_authority_id primary key (id), 
	code varchar(64) NOT NULL, 
	name varchar(64) NOT NULL, 
	authority_desc varchar(100) NOT NULL DEFAULT '',  
	pid bigint NULL,
	show_order int NOT NULL DEFAULT 1,
	update_user bigint not null DEFAULT 1,
	update_date datetime not null DEFAULT getdate()
);

-- 微服务模块表
if exists (select * from sys.objects where object_id = object_id(N'[dbo].[t_service_module]') and type in (N'U')) drop table [dbo].[t_service_module];
create table t_service_module ( 
	Id bigint identity(1,1) constraint pk_t_service_module_id primary key (id), 
	name varchar(32) NOT NULL,
	url varchar(32) NOT NULL,
	update_user bigint not null DEFAULT 1,
	update_date datetime not null DEFAULT getdate()
);

-- 外键约束
alter table t_user_role add constraint fk_t_user_role_user_id foreign key (user_id ) references t_user (id );
alter table t_user_role add constraint fk_t_user_role_role_id foreign key (role_id ) references t_role (id );

alter table t_role_authority add constraint fk_t_role_authority_role_id foreign key (role_id ) references t_role (id );
alter table t_role_authority add constraint fk_t_role_authority_authority_id foreign key (authority_id ) references t_authority (id );

alter table t_resource add constraint fk_t_resource_service_module_id foreign key (service_module_id ) references t_service_module (id );
alter table t_resource add constraint fk_t_resource_authority_id foreign key (authority_id ) references t_authority (id );