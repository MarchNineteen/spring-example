package com.wyb.mybatis.multidatasource.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.wyb.mybatis.multidatasource.config.MasterConfig;

/**
 * mysql-connection 8.0.11之后
 *
 * @author Marcher丶
 */
@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.wyb.mybatis.multidatasource.dao.mapper.master", sqlSessionTemplateRef = "masterSqlSessionTemplate")
public class MasterDbConfig {

    // 配置数据源
    @Bean(name = "masterDataSource") // test1DataSource
    public DataSource testDataSource(MasterConfig masterConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(masterConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(masterConfig.getPassword());
        mysqlXaDataSource.setUser(masterConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("masterDataSource");

        xaDataSource.setMinPoolSize(masterConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(masterConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(masterConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(masterConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(masterConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(masterConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(masterConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(masterConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("masterDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "masterSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("masterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
