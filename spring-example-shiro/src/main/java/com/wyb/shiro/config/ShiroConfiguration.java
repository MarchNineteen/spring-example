package com.wyb.shiro.config;

import com.wyb.shiro.config.properties.*;
import com.wyb.shiro.filter.JWTFilter;
import com.wyb.shiro.realm.ShiroRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Kunzite
 */
@EnableConfigurationProperties({
        ShiroProperties.class, ShiroSignInProperties.class,
        ShiroCookieProperties.class, ShiroSessionProperties.class,
        ShiroJdbcRealmProperties.class
})
@Configuration
public class ShiroConfiguration {

    @Resource
    private ShiroProperties properties;
    @Resource
    private ShiroSignInProperties signInProperties;

    /**
     * LifecycleBeanPostProcessor，这是个DestructionAwareBeanPostProcessor的子类，
     * 负责org.apache.shiro.util.Initializable类型bean的生命周期的，初始化和销毁。
     * 主要是AuthorizingRealm类的子类，以及EhCacheManager类。
     */
    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        credentialsMatcher.setHashAlgorithmName("MD5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        credentialsMatcher.setHashIterations(2);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }

    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public ShiroRealm shiroRealm() {
        ShiroRealm realm = new ShiroRealm();
//        realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    /**
     * SecurityManager，权限管理，这个类组合了登陆，登出，权限，session的处理，是个比较重要的类。
     */
    @Bean(name = "securityManager")
    @DependsOn(value = {"shiroCacheManager", "rememberMeManager", "mainRealm"})
    public DefaultWebSecurityManager securityManager(CacheManager cacheManager) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());
        securityManager.setCacheManager(cacheManager);
        return securityManager;
    }

    /**
     * AuthorizationAttributeSourceAdvisor，shiro里实现的Advisor类，
     * 内部使用AopAllianceAnnotationsAuthorizingMethodInterceptor来拦截用以下注解的方法。
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     */
    @Bean
    @DependsOn(value = {"securityManager"})
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aASA = new AuthorizationAttributeSourceAdvisor();
        aASA.setSecurityManager(securityManager);
        return aASA;
    }

    /**
     * ShiroFilterFactoryBean，是个factorybean，为了生成ShiroFilter。
     * 它主要保持了三项数据，securityManager，filters，filterChainDefinitionManager。
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new LinkedHashMap<String, Filter>();
        LogoutFilter logoutFilter = new LogoutFilter();
        logoutFilter.setRedirectUrl("/login");
        // 添加自己的过滤器并且取名为jwt
        filters.put("jwt",new JWTFilter());
        // 登录过滤器
//        FormSignInFilter filter = new FormSignInFilter();
//        filter.setLoginUrl(properties.getLoginUrl());
//        filter.setSuccessUrl(properties.getSuccessUrl());
//        filter.setUsernameParam(signInProperties.getUserParam());
//        filter.setPasswordParam(signInProperties.getPasswordParam());
//        filter.setRememberMeParam(signInProperties.getRememberMeParam());
//        filters.put("logout",null);
        shiroFilterFactoryBean.setFilters(filters);

        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionManager.put("/logout", "logout");
//        filterChainDefinitionManager.put("/user/**", "authc,roles[ROLE_USER]");
        filterChainDefinitionManager.put("/events/**", "authc,roles[ROLE_ADMIN]");
//        filterChainDefinitionManager.put("/user/edit/**", "authc,perms[user:edit]");// 这里为了测试，固定写死的值，也可以从数据库或其他配置中读取
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都可以匿名访问-->
        filterChainDefinitionManager.put("login","anon");
        filterChainDefinitionManager.put("/user/**", "jwt");
        // 静态资源
        filterChainDefinitionManager.put("/css/**", "anon");
        filterChainDefinitionManager.put("/img/**", "anon");
        filterChainDefinitionManager.put("/js/**", "anon");
        filterChainDefinitionManager.put("/favicon.ico", "anon");
//        filterChainDefinitionManager.put("/**", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

//        shiroFilterFactoryBean.setSuccessUrl("/");
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        return shiroFilterFactoryBean;
    }

    /****************************** shiro cache start ********************************/

    /**
     * (基于内存的)用户授权信息Cache
     * @ConditionalOnMissingBean（仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean）
     */
    @Bean(name = "shiroCacheManager")
    @ConditionalOnMissingBean(name = "shiroCacheManager")
    public CacheManager memoryCacheManager() {
        return new MemoryConstrainedCacheManager();
    }

    /**
     * (基于redis的)用户授权信息Cache
     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnMissingBean(name="shiroCacheManager")
//    public CacheManager redisCacheManager(RedisTemplate<String, Object> redisTemplate) {
//        return new RedisCacheManager(redisTemplate);
//    }

    /**
     * (基于ehcache的)用户授权信息Cache
     */
//    @Bean(name = "shiroCacheManager")
//    @ConditionalOnClass(name = {"org.apache.shiro.cache.ehcache.EhCacheManager"})
//    @ConditionalOnMissingBean(name = "shiroCacheManager")
//    public CacheManager ehcacheCacheManager() {
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
     *
     * 注释掉该方法时 ，shiro的登录会话session由ehcache保持。
     * 打开该方法时，shiro的登录回话session由redis保持。
     * @paramjedisShiroSessionRepository
     * @return
     */
//    @Bean
//    @DependsOn(value = { "jedisShiroSessionRepository" })
//    public SessionDAO sessionDAO(JedisShiroSessionRepository jedisShiroSessionRepository) {
//        final CustomShiroSessionDAO customShiroSessionDAO = new CustomShiroSessionDAO();
//        customShiroSessionDAO.setShiroSessionRepository(jedisShiroSessionRepository);
//        return customShiroSessionDAO;
//    }



//    @Bean
//    @DependsOn(value = { "objectRedisTemplate" })
//    public JedisShiroSessionRepository jedisShiroSessionRepository(RedisTemplate<String, Object> objectRedisTemplate) {
//        final JedisShiroSessionRepository jedisShiroSessionRepository = new JedisShiroSessionRepository();
//        jedisShiroSessionRepository.setObjectRedisTemplate(objectRedisTemplate);
//        return jedisShiroSessionRepository;
//    }

    /****************************** shiro session end ********************************/

}
