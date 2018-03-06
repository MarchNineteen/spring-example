# aop 切面

1.静态代理实现代表：AspectJ（编译时增强实现AOP）

需要特定编译器，使用较少

2.动态代理实现代表：Spring AOP 

Spring AOP中的动态代理主要有两种方式，**JDK动态代理**和**CGLIB动态代理**

提供两种实现方式：基于xml的配置文件（spring-aop.xml）和通过AspectJ提供的注解实现AOP（aop backage）。