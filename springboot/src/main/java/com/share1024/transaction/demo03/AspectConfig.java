package com.share1024.transaction.demo03;


import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.ConnectionHolder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionSynchronizationUtils;


//Datasource每次取connection不是一个对象，所以这种方法不太好弄，只有看jdbcTemplate,查看里面有哪些地方可以修改
@Aspect
@Component
public class AspectConfig {


    @Autowired
    private PlatformTransactionManager transactionManager;

    @Autowired
    private DataSource dataSource;


    @Around("execution(* com.share1024.transaction.demo03.UserService.*(..))")
    public  void around(ProceedingJoinPoint joinPoint) throws SQLException {
//
//        TransactionDefinition transactionDefinition = new DefaultTransactionDefinition();
//        TransactionStatus transactionStatus = transactionManager.getTransaction(transactionDefinition);
        Connection connection = dataSource.getConnection();
        try {
            connection.setAutoCommit(false);
            ConnectionHolder connectionHolder = new ConnectionHolder(connection);
            TransactionSynchronizationManager.bindResource(dataSource, connectionHolder);
            joinPoint.proceed();
//            transactionManager.commit(transactionStatus);
            connection.commit();
        }catch (Throwable e){
//            transactionManager.rollback(transactionStatus);
            connection.rollback();
            e.printStackTrace();
        }

    }

}
