# Spring事务实例
- 配置事务无法使用.yml格式配置文件（因为spingxml文件无法读取.yml格式文件），
请使用.properties文件
- springboot项目请在启动类中添加
@ImportResource({"classpath*:spring/spring-mybatis-*.xml"})
引入xml配置文件

编程式事务配置
- 1.spring-mybatis-anno 注解形式配置

声明式事务的配置
- 2.spring-mybatis-tx 使用tx标签配置的拦截器
注意添加aop maven依赖
- 3.spring-mybatis-proxy 所有Bean共享一个代理基类
- 4.spring-mybatis-single-proxy 每个Bean都有一个代理
- 5.spring-mybatis-Interceptor 拦截器配置


### 使用场景：
- springboot中优先使用第一种
- 第二种使用较多
- 3，4，5很少使用

> 参考：https://www.cnblogs.com/longshiyVip/p/5061547.html

### 事务隔离级别（定义了一个事务可能受其他并发事务影响的程度）

- TransactionDefinition.ISOLATION_DEFAULT=-1 ：使用后端数据库默认的隔离级别，Mysql 默认采用的 REPEATABLE_READ隔离级别 Oracle 默认采用的 READ_COMMITTED隔离级别. 
- TransactionDefinition.ISOLATION_READ_UNCOMMITTED: 最低的隔离级别，允许读取尚未提交的数据变更，可能会导致**脏读**、**幻读**或**不可重复读**
- TransactionDefinition.ISOLATION_READ_COMMITTED: 允许读取并发事务已经提交的数据，可以阻止**脏读**，但是**幻读**或**不可重复读**仍有可能发生
- TransactionDefinition.ISOLATION_REPEATABLE_READ: 对同一字段的多次读取结果都是一致的，除非数据是被本身事务自己所修改，可以阻止**脏读**和**不可重复读**，但**幻读**仍有可能发生。
- TransactionDefinition.ISOLATION_SERIALIZABLE: 最高的隔离级别，完全服从ACID的隔离级别。所有的事务依次逐个执行，这样事务之间就完全不可能产生干扰，也就是说，该级别可以防止**脏读**、**不可重复读**以及**幻读**。但是这将严重影响程序的性能。通常情况下也不会用到该级别。

### 事务传播行为（为了解决业务层方法之间互相调用的事务问题）

支持当前事务的情况：

- TransactionDefinition.PROPAGATION_REQUIRED： 如果当前存在事务，则加入该事务；如果当前没有事务，则**创建一个新的事务**。
- TransactionDefinition.PROPAGATION_SUPPORTS： 如果当前存在事务，则加入该事务；如果当前没有事务，则以**非事务的方式继续运行**。 
- TransactionDefinition.PROPAGATION_MANDATORY： 如果当前存在事务，则加入该事务；如果当前没有事务，则**抛出异常**。

不支持当前事务的情况：

- TransactionDefinition.PROPAGATION_REQUIRES_NEW： 创建一个新的事务，如果当前存在事务，则把当前事务挂起。
- TransactionDefinition.PROPAGATION_NOT_SUPPORTED： 以**非事务方式**运行，如果当前存在事务，则把当前事务挂起。
- TransactionDefinition.PROPAGATION_NEVER： **以非事务方式运行**，如果当前**存在事务，则抛出异常**。

其他情况：嵌套事务

- TransactionDefinition.PROPAGATION_NESTED： 如果当前存在事务，则创建一个事务作为当前事务的嵌套事务来运行；如果当前没有事务，则该取值等价于TransactionDefinition.PROPAGATION_REQUIRED。