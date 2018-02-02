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