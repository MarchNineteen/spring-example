//package com.wyb.mybatis.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//
///**
// * @author Marcher丶
// * <p>
// * 使执行器端点可访问
// * 安全性参考：http://codecentric.github.io/spring-boot-admin/2.0.2/#securing-spring-boot-admin
// * </p>
// */
//@Configuration
//public class SecurityPermitAllConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().permitAll()
//                .and().csrf().disable();
//    }
//
//}
