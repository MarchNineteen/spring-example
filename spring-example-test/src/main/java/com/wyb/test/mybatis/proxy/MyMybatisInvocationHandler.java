package com.wyb.test.mybatis.proxy;

import com.wyb.test.mybatis.annotation.MySelect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.*;

/**
 * @author Marcher丶
 */
public class MyMybatisInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (Object.class.equals(method.getDeclaringClass())) {
            try {
                return method.invoke(this, args);
            } catch (Throwable t) {
                t.printStackTrace();
            }
            //如果传进来的是一个接口（核心)
        } else {
            // 执行具体sql
            MySelect mySelect = method.getAnnotation(MySelect.class);
            String sql = mySelect.value();
            Long id = (Long) args[0];
            sql = sql.replaceAll("\\$\\{.*\\}", String.valueOf(id));
            return excutesql(sql);
        }
        return null;
    }

    public static String excutesql(String sql) {
        String driver = "com.mysql.cj.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://111.231.85.51:3306/shiro";
        // MySQL配置时的用户名
        String user = "shiro";
        // Java连接MySQL配置时的密码
        String password = "shiroPwd";

        try {
            Class cl = Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, user, password);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()) {
                String s = result.getString("real_name");
                return s;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
