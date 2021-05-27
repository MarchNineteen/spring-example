show VARIABLES like '%max_allowed_packet%';
show VARIABLES like '%cache%';
set global max_allowed_packet = 20*1024*1024;
set global query_cache_size = 0;

create DATABASE if not exists vhr collate utf8_general_ci;
grant select,insert,update,delete on vhr.* to vhr@'%' Identified by 'vhrPwd';

create DATABASE if not exists kanedb collate utf8_general_ci;
grant create,alter,drop on kanedb.* to kane@'%';
grant select,insert,update,delete on kanedb.* to kane@'%' Identified by 'kanePwd';

CREATE DATABASE IF NOT EXISTS kanedbPro DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
grant select,insert,update,delete on kanedbPro.* to kanePro@'%' Identified by 'kanePwd';
grant create,alter,drop on kanedbPro.* to kanePro@'%';

CREATE DATABASE IF NOT EXISTS shiro DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
grant select,insert,update,delete on shiro.* to shiro@'%' Identified by 'shiroPwd';
grant create,alter,drop on shiro.* to shiro@'%';

CREATE DATABASE IF NOT EXISTS seckill DEFAULT CHARSET utf8 COLLATE utf8_general_ci;
grant select,insert,update,delete on seckill.* to seckill@'%' Identified by 'seckillPwd';

CREATE DATABASE IF NOT EXISTS miaosha DEFAULT CHARSET utf8 COLLATE utf8_general_ci;


create DATABASE if not exists test1 collate utf8_general_ci;
grant select,insert,update,delete on test1.* to test1@'%' Identified by '123456';

# 主从同步
CREATE USER 'slave'@'%' IDENTIFIED BY '123456';
GRANT REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'slave'@'%';

create DATABASE if not exists multidatabase collate utf8_general_ci;
grant select,insert,update,delete on multidatabase.* to multidatabase@'%' Identified by '123456';
grant select,insert,update,delete on multidatabase.* to multidatabase@'%' Identified by '123456';
# 主从同步



flush privileges;


