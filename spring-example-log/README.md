# java 日志体系

### SLF4J

### slf4j-simple

### JCL

common-logging是apache提供的一个通用的日志接口

### JUL

common-logging是apache提供的一个通用的日志接口java.util.logging.Logger（JUL），JDK自带的日志系统

### Log4j 

### Log4j2

### logback

Spring Boot默认的日志系统

### 总结

![](https://github.com/MarchNineteen/spring-example/tree/master/spring-example-log/src/main/resources/static/all-log.jpg)

接口：将所有日志实现适配到了一起，用统一的接口调用。 
实现：目前主流的日志实现 
旧日志到slf4j的适配器：如果使用了slf4j，但是只想用一种实现，想把log4j的日志体系也从logback输出，这个是很有用的。 
slf4j到实现的适配器：如果想制定slf4j的具体实现，需要这些包。

slf4j跟commons-logging类似，是各种日志实现的通用入口，log4j、log4j2、logback、slf4j-simple和java.util.logging是比较常见的日志实现系统，目前应用比较广泛的是Log4j和logback，而logback作为后起之秀，以替代log4j为目的，整体性能比log4j较佳，log4j的升级版log4j2也是有诸多亮点，用户可以根据项目需求和个人习惯，选择合适的日志实现。

