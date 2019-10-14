# Spring boot 集成mybatis 实现多数据源 分布式事务

### 实现多数据源的几种方法

- JPA
- 实现spring提供的动态数据源，通过aop在运行时切换数据源([参考项目](https://github.com/heikehuan/springboot-multiple-dataSources))
- 每个数据库实现一个数据源、事务，通过不同的mapper实现不同数据库操作(项目采用该种)

### 分布式事务开源框架

采用jta-atomikos

### 问题

事务回滚会占用一个主键ID
