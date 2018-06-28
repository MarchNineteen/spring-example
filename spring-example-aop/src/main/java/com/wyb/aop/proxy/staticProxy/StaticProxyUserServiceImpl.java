package com.wyb.aop.proxy.staticProxy;

import com.wyb.aop.dao.model.UserDo;
import com.wyb.aop.service.UserService;
import com.wyb.aop.service.impl.UserServiceImpl;

import java.util.List;

/**
 * 静态代理
 * 静态代理只能代理一个具体的类
 *
 * @author Kunzite
 */
//@Service(value = "staticProxyUserServiceImpl")
public class StaticProxyUserServiceImpl implements UserService {

    private UserService userService;

    public StaticProxyUserServiceImpl() {
        userService = new UserServiceImpl();
    }

    @Override
    public List<UserDo> listAll(int pageNum, int pageSize) {
        System.out.println("Proxy doSomethings before)");
        //实际调用
        return userService.listAll(pageNum, pageSize);
    }

    public static void main(String[] args) {
        StaticProxyUserServiceImpl staticProxy = new StaticProxyUserServiceImpl();
        staticProxy.listAll(0, 10);
    }
}
