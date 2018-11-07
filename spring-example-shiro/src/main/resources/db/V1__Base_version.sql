DROP TABLE IF EXISTS menu;
create table menu
(
  id int(8) auto_increment
    primary key,
  p_id int(8) not null comment '父级菜单id',
  url varchar(64) default '' not null comment '访问路径',
  menu_name varchar(64) default '' not null comment '菜单名称',
  description varchar(64) default '' null comment '描述',
  is_delete tinyint default '0' not null,
  create_time datetime not null,
  update_time datetime not null,
  constraint menu_id_uindex
  unique (id)
)
  comment '菜单表(资源表)'
;

DROP TABLE IF EXISTS role;
create table role
(
  id int(8) auto_increment
    primary key,
  role_name varchar(64) default '' not null comment '角色名称',
  description varchar(64) default '' null comment '描述',
  is_delete tinyint default '0' not null,
  create_time datetime not null,
  update_time datetime not null,
  constraint role_id_uindex
  unique (id)
)
  comment '角色表'
;

DROP TABLE IF EXISTS role_menu;
create table role_menu
(
  id int(8) auto_increment
    primary key,
  role_id int(8) not null comment '角色id',
  menu_id int(8) not null comment '菜单id'
)
  comment '角色菜单关联表'
;

DROP TABLE IF EXISTS user;
create table user
(
  id int(8) auto_increment
    primary key,
  username varchar(255) default '' not null comment '用户名',
  real_name varchar(255) default '' null comment '真实姓名',
  password varchar(255) default '' not null comment '密码',
  address varchar(255) default '' null comment '地址',
  phone varchar(16) default '' not null comment '联系方式',
  is_delete tinyint default '0' not null,
  create_time datetime not null,
  update_time datetime not null,
  constraint user_id_uindex
  unique (id)
)
  comment '用户表'
;

DROP TABLE IF EXISTS user_role;
create table user_role
(
  id int(8) auto_increment
    primary key,
  role_id int(8) not null comment '角色id',
  user_id int(8) not null comment '用户id'
)
  comment '用户角色表'
;

ALTER table user add COLUMN salt  VARCHAR(32) COMMENT '密码盐' after password;
INSERT into user (username, real_name, password, address, phone, is_delete, create_time, update_time) VALUE ('admin','管理员','123456','杭州','1378741577',0,now(),now());

INSERT into role (role_name, description, is_delete, create_time, update_time) VALUE ('超级管理员','超级管理员-最高权限',0,now(),now());
INSERT into role (role_name, description, is_delete, create_time, update_time) VALUE ('系统管理员','系统管理员-系统管理模块权限',0,now(),now());

INSERT into user_role (role_id,user_id) VALUE ('1','1');
INSERT into user_role (role_id,user_id) VALUE ('2','2');

INSERT into role_menu (role_id,menu_id) VALUE ('2','1');
INSERT into role_menu (role_id,menu_id) VALUE ('2','3');

# menu 初始化
INSERT into menu (p_id,url, menu_name,description,is_delete,create_time, update_time) VALUE (0,'/index','主页','网站主页',0,now(),now());
INSERT into menu (p_id,url, menu_name,description,is_delete,create_time, update_time) VALUE (0,'/user','用户列表','用户列表-页面',0,now(),now());
INSERT into menu (p_id,url, menu_name,description,is_delete,create_time, update_time) VALUE (0,'/system','系统管理','系统管理-tab',0,now(),now());