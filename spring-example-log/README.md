# java 日志体系

# 日志具体实现

### JUL

java.util.logging.Logger（JUL），JDK自带的日志系统

优点：

使用简单，在jdk中直接使用

缺点：

太过简单，不支持占位符，扩展性查

### Log4j 

Log4j是Apache的一个开放源代码项目，通过使用Log4j，我们可以控制日志信息输送的目的地是控制台、文件、数据库等；我们也可以控制每一条日志的输出格式；通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。 

Log4j有7种不同的log级别，按照等级从低到高依次为：TRACE、DEBUG、INFO、WARN、ERROR、FATAL、OFF。如果配置为OFF级别，表示关闭log。 

Log4j支持两种格式的配置文件：properties和xml。包含三个主要的组件：Logger、appender、Layout。

缺点：

不支持使用占位符，不利于代码阅读

### Log4j2

Spring Boot1.4以及之后的版本已经不支持log4j，log4j也很久没有更新了，现在已经有很多其他的日志框架对Log4j进行了改良，比如说SLF4J、Logback等。而且Log4j 2在各个方面都与Logback非常相似，那么为什么我们还需要Log4j 2呢？ 

1. 插件式结构。Log4j 2支持插件式结构。我们可以根据自己的需要自行扩展Log4j 2. 我们可以实现自己的appender、logger、filter。 

2. 配置文件优化。在配置文件中可以引用属性，还可以直接替代或传递到组件。而且支持json格式的配置文件。不像其他的日志框架，它在重新配置的时候不会丢失之前的日志文件。 

3. Java 5的并发性。Log4j 2利用Java 5中的并发特性支持，尽可能地执行最低层次的加锁。解决了在log4j 1.x中存留的死锁的问题。 

4. 异步logger。Log4j 2是基于LMAX Disruptor库的。在多线程的场景下，和已有的日志框架相比，异步的logger拥有10倍左右的效率提升。 
　　 
### logback

Spring Boot默认的日志系统

LogBack 其实可以说是 Log4J 的进化版。LogBack 除了具备 Log4j 的所有优点之外，还解决了 Log4J 不能使用占位符的问题。

LogBack 内部集成了 SLF4J 可以更原生地实现一些日志记录的实现

配置文件：需要在项目的src目录下建立一个logback.xml。 
注：（1）logback首先会试着查找logback.groovy文件； 
（2）当没有找到时，继续试着查找logback-test.xml文件； 
（3）当没有找到时，继续试着查找logback.xml文件； 
（4）如果仍然没有找到，则使用默认配置（打印到控制台）。

### SLF4J-SIMPLE

slf4j-simple是slf4j的一种日志系统实现

# 日志门面接口

### SLF4J (适配器)

SLF4J（Simple Logging Facade for Java，即Java简单日志记录接口集）是一个日志的接口规范，它对用户提供了统一的日志接口，屏蔽了不同日志组件的差异。
这样我们在编写代码的时候只需要看 SLF4J 这个接口文档即可，不需要去理会不同日之框架的区别。
而当我们需要更换日志组件的时候，我们只需要更换一个具体的日志组件Jar包就可以了。

![sl4f-api](https://github.com/MarchNineteen/spring-example/blob/master/spring-example-log/src/main/resources/static/sl4f-api.png)

### JCL

common-logging是apache提供的一个通用的日志接口

JCL默认的配置：如果能找到Log4j 则默认使用log4j 实现，如果没有则使用jul(jdk自带的) 实现，再没有则使用jcl内部提供的SimpleLog 实现。

![jcl](https://github.com/MarchNineteen/spring-example/blob/master/spring-example-log/src/main/resources/static/jcl.png)

# 日志系统转换

### 从日志框架转向SLF4J

jul-to-slf4j：jdk-logging到slf4j的桥梁

log4j-over-slf4j：log4j1到slf4j的桥梁

jcl-over-slf4j：commons-logging到slf4j的桥梁

### 从SLF4J转向具体的日志框架

slf4j-jdk14：slf4j到jdk-logging的桥梁

slf4j-log4j12：slf4j到log4j1的桥梁

log4j-slf4j-impl：slf4j到log4j2的桥梁

logback-classic：slf4j到logback的桥梁

slf4j-jcl：slf4j到commons-logging的桥梁

# 总结

![](https://github.com/MarchNineteen/spring-example/blob/master/spring-example-log/src/main/resources/static/all-log.jpg)

接口：将所有日志实现适配到了一起，用统一的接口调用。 
实现：目前主流的日志实现 
旧日志到slf4j的适配器：如果使用了slf4j，但是只想用一种实现，想把log4j的日志体系也从logback输出，这个是很有用的。 
slf4j到实现的适配器：如果想制定slf4j的具体实现，需要这些包。

slf4j跟commons-logging类似，是各种日志实现的通用入口，log4j、log4j2、logback、slf4j-simple和java.util.logging是比较常见的日志实现系统，目前应用比较广泛的是Log4j和logback，而logback作为后起之秀，以替代log4j为目的，整体性能比log4j较佳，log4j的升级版log4j2也是有诸多亮点，用户可以根据项目需求和个人习惯，选择合适的日志实现。

