# 总体思路:

1) 创建invocationHandler
2) 通过Proxy.getProxyClass获得代理类信息
3) 通过反射获得类的构造方法
4) 根据构造方法生成代理对象并将InvocationHandler实例对象传入
5) 通过代理对象调用

> https://blog.csdn.net/yhl_jxy/article/details/80586785

# 深入源码分析:

以Proxy.newProxyInstance()方法为切入点来剖析代理类的生成及代理方法的调用。

    @CallerSensitive
    public static Object newProxyInstance(ClassLoader loader,
                                          Class<?>[] interfaces,
                                          InvocationHandler h)
        throws IllegalArgumentException
    {
	// 如果h为空直接抛出空指针异常，之后所有的单纯的判断null并抛异常，都是此方法
        Objects.requireNonNull(h);
	// 拷贝类实现的所有接口
        final Class<?>[] intfs = interfaces.clone();
	// 获取当前系统安全接口
        final SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
	    // Reflection.getCallerClass返回调用该方法的方法的调用类;loader：接口的类加载器
	    // 进行包访问权限、类加载器权限等检查
            checkProxyAccess(Reflection.getCallerClass(), loader, intfs);
        }
 
        /*
         * Look up or generate the designated proxy class.
	    * 译: 查找或生成指定的代理类
         */
        Class<?> cl = getProxyClass0(loader, intfs);
 
        /*
         * Invoke its constructor with the designated invocation handler.
	     * 译: 用指定的调用处理程序调用它的构造函数。
         */
        try {
            if (sm != null) {
                checkNewProxyPermission(Reflection.getCallerClass(), cl);
            }
	   /*
	    * 获取代理类的构造函数对象。
	    * constructorParams是类常量，作为代理类构造函数的参数类型，常量定义如下:
	    * private static final Class<?>[] constructorParams = { InvocationHandler.class };
	    */
            final Constructor<?> cons = cl.getConstructor(constructorParams);
            final InvocationHandler ih = h;
            if (!Modifier.isPublic(cl.getModifiers())) {
                AccessController.doPrivileged(new PrivilegedAction<Void>() {
                    public Void run() {
                        cons.setAccessible(true);
                        return null;
                    }
                });
            }
	    // 根据代理类的构造函数对象来创建需要返回的代理类对象
            return cons.newInstance(new Object[]{h});
        } catch (IllegalAccessException|InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e) {
            Throwable t = e.getCause();
            if (t instanceof RuntimeException) {
                throw (RuntimeException) t;
            } else {
                throw new InternalError(t.toString(), t);
            }
        } catch (NoSuchMethodException e) {
            throw new InternalError(e.toString(), e);
        }
    }
