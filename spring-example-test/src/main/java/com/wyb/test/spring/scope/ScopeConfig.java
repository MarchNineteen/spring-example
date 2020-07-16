package com.wyb.test.spring.scope;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author Marcher丶
 */
@Configuration
public class ScopeConfig {

    @Bean(name = "singletonBean")
    @Scope("singleton")
    public ScopeBean singletonBean() {
        return new ScopeBean();
    }

    @Bean(name = "prototypeBean")
    @Scope("prototype")
    public ScopeBean prototypeBean() {
        return new ScopeBean();
    }

    @Bean(name = "requestBean")
    @Scope("request")
    public ScopeBean requestBean() {
        return new ScopeBean();
    }

    @Bean(name = "sessionBean")
    @Scope("session")
    public ScopeBean session() {
        return new ScopeBean();
    }

    @Bean(name = "globalSessionBean")
    @Scope("globalSession")
    public ScopeBean globalSession() {
        return new ScopeBean();
    }
}
