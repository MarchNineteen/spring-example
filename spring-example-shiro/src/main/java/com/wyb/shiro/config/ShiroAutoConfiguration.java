package com.wyb.shiro.config;

import com.wyb.shiro.config.properties.*;
import com.wyb.shiro.config.redis.ShiroRedisCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.CipherService;
import org.apache.shiro.io.Serializer;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Import;

import javax.sql.DataSource;

/**
 * shiro 基础配置扩展
 *
 * @author Kunzite
 */
//@EnableShiroWebSupport
//@ConditionalOnWebApplication
@EnableConfigurationProperties({
        ShiroProperties.class, ShiroSignInProperties.class,
        ShiroCookieProperties.class, ShiroSessionProperties.class,
        ShiroJdbcRealmProperties.class
})
@Import(ShiroConfiguration.class)
@Configuration
@Slf4j
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


    /****************************** shiro Realm start ********************************/
    @Bean(name = "mainRealm")
    @ConditionalOnMissingBean(name = "mainRealm")
    @ConditionalOnProperty(prefix = "shiro.realm.jdbc", name = "enabled", havingValue = "true")
    @DependsOn(value = {"dataSource", "lifecycleBeanPostProcessor", "credentialsMatcher"})
    public Realm jdbcRealm(DataSource dataSource, CredentialsMatcher credentialsMatcher) {
        log.info("开始加载jdbcRealm");
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
//        jdbcRealm.setCredentialsMatcher(credentialsMatcher);
        return jdbcRealm;
    }

    @Bean(name = "mainRealm")
    @ConditionalOnMissingBean(name = "mainRealm")
    @DependsOn(value = {"lifecycleBeanPostProcessor", "credentialsMatcher", "shiroCacheManager"})
    public Realm realm(CredentialsMatcher credentialsMatcher, ShiroRedisCacheManager shiroRedisCacheManager) {
        log.info("开始加载自定义Realm");
        Class<?> realmClass = properties.getRealmClass();
        Realm realm = (Realm) BeanUtils.instantiateClass(realmClass);
        if (realm instanceof AuthenticatingRealm) {
            ((AuthenticatingRealm) realm).setCredentialsMatcher(credentialsMatcher);
            ((AuthenticatingRealm) realm).setCacheManager(shiroRedisCacheManager);
        }
        return realm;
    }
    /**
     * ShiroRealm，这是个自定义的认证类，继承自AuthorizingRealm，
     * 负责用户的认证和权限的处理，可以参考JdbcRealm的实现。
     */
//    @Bean(name = "shiroRealm")
//    @DependsOn("lifecycleBeanPostProcessor")
//    public ShiroRealm shiroRealm() {
//        log.info("开始加载shiroRealm");
//        ShiroRealm realm = new ShiroRealm();
////        realm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return realm;
//    }
    /****************************** shiro Realm end ********************************/

    /****************************** shiro RememberMeManager start ********************************/

    @Bean
    @ConditionalOnMissingBean(Cookie.class)
    public Cookie rememberMeCookie() {
        log.info("开始加载Cookie");
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
        log.info("开始加载RememberMeManager");
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

    /****************************** shiro RememberMeManager end ********************************/

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了）
     * HashedCredentialsMatcher，这个类是为了对密码进行编码的，
     * 防止密码在数据库里明码保存，当然在登陆认证的时候，
     * 这个类也负责对form里输入的密码进行编码。
     * 需要使用时，在reaml类中加载这个bean
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        log.info("开始加载credentialsMatcher");
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        credentialsMatcher.setHashAlgorithmName(properties.getHashAlgorithmName());
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        credentialsMatcher.setHashIterations(properties.getHashIterations());
        credentialsMatcher.setStoredCredentialsHexEncoded(properties.isStoredCredentialsHexEncoded());
        return credentialsMatcher;
    }

}
