-- ----------------------------
-- 1、用户表
-- ----------------------------
drop table if exists sys_user;
create table sys_user(
  user_id int not null auto_increment comment '用户ID',
  user_name varchar(30) not null comment '用户名称',
  login_name varchar(30) not null comment '登陆名',
  password varchar(60) not null comment '密码',
  primary key(user_id)
)engine=innodb default charset=utf8 comment = '用户表';

-- ----------------------------
-- 用户表 初始化数据
-- ----------------------------
insert into sys_user values(1, 'admin', 'admin', '$2a$10$HIM5Mt1HOrrIEWTAKUTlMO746lFoxZdAs6tkdOSXyOohk.nIE2Lie')


-- ----------------------------
-- 2、角色表
-- ----------------------------
drop table if exists sys_role;
create table sys_role(
  role_id int not null auto_increment comment '角色ID',
  role_name varchar(30) not null comment '角色名称',
  role_key varchar(30) not null comment '角色权限字符串',
  primary key(role_id)
)engine=innodb default charset=utf8 comment = '角色表';

-- ----------------------------
-- 角色表 初始化数据
-- ----------------------------
insert into sys_role values(1, 'admin', 'ADMIN');


-- ----------------------------
-- 3、用户和角色关联表
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role(
  user_id int not null comment '用户ID',
  role_id int not null comment '角色ID',
primary key(user_id, role_id)
)engine=innodb default charset=utf8 comment = '用户和角色关联表';

-- ----------------------------
-- 用户和角色关联表 初始化数据
-- ----------------------------
insert into sys_user_role values(1, 1);


-- ----------------------------
-- 4、菜单表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu(
  menu_id int not null auto_increment comment '菜单ID',
  menu_name varchar(50) not null comment '菜单名称',
  parent_id int	default 0 comment '父菜单ID',
  order_num int(4) default 0 comment '显示顺序',
  url varchar(200) default '#'	comment '请求地址',
  menu_type char(1) default '' 	comment '菜单类型（M目录 C菜单 F按钮）',
  visible char(1) default 0 comment '菜单状态（0显示 1隐藏）',
  perms varchar(100) 	default null 	comment '权限标识',
  icon 	varchar(100) 	default '#' comment '菜单图标',
  primary key(menu_id)
)engine=innodb default charset=utf8 comment = '菜单表';

-- ----------------------------
-- 菜单表 初始化数据
-- ----------------------------
insert into sys_menu values(1, '系统管理', 0, 1, '#', 'M', 0, '', 'fa fa-gear');
insert into sys_menu values(2, '用户管理', 1, 1, '/system/user', 'C', 0, 'SYSTEM_USER_SELECT', '#');


-- ----------------------------
-- 5、角色和菜单关联表
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id int not null comment '角色ID',
  menu_id int not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb default charset=utf8 comment = '角色和菜单关联表';

-- ----------------------------
-- 角色和菜单关联表 初始化数据
-- ----------------------------
insert into sys_role_menu values(1,1);
insert into sys_role_menu values(1,2);


