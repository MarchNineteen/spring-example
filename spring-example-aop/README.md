# aop 切面

1.静态代理实现代表：AspectJ（编译时增强实现AOP）

需要特定编译器，使用较少

2.动态代理实现代表：Spring AOP 

Spring AOP中的动态代理主要有两种方式，**JDK动态代理**和**CGLIB动态代理**

提供两种实现方式：基于xml的配置文件（spring-aop.xml）和通过AspectJ提供的注解实现AOP（aop backage）。

# 注解
元注解:元注解是可以注解到注解上的注解，或者说元注解是一种基本注解，但是它能够应用到其它的注解上面。
如果难于理解的话，你可以这样理解。元注解也是一张标签，但是它是一张特殊的标签，它的作用和目的就是给其他普通的标签进行解释说明的。
元标签有 @Retention、@Documented、@Target、@Inherited、@Repeatable 5 种。

@Retention:保留期，表示注解的的存活时间
取值如下：
- RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器进行编译时它将被丢弃忽视。
- RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
- RetentionPolicy.RUNTIME 注解可以保留到程序运行的时候，它会被加载进入到 JVM 中，所以在程序运行时可以获取到它们。

@Documented：顾名思义，这个元注解肯定是和文档有关。它的作用是能够将注解中的元素包含到 Javadoc 中去

@Target：指定了注解运用的地方
取值如下：
- ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
- ElementType.CONSTRUCTOR 可以给构造方法进行注解
- ElementType.FIELD 可以给属性进行注解
- ElementType.LOCAL_VARIABLE 可以给局部变量进行注解
- ElementType.METHOD 可以给方法进行注解
- ElementType.PACKAGE 可以给一个包进行注解
- ElementType.PARAMETER 可以给一个方法内的参数进行注解
- ElementType.TYPE 可以给一个类型进行注解，比如类、接口、枚举

@Inherited:父类拥有注解，当其子类没有其它注解时，默认拥有父类注解

@Repeatable：使用该注解，在同一个类（或方法。。。）可重复使用同一注解，在注解中必须添加@Repeatable(Logs.class)当前注解的注解容器
```
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Logs {

    Log[] value() default {};
}

```
重复使用后，就变成了注解容器@Logs,切点注意改成注解容易才能获取

# java代理

静态代理：把真正的接口实现类作为代理类的成员变量，调用者其实都不知道被代理对象的存在。只能代理一个具体的类，如果要代理一个接口的多个实现的话需要定义不同的代理类。

动态代理：

- JDK 动态代理：proxy 类是用于创建代理对象，而 InvocationHandler 接口主要你是来处理执行逻辑。被代理的必须为接口。
- CGLIB 动态代理: cglib 是对一个小而快的字节码处理框架 ASM 的封装。 他的特点是继承于被代理类，这就要求被代理类不能被 final 修饰。使用Enhancer创建代理类。
 