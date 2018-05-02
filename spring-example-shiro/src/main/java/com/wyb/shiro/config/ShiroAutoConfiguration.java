package com.wyb.shiro.config;

import com.wyb.shiro.config.properties.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.io.Serializer;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;
import org.apache.shiro.session.mgt.quartz.QuartzSessionValidationScheduler;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.session.mgt.WebSessionManager;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * shiro 基础配置扩展
 *
 * @author Kunzite
 */
//@EnableShiroWebSupport
//@ConditionalOnWebApplication
//@Import(ShiroConfiguration.class)
//@EnableConfigurationProperties({
//        ShiroProperties.class, ShiroSignInProperties.class,
//        ShiroCookieProperties.class, ShiroSessionProperties.class,
//        ShiroJdbcRealmProperties.class
//})
@Configuration
public class ShiroAutoConfiguration {

    @Autowired
    private ShiroProperties properties;

    @Autowired
    private ShiroSignInProperties signInProperties;

    @Autowired
    private ShiroCookieProperties shiroCookieProperties;

    @Autowired
    private ShiroSessionProperties shiroSessionProperties;

    @Autowired
    private ShiroJdbcRealmProperties shiroJdbcRealmProperties;

    @Autowired(required = false)
    private CipherService cipherService;

    @Autowired(required = false)
    private Serializer<PrincipalCollection> serializer;

    @Autowired(required = false)
    private Collection<SessionListener> listeners;


    @Bean(name = "mainRealm")
    @ConditionalOnMissingBean(name = "mainRealm")
    @ConditionalOnProperty(prefix = "shiro.realm.jdbc", name = "enabled", havingValue = "true")
    @DependsOn(value = {"dataSource", "lifecycleBeanPostProcessor", "credentialsMatcher"})
    public Realm jdbcRealm(DataSource dataSource, CredentialsMatcher credentialsMatcher) {
        JdbcRealm jdbcRealm = new JdbcRealm();
        if (shiroJdbcRealmProperties.getAuthenticationQuery() != null) {
            jdbcRealm.setAuthenticationQuery(shiroJdbcRealmProperties.getAuthenticationQuery());
        }
        if (shiroJdbcRealmProperties.getUserRolesQuery() != null) {
            jdbcRealm.setUserRolesQuery(shiroJdbcRealmProperties.getUserRolesQuery());
        }
        if (shiroJdbcRealmProperties.getPermissionsQuery() != null) {
            jdbcRealm.setPermissionsQuery(shiroJdbcRealmProperties.getPermissionsQuery());
        }
        if (shiroJdbcRealmProperties.getSalt() != null) {
            jdbcRealm.setSaltStyle(shiroJdbcRealmProperties.getSalt());
        }
        jdbcRealm.setDataSource(dataSource);
        jdbcRealm.setCredentialsMatcher(credentialsMatcher);
        return jdbcRealm;
    }

    @Bean(name = "mainRealm")
    @ConditionalOnMissingBean(name = "mainRealm")
    @DependsOn(value = {"lifecycleBeanPostProcessor", "credentialsMatcher"})
    public Realm realm(CredentialsMatcher credentialsMatcher) {
        Class<?> realmClass = properties.getRealmClass();
        Realm realm = (Realm) BeanUtils.instantiate(realmClass);
        if (realm instanceof AuthenticatingRealm) {
            ((AuthenticatingRealm) realm).setCredentialsMatcher(credentialsMatcher);
        }
        return realm;
    }


    @Bean
    @ConditionalOnMissingBean(Cookie.class)
    public Cookie rememberMeCookie() {
        SimpleCookie rememberMeCookie = new SimpleCookie();
        rememberMeCookie.setName(signInProperties.getRememberMeParam());
        rememberMeCookie.setMaxAge(shiroCookieProperties.getMaxAge());
        rememberMeCookie.setValue(shiroCookieProperties.getValue());
        rememberMeCookie.setVersion(shiroCookieProperties.getVersion());
        rememberMeCookie.setHttpOnly(shiroCookieProperties.isHttpOnly());
        rememberMeCookie.setSecure(shiroCookieProperties.isSecure());
        return rememberMeCookie;
    }

    @Bean
    @ConditionalOnMissingBean(RememberMeManager.class)
    public RememberMeManager rememberMeManager(Cookie cookie) {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(cookie);
        cookieRememberMeManager.setCipherService(cipherService);
        if (shiroCookieProperties.getCipherKey() != null) {
            cookieRememberMeManager.setCipherKey(shiroCookieProperties.getCipherKey().getBytes());
        } else {
            if (shiroCookieProperties.getEncryptionCipherKey() != null) {
                cookieRememberMeManager.setEncryptionCipherKey(shiroCookieProperties.getEncryptionCipherKey().getBytes());
            }
            if (shiroCookieProperties.getDecryptionCipherKey() != null) {
                cookieRememberMeManager.setDecryptionCipherKey(shiroCookieProperties.getDecryptionCipherKey().getBytes());
            }
        }
        cookieRememberMeManager.setSerializer(serializer);
        return cookieRememberMeManager;
    }

    @Bean
    @ConditionalOnMissingBean
    public SessionDAO sessionDAO(CacheManager cacheManager) {
        EnterpriseCacheSessionDAO sessionDAO = new EnterpriseCacheSessionDAO();
        sessionDAO.setActiveSessionsCacheName(shiroSessionProperties.getActiveSessionsCacheName());
        Class<? extends SessionIdGenerator> idGenerator = shiroSessionProperties.getIdGenerator();
        if (idGenerator != null) {
            SessionIdGenerator sessionIdGenerator = BeanUtils.instantiate(idGenerator);
            sessionDAO.setSessionIdGenerator(sessionIdGenerator);
        }
        sessionDAO.setCacheManager(cacheManager);
        return sessionDAO;
    }

    @Bean(name = "sessionValidationScheduler")
    @DependsOn(value = {"sessionManager"})
    @ConditionalOnClass(name = {"org.quartz.Scheduler"})
    @ConditionalOnMissingBean(SessionValidationScheduler.class)
    public SessionValidationScheduler quartzSessionValidationScheduler(DefaultWebSessionManager sessionManager) {
        QuartzSessionValidationScheduler quartzSessionValidationScheduler = new QuartzSessionValidationScheduler(sessionManager);
        quartzSessionValidationScheduler.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
        quartzSessionValidationScheduler.enableSessionValidation();
        sessionManager.setDeleteInvalidSessions(shiroSessionProperties.isDeleteInvalidSessions());
        sessionManager.setSessionValidationInterval(shiroSessionProperties.getValidationInterval());
        sessionManager.setSessionValidationSchedulerEnabled(shiroSessionProperties.isValidationSchedulerEnabled());
        sessionManager.setSessionValidationScheduler(quartzSessionValidationScheduler);
        return quartzSessionValidationScheduler;
    }

    @Bean(name = "sessionValidationScheduler")
    @DependsOn(value = {"sessionManager"})
    @ConditionalOnMissingBean(SessionValidationScheduler.class)
    public SessionValidationScheduler sessionValidationScheduler(DefaultWebSessionManager sessionManager) {
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
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setCacheManager(cacheManager);
        sessionManager.setGlobalSessionTimeout(shiroSessionProperties.getGlobalSessionTimeout());

        sessionManager.setSessionDAO(sessionDAO);
        sessionManager.setSessionListeners(listeners);
        return sessionManager;
    }



}
