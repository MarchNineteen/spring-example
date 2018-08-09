# Spring boot 集成mybatis swagger log4j2 admin-UI 配置文件切换

### log4j2

集成日志系统 log4j2 详见配置文件 
注意：去掉原来默认的日志依赖

### 集成swagger 注意添加依赖 和 映射

```
@Configuration
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
          registry.addResourceHandler("/webjars/**")
                 .addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

}
```

> https://www.cnblogs.com/luoluocaihong/p/7106276.html

### springboot Admin-UI 

> 官方文档配置基于springboot2.x  http://codecentric.github.io/spring-boot-admin/2.0.2/3

### 配置文件切换

- idea VM-options添加：-Dspring.profiles.active=test
- 配置文件切换
```
spring.profiles.active=pre

application-dev.properties：开发环境
application-test.properties：测试环境
application-prod.properties：生产环境
```