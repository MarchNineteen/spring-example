package com.wyb.shiro.config;

import com.wyb.shiro.config.properties.ShiroProperties;
import com.wyb.shiro.config.properties.ShiroSessionProperties;
import com.wyb.shiro.config.properties.ShiroSignInProperties;
import com.wyb.shiro.config.redis.ShiroRedisCacheManager;
import com.wyb.shiro.config.session.CustomShiroSessionDAO;
import com.wyb.shiro.config.session.JedisShiroSessionRepository;
import com.wyb.shiro.filter.FormSignInFilter;
import com.wyb.shiro.filter.JWTFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kunzite
 */

@Slf4j
public class ShiroConfiguration {

    @Autowired
    private ShiroProperties properties;

    @Autowired
    private ShiroSignInProperties signInProperties;

    @Autowired
    private ShiroSessionProperties shiroSessionProperties;

    @Autowired(required = false)
    private Collection<SessionListener> listeners;

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        log.info("开始加载lifecycleBeanPostProcessor");
        return new LifecycleBeanPostProcessor();
    }

    /****************************** shiro aop注解支持 start ********************************/
    /**
     * 添加注解支持
     */
    @Bean
    @ConditionalOnMissingBean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        log.info("开始加载DefaultAdvisorAutoProxyCreator");
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    @ConditionalOnMissingBean
    @DependsOn(value = {"securityManager"})
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        log.info("开始加载AuthorizationAttributeSourceAdvisor");
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager);
        return aASA;
    }
    /****************************** shiro aop注解支持 end ********************************/


    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     */
    @Bean(name = "securityManager")
    @DependsOn(value = {"shiroCacheManager", "rememberMeManager", "mainRealm"})
    public DefaultWebSecurityManager securityManager(CacheManager cacheManager, Realm realm,
                                                     RememberMeManager rememberMeManager, SessionManager sessionManager) {
        log.info("开始加载securityManager");
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        securityManager.setCacheManager(cacheManager);
        securityManager.setSessionManager(sessionManager);
        securityManager.setRememberMeManager(rememberMeManager);
        return securityManager;
    }

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        log.info("开始加载shiroFilter");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        /******************** 创建过滤器 start **********************/
        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        // 退出过滤器
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/admin/login");
        // jwt过滤器
        JWTFilter jwtFilter = new JWTFilter();
        // 登录过滤器
        FormSignInFilter signInFilter = new FormSignInFilter();
        signInFilter.setLoginUrl(properties.getLoginUrl());
        signInFilter.setSuccessUrl(properties.getSuccessUrl());
        signInFilter.setUsernameParam(signInProperties.getUserParam());
        signInFilter.setPasswordParam(signInProperties.getPasswordParam());
        signInFilter.setRememberMeParam(signInProperties.getRememberMeParam());

        filters.put("logout", logoutFilter);
        filters.put("authc", signInFilter);
        filters.put("jwt", jwtFilter);
        shiroFilterFactoryBean.setFilters(filters);
        /******************** 创建过滤器 end **********************/

        // 过滤器过滤哪些url
        // 自定义url规则 http://shiro.apache.org/web.html#urls-
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionManager.put("/admin/logout", "logout");
//        filterChainDefinitionManager.put("/user/**", "authc,roles[ROLE_USER]");
        filterChainDefinitionManager.put("/events/**", "authc,roles[ROLE_ADMIN]");
//        filterChainDefinitionManager.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都可以匿名访问-->
//        filterChainDefinitionManager.put("/user/**", "jwt");
        filterChainDefinitionManager.put("/admin/user/**", "authc");
        filterChainDefinitionManager.put("/admin/sys/**", "authc");
        // 静态资源 start
        filterChainDefinitionManager.put("/css/**", "anon");
        filterChainDefinitionManager.put("/img/**", "anon");
        filterChainDefinitionManager.put("/js/**", "anon");
        filterChainDefinitionManager.put("/font-awesome/**", "anon");
        filterChainDefinitionManager.put("/favicon.ico", "anon");
        // 静态资源 end
//        filterChainDefinitionManager.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);
//        shiroFilterFactoryBean.setSuccessUrl("/user/list");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }

//    public FormSignInFilter formSignInFilter() {
//        FormSignInFilter filter = new FormSignInFilter();
//        filter.setLoginUrl(properties.getLoginUrl());
//        filter.setSuccessUrl(properties.getSuccessUrl());
//        filter.setUsernameParam(signInProperties.getUserParam());
//        filter.setPasswordParam(signInProperties.getPasswordParam());
//        filter.setRememberMeParam(signInProperties.getRememberMeParam());
//        return filter;
//    }
//
//    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
//        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
//        shiroFilter.setSecurityManager(securityManager);
//        shiroFilter.setLoginUrl(properties.getLoginUrl());
//        shiroFilter.setSuccessUrl(properties.getSuccessUrl());
//        shiroFilter.setUnauthorizedUrl(properties.getUnauthorizedUrl());
//
//        Map<String, Filter> filterMap = new LinkedHashMap<String, Filter>();
//        Class<? extends AuthorizationFilter> customAuthcFilterClass = properties.getCustomAuthcFilterClass();
//        if (null != customAuthcFilterClass ) {
//            AuthorizationFilter filter = BeanUtils.instantiate(customAuthcFilterClass);
//            filterMap.put("authc", filter);
//        } else {
//            filterMap.put("authc", formSignInFilter());
//        }
//
//        shiroFilter.setFilters(filterMap);
//        shiroFilter.setFilterChainDefinitionMap(properties.getFilterChainDefinitions());
//        return shiroFilter;
//    }
//
//    @Bean(name = "shiroFilter")
//    @DependsOn("securityManager")
//    @ConditionalOnMissingBean
//    public FilterRegistrationBean filterRegistrationBean(SecurityManager securityManager) throws Exception {
//        FilterRegistrationBean filterRegistration = new FilterRegistrationBean();
//        //该值缺省为false,表示生命周期由SpringApplicationContext管理,设置为true则表示由ServletContainer管理
//        filterRegistration.addInitParameter("targetFilterLifecycle", "true");
//        filterRegistration.setFilter((Filter) getShiroFilterFactoryBean(securityManager).getObject());
//        filterRegistration.setEnabled(true);
//        filterRegistration.addUrlPatterns("/*");
//        return filterRegistration;
//    }


    /****************************** shiro cache start ********************************/

    /**
     * (基于内存的)用户授权信息Cache
     *
     * @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnMissingBean(name = "shiroCacheManager")
//    public CacheManager memoryCacheManager() {
//        log.info("开始加载MemoryConstrainedCacheManager");
//        return new MemoryConstrainedCacheManager();
//    }

    /**
     * (基于redis的)用户授权信息Cache
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean(name="shiroCacheManager")
    public CacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate) {
        return new ShiroRedisCacheManager(redisTemplate);
    }

    /**
     * (基于ehcache的)用户授权信息Cache
     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnClass(name = {"org.apache.shiro.cache.ehcache.EhCacheManager"})
//    @ConditionalOnMissingBean(name = "shiroCacheManager")
//    public CacheManager ehcacheCacheManager() {
//        log.info("开始加载EhCacheManager");
//        EhCacheManager ehCacheManager = new EhCacheManager();
//        ShiroProperties.Ehcache ehcache = properties.getEhcache();
//        if (ehcache.getCacheManagerConfigFile() != null) {
//            ehCacheManager.setCacheManagerConfigFile(ehcache.getCacheManagerConfigFile());
//        }
//        return ehCacheManager;
//    }

    /**
     * EhCacheManager，缓存管理，用户登陆成功后，把用户信息和权限信息缓存起来，
     * 然后每次用户请求时，放入用户的session中，如果不设置这个bean，每个请求都会查询一次数据库。
     */
//    @Bean(name = "ehCacheManager")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public EhCacheManager ehCacheManager() {
//        return new EhCacheManager();
//    }

    /****************************** shiro cache end ********************************/

    /****************************** shiro session start ********************************/

    /**
     * 打开该方法时，shiro的登录回话session由redis保持。
     *
     * @return
     * @paramjedisShiroSessionRepository
     */
    @Bean
    @DependsOn(value = { "jedisShiroSessionRepository" })
    @ConditionalOnMissingBean
    public SessionDAO sessionDAO(JedisShiroSessionRepository jedisShiroSessionRepository) {
        final CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
        customShiroSessionDAO.setShiroSessionRepository(jedisShiroSessionRepository);
        return customShiroSessionDAO;
    }

    @Bean
    @DependsOn(value = { "objectRedisTemplate" })
    public JedisShiroSessionRepository jedisShiroSessionRepository(RedisTemplate<String, Object> objectRedisTemplate) {
        final JedisShiroSessionRepository jedisShiroSessionRepository = new JedisShiroSessionRepository();
        jedisShiroSessionRepository.setObjectRedisTemplate(objectRedisTemplate);
        return jedisShiroSessionRepository;
    }

//    @Bean
//    @ConditionalOnMissingBean
//    public SessionDAO sessionDAO(CacheManager cacheManager) {
//        log.info("开始加载sessionDAO");
//        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
//        sessionDAO.setActiveSessionsCacheName(shiroSessionProperties.getActiveSessionsCacheName());
//        Class<? extends SessionIdGenerator> idGenerator = shiroSessionProperties.getIdGenerator();
//        if (idGenerator != null) {
//            SessionIdGenerator sessionIdGenerator = BeanUtils.instantiate(idGenerator);
//            sessionDAO.setSessionIdGenerator(sessionIdGenerator);
//        }
//        sessionDAO.setCacheManager(cacheManager);
//        return sessionDAO;
//    }

//    @Bean(name = "sessionValidationScheduler")
//    @DependsOn(value = {"sessionManager"})
//    @ConditionalOnClass(name = {"org.quartz.Scheduler"})
//    @ConditionalOnMissingBean(SessionValidationScheduler.class)
//    public SessionValidationScheduler quartzSessionValidationScheduler(DefaultWebSessionManager sessionManager) {
//        log.info("开始加载quartzSessionValidationScheduler");
//        QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler(sessionManager);
//        quartzSessionValidationScheduler.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
//        quartzSessionValidationScheduler.enableSessionValidation();
//        sessionManager.setDeleteInvalidSessions(shiroSessionProperties.isDeleteInvalidSessions());
//        sessionManager.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
//        sessionManager.setSessionValidationSchedulerEnabled(shiroSessionProperties.isValidationSchedulerEnabled());
//        sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler);
//        return quartzSessionValidationScheduler;
//    }

    @Bean(name = "sessionValidationScheduler")
    @DependsOn(value = {"sessionManager"})
    @ConditionalOnMissingBean(SessionValidationScheduler.class)
    public SessionValidationScheduler sessionValidationScheduler(DefaultWebSessionManager sessionManager) {
        log.info("开始加载SessionValidationScheduler");
        ExecutorServiceSessionValidationScheduler validationScheduler = new ExecutorServiceSessionValidationScheduler(sessionManager);
        sessionManager.setDeleteInvalidSessions(shiroSessionProperties.isDeleteInvalidSessions());
        sessionManager.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
        sessionManager.setSessionValidationSchedulerEnabled(shiroSessionProperties.isValidationSchedulerEnabled());
        sessionManager.setSessionValidationScheduler(validationScheduler);
        return validationScheduler;
    }

    @Bean
    @DependsOn(value = {"shiroCacheManager", "sessionDAO"})
    public WebSessionManager sessionManager(CacheManager cacheManager, SessionDAO sessionDAO) {
        log.info("开始加载WebSessionManager");
        ShiroSessionManager sessionManager = new ShiroSessionManager();
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setGlobalSessionTimeout(shiroSessionProperties.getGlobalSessionTimeout());
        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }

    /****************************** shiro session end ********************************/


}
