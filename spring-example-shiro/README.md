# springboot 使用tk.mybatis shiro jwt freemaker 实现权限系统
-  @EnableConfigurationProperties加载main上没有作用,要具体到类名
```
@EnableConfigurationProperties({
          ShiroProperties.class, ShiroSignInProperties.class,
          ShiroCookieProperties.class, ShiroSessionProperties.class,
          ShiroJdbcRealmProperties.class
 })
```

-  ShiroProperties.class只能ShiroAutoConfiguration加载得到，ShiroConfiguration
一直加载不到，不知道为什么，快疯了（看日志ShiroAutoConfiguration加载时，properties类会加载，
而ShiroConfiguration加载时却不会去加载properties类）**经测试是lifecycleBeanPostProcessor这个bean的原因 有这个bean就加载不到没有就可以，和他的生命周期有关**