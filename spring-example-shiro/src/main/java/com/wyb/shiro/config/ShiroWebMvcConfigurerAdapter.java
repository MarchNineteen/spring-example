package com.wyb.shiro.config;

import com.wyb.shiro.config.annotation.SessionUserArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 大家在使用2.0版本的springboot的时候 使用WebMvcConfigurationSupport类配置拦截器时一定要重写addResourceHandlers来实现静态资源的映射,
 * 不要使用application.properties中添加配置来实现映射，不然资源会映射不成功导致打开页面资源一直加载不到。会出现下面这种奇怪的问题
 *
 * @author Kunzite
 */
@Configuration
public class ShiroWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SessionUserArgumentResolver());
    }

}
