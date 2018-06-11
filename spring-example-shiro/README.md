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

- 错误解决：在lifecycleBeanPostProcessor方法上添加static (要在lifecycleBeanPostProcessor拥有这个方法的类中引入其他配置文件必须加static，不引用则不需要)
- > https://blog.csdn.net/wuxuyang_7788/article/details/70141812

springboot中的动态代理设置 
> https://www.cnblogs.com/tuifeideyouran/p/7696055.html

> https://zhuanlan.zhihu.com/p/29161098

- 集成FormAuthenticationFilter的过滤器添加的名称一定为authc，否则无法拦截

- mybatis-generator rootClass BaseEntity一定要添加到项目的依赖中，即添加到generator的classpath中，springbootWeb项目无法加载到，所以添加到其他的模块中再进行依赖