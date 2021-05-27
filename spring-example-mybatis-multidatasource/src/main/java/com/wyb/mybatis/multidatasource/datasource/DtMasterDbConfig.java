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
import com.wyb.mybatis.multidatasource.config.DtMasterConfig;

/**
 * mysql-connection 8.0.11之后
 *
 * @author Marcher丶
 */
@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.wyb.mybatis.multidatasource.dao.mapper.master_dt", sqlSessionTemplateRef = "dtMasterSqlSessionTemplate")
public class DtMasterDbConfig {

    // 配置数据源
    @Bean(name = "dtMasterDataSource") // test1DataSource
    public DataSource testDataSource(DtMasterConfig dtMasterConfig) throws SQLException {
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(dtMasterConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(dtMasterConfig.getPassword());
        mysqlXaDataSource.setUser(dtMasterConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);

        // 将本地事务注册到创 Atomikos全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName("dtMasterDataSource");

        xaDataSource.setMinPoolSize(dtMasterConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(dtMasterConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(dtMasterConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(dtMasterConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(dtMasterConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(dtMasterConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(dtMasterConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(dtMasterConfig.getTestQuery());
        return xaDataSource;
    }

    @Bean(name = "dtMasterSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dtMasterDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "dtMasterSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("dtMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
