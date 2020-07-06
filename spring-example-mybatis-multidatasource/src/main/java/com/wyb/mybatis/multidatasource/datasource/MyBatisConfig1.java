package com.wyb.mybatis.multidatasource.datasource;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.wyb.mybatis.multidatasource.config.DB1Config;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * mysql-connection 8.0.11之后
 *
 * @author Marcher丶
 */
@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.wyb.mybatis.multidatasource.dao.mapper.test1", sqlSessionTemplateRef = "test1SqlSessionTemplate")
public class MyBatisConfig1 {

    // 配置数据源
    @Bean(name = "test1DataSource")  //test1DataSource
    public DataSource testDataSource(DB1Config testConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(testConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(testConfig.getPassword());
        mysqlXaDataSource.setUser(testConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("test1DataSource");

        xaDataSource.setMinPoolSize(testConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(testConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(testConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(testConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(testConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(testConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(testConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(testConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "test1SqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("test1DataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "test1SqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("test1SqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
