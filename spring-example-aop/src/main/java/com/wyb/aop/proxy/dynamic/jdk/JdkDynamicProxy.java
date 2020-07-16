package com.wyb.aop.proxy.dynamic.jdk;

import com.wyb.aop.service.UserService;
import com.wyb.aop.service.impl.UserServiceImpl;

import java.lang.reflect.*;

/**
 * jdk动态代理
 *
 * @author wangyb
 */
public class JdkDynamicProxy implements InvocationHandler {

    // 这个就是我们要代理的真实对象
    private Object target;

    // 构造方法，给我们要代理的真实对象赋初值
    public JdkDynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在代理真实对象前我们可以添加一些自己的操作
        System.out.println("before rent house");

        System.out.println("Method:" + method);

        // 当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        method.invoke(target, args);

        // 在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("after rent house");
        return null;
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        // =========================第一种==========================
        // 1、生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 2、获取动态代理类
        Class proxyClazz = Proxy.getProxyClass(UserService.class.getClassLoader(), UserService.class);
        // 3、获得代理类的构造函数，并传入参数类型InvocationHandler.class
        Constructor constructor = proxyClazz.getConstructor(InvocationHandler.class);
        // 4、通过构造函数来创建动态代理对象，将自定义的InvocationHandler实例传入
        UserService iHello1 = (UserService) constructor.newInstance(new JdkDynamicProxy(new UserServiceImpl()));
        // 5、通过代理对象调用目标方法
        iHello1.listAll(0, 20);


        UserService realUserService = new UserServiceImpl();
        InvocationHandler handler = new JdkDynamicProxy(realUserService);
        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数 userService.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数 handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        // 1、生成$Proxy0的class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

        UserService proxyUserService = (UserService) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realUserService
                .getClass().getInterfaces(), handler);
        System.out.println("-----------JdkProxy-------------");
        System.out.println(proxyUserService.getClass().getName());
        proxyUserService.listAll(0, 10);
    }
}
