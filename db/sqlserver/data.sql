-- 系统管理数据库
USE "microservice-spirit-sysmgr";

-- 用户数据
SET IDENTITY_INSERT dbo.t_user ON;  
insert into t_user(id, account,name,password,status,email) values
(1, 'superadmin','超级管理员','$2a$10$tjOiDRuFyuyLCszfrFIlfeu.ODX29jaem3wKJ0.Gq0.fY0p3QvfF.','NORMAL','-');
SET IDENTITY_INSERT dbo.t_user OFF;

-- 权限数据
SET IDENTITY_INSERT dbo.t_authority ON;
insert into t_authority(id, code, name, authority_desc, pid, show_order, update_user, update_date) values
(1, 'public', '公共权限', '公共权限', null, 1, 1, getdate()),
(2, 'public', '系统管理', '系统管理', null, 2, 1, getdate()),

(3, 'public', '用户管理', '用户管理', 2, 1, 1, getdate()),
(4, 'sysmgr.user.query', '查询用户', '查询用户', 3, 1, 1, getdate()),
(5, 'sysmgr.user.update', '更新用户', '更新用户', 3, 2, 1, getdate()),
(6, 'sysmgr.user.delete', '删除用户', '删除用户', 3, 3, 1, getdate()),

(7, 'public', '角色管理', '角色管理', 2, 2, 1, getdate()),
(8, 'sysmgr.role.query', '查询角色', '查询角色', 7, 1, 1, getdate()),
(9, 'sysmgr.role.update', '更新角色', '更新角色', 7, 2, 1, getdate()),
(10, 'sysmgr.role.delete', '删除角色', '删除角色', 7, 3, 1, getdate()),

(11, 'public', '权限管理', '权限管理', 2, 3, 1, getdate()),
(12, 'sysmgr.authority.query', '查询权限', '查询权限', 11, 1, 1, getdate()),
(13, 'sysmgr.authority.update', '更新权限', '更新权限', 11, 2, 1, getdate()),
(14, 'sysmgr.authority.delete', '删除权限', '删除权限', 11, 3, 1, getdate()),

(15, 'public', '菜单管理', '菜单管理', 2, 4, 1, getdate()),
(16, 'sysmgr.resource.query', '查询菜单', '查询菜单', 15, 1, 1, getdate()),
(17, 'sysmgr.resource.update', '更新菜单', '更新菜单', 15, 2, 1, getdate()),
(18, 'sysmgr.resource.delete', '删除菜单', '删除菜单', 15, 3, 1, getdate()),

(19, 'public', '服务模块管理', '服务模块管理', 2, 5, 1, getdate()),
(20, 'sysmgr.servicemodule.query', '查询服务模块', '查询服务模块', 19, 1, 1, getdate()),
(21, 'sysmgr.servicemodule.update', '更新服务模块', '更新服务模块', 19, 2, 1, getdate()),
(22, 'sysmgr.servicemodule.delete', '删除服务模块', '删除服务模块', 19, 3, 1, getdate()),

(23, 'syslog.syslog.query', '系统日志管理', '系统日志管理', 1, 2, 1, getdate());
SET IDENTITY_INSERT dbo.t_authority OFF;

-- 微服务模块数据
SET IDENTITY_INSERT dbo.t_service_module ON;
insert into t_service_module (id, name, url, update_user, update_date) values
(1, '系统管理服务模块', 'sysmgr', 1, getdate()),
(2, '系统日志服务模块', 'syslog', 1, getdate());
SET IDENTITY_INSERT dbo.t_service_module OFF;

-- 资源数据
SET IDENTITY_INSERT dbo.t_resource ON;
insert into t_resource(id, name, url, service_module_id, pid, authority_id, full_id, show_order, resource_desc,update_user,update_date) values 
(1, '系统管理', '-', 1, null, 1, '1', 1, '系统管理', 1, getdate()),
(2, '用户管理', 'user', 1, 1, 4, '2-1', 1, '用户管理', 1, getdate()),
(3, '角色管理', 'role', 1, 1, 8, '3-1', 2, '角色管理', 1, getdate()),
(4, '权限管理', 'authority', 1, 1, 12, '4-1', 3, '权限管理', 1, getdate()),
(5, '菜单管理', 'menu', 1, 1, 16, '5-1', 4, '菜单管理', 1, getdate()),
(6, '服务模块管理', 'servicemodule', 1, 1, 16, '6-1', 5, '服务模块管理', 1, getdate()),
(7, '系统日志管理', 'syslog', 2, null, 23, '71', 5, '系统日志管理', 1, getdate());
SET IDENTITY_INSERT dbo.t_resource OFF;

-- 角色数据
SET IDENTITY_INSERT dbo.t_role ON;
insert into t_role(id, name, role_desc, update_user, update_date) values
(1, '系统管理员', '管理系统管理服务模块中的各个功能', 1, getdate());
SET IDENTITY_INSERT dbo.t_role OFF;

-- 角色权限数据
SET IDENTITY_INSERT dbo.t_role_authority ON;
insert into t_role_authority(id, role_id, authority_id) values
(1, 1, 1),
(2, 1, 4),
(3, 1, 5),
(4, 1, 6),
(5, 1, 8),
(6, 1, 9),
(7, 1, 10),
(8, 1, 12),
(9, 1, 13),
(10, 1, 14),
(11, 1, 16),
(12, 1, 17),
(13, 1, 18),
(14, 1, 20),
(15, 1, 21),
(16, 1, 22),
(17, 1, 23);
SET IDENTITY_INSERT dbo.t_role_authority OFF;

-- 用户角色数据
SET IDENTITY_INSERT dbo.t_user_role ON;
insert into t_user_role(id, user_id, role_id) values 
(1, 1, 1);
SET IDENTITY_INSERT dbo.t_user_role OFF;