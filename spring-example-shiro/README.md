# springboot 使用tk.mybatis shiro jwt freemaker 实现权限系统
-  @EnableConfigurationProperties加载main上没有作用
```
@EnableConfigurationProperties({
          ShiroProperties.class, ShiroSignInProperties.class,
          ShiroCookieProperties.class, ShiroSessionProperties.class,
          ShiroJdbcRealmProperties.class
 })
```
