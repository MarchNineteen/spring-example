create table if not exists user(
    id int(8) auto_increment
    primary key,
    username varchar(255) default '' not null comment '用户名',
    password varchar(255) default '' not null comment '密码',
    sex int not null,
    age int default 0 not null,
    phone varchar(16) default '' not null comment '联系方式',
    status int(4) default 0 not null,
    create_time datetime null,
    update_time datetime null
) comment '用户表';
