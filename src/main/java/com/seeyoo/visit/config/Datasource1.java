package com.seeyoo.visit.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = "com.seeyoo.visit.mapper", sqlSessionTemplateRef = "visitSqlSessionTemplate")
public class Datasource1 {
    // 声明bean
    @Bean(name = "visitDataSource")
    // 指明读取的配置
    @ConfigurationProperties(prefix = "spring.datasource.visit")
    // 设置为主数据源
    @Primary
    /*
     * 声明数据源配置
     */
    public DataSource visitDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "visitSqlSessionFactory")
    @Primary
    /**
     * 使用声明的数据源，创建sqlSession工厂
     */
    public SqlSessionFactory visitSqlSessionFactory(@Qualifier("visitDataSource") DataSource dataSource)
            throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        /*
         * 当mybatis采用映射配置文件的方式时，指明该数据源需要是扫描的xml文件路径
         */
        bean.setDataSource(dataSource);
         bean.setMapperLocations( new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "visitTransactionManager")
    @Primary
    /**
     * 声明数据源有自己的事务管理
     */
    public DataSourceTransactionManager visitTransactionManager(@Qualifier("visitDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "visitSqlSessionTemplate")
    @Primary
    /**
     * 声明SqlSessionTemplate由指定的SqlSession工厂创建
     */
    public SqlSessionTemplate visitSqlSessionTemplate(
            @Qualifier("visitSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
