package com.wyb.mybatis.multidatasource.datasource;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.wyb.mybatis.multidatasource.config.SlaveConfig;

/**
 * mysql-connection 8.0.11之后
 *
 * @author Marcher丶
 */
@Configuration
// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
@MapperScan(basePackages = "com.wyb.mybatis.multidatasource.dao.mapper.slave", sqlSessionTemplateRef = "slaveSqlSessionTemplate")
public class SlaveDbConfig {

    // 配置数据源
    @Bean(name = "slaveDataSource")
    public DataSource testDataSource(SlaveConfig slaveConfig) throws SQLException {
        DruidDataSource mysqlXaDataSource = new DruidDataSource();
        mysqlXaDataSource.setUrl(slaveConfig.getUrl());
        mysqlXaDataSource.setUseGlobalDataSourceStat(true);
        mysqlXaDataSource.setPassword(slaveConfig.getPassword());
        mysqlXaDataSource.setUsername(slaveConfig.getUsername());
        return mysqlXaDataSource;
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        return bean.getObject();
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
