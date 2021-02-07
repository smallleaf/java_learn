package com.share1024.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/7 10:42
 * \* Description:
 * \
 */
@Service
public class SplitTableService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void init(){
        query();
    }

    public void query(){
        List list =  jdbcTemplate.queryForList("select * from user where user_id = 1000");
        System.out.println("query size " + list.size());
    }

    public void addUser(){
        jdbcTemplate.execute("insert into user(user_id) values(1001)");
    }
}