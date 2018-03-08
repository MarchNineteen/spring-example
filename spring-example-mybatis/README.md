# Spring boot 集成mybatis

集成日志系统 log4j2 详见配置文件 
注意：去掉原来默认的日志依赖


集成swagger 注意添加依赖 和 映射
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