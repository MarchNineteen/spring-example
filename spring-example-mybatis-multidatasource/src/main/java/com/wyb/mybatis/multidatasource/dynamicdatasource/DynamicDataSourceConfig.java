//package com.wyb.mybatis.multidatasource.dynamicdatasource;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.sql.DataSource;
//
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DataSourceTransactionManager;
//
//import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
//
///**
// * @author Marcher丶
// */
//@Configuration
//// basePackages 最好分开配置 如果放在同一个文件夹可能会报错
//@MapperScan(basePackages = "com.wyb.mybatis.multidatasource.dao.mapper", sqlSessionTemplateRef = "testSqlSessionTemplate")
//public class DynamicDataSourceConfig {
//
//    /**
//     * 主库
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.master")
//    public DataSource master() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * 从库
//     */
//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    public DataSource slaver() {
//        return DruidDataSourceBuilder.create().build();
//    }
//
//    /**
//     * 实例化数据源路由
//     */
//    @Bean
//    public DynamicDataSource dynamicDB(@Qualifier("master") DataSource masterDataSource,
//            @Autowired(required = false) @Qualifier("slaver") DataSource slaveDataSource) {
//
//        DynamicDataSource dynamicDataSource = new DynamicDataSource();
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DataSourceEnum.MASTER.getDataSourceName(), masterDataSource);
//        if (slaveDataSource != null) {
//            targetDataSources.put(DataSourceEnum.SLAVE.getDataSourceName(), slaveDataSource);
//        }
//        dynamicDataSource.setTargetDataSources(targetDataSources);
//        dynamicDataSource.setDefaultTargetDataSource(masterDataSource);
//        return dynamicDataSource;
//    }
//
//    /**
//     * 配置sessionFactory
//     *
//     * @param dynamicDataSource
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    public SqlSessionFactory sessionFactory(@Qualifier("dynamicDB") DataSource dynamicDataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setMapperLocations(
//                new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*Mapper.xml"));
//        bean.setDataSource(dynamicDataSource);
//        return bean.getObject();
//    }
//
//    /**
//     * 创建sqlTemplate
//     *
//     * @param sqlSessionFactory
//     * @return
//     */
//    @Bean
//    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//
//    /**
//     * 事务配置
//     *
//     * @param dynamicDataSource
//     * @return
//     */
//    @Bean(name = "dataSourceTx")
//    public DataSourceTransactionManager dataSourceTransactionManager(
//            @Qualifier("dynamicDB") DataSource dynamicDataSource) {
//        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
//        dataSourceTransactionManager.setDataSource(dynamicDataSource);
//        return dataSourceTransactionManager;
//    }
//}
