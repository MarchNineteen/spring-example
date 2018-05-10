package com.wyb.test.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 数据库连接demo
 *
 * @author Kunzite
 */
public class Db {

    public static void main(String[] args)  {
        String driver = "com.mysql.jdbc.Driver";
        // URL指向要访问的数据库名scutcs
        String url = "jdbc:mysql://123.206.88.40:3306/Test";
        // MySQL配置时的用户名
        String user = "root";
        // Java连接MySQL配置时的密码
        String password = "123456";

        try {
            Class cl = Class.forName(driver);
            Connection connection = DriverManager.getConnection(url,user,password);
            connection.setAutoCommit(true);
            Statement statement = connection.createStatement();
//            String sql= "select * from c_user";
            String sql = "update c_user SET username = 'wyb' where id  = 1  ";
            int result = statement.executeUpdate(sql);
//            while (result.next()){
//                System.out.println(result.getString("realname"));
//            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
