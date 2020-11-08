package com.share1024.transaction.demo02;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-25
 */
@Configuration
public class JavaConfig {


    @Bean
    @Qualifier("userService")
    public UserService userService(){
        return new UserServiceImpl();
    }

    @Bean
    public DataSource dataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123456");
        return dataSource;
    }


    @Bean
    public DataSourceTransactionManager transactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public JdbcTemplate jdbcTemplate(){
        return new JdbcTemplate(dataSource());
    }


    @Bean
    public TransactionProxyFactoryBean proxyFactoryBean(UserService userService){
        TransactionProxyFactoryBean proxyFactoryBean = new TransactionProxyFactoryBean();
        proxyFactoryBean.setTransactionManager(transactionManager());
        Properties properties = new Properties();
        properties.setProperty("*","PROPAGATION_REQUIRED");
        proxyFactoryBean.setTransactionAttributes(properties);
        proxyFactoryBean.setProxyInterfaces(UserService.class.getInterfaces());
        proxyFactoryBean.setTarget(userService);
        return proxyFactoryBean;
    }
}
