package com.share1024.masterslaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/5 16:53
 * \* Description:
 * \
 */
@Service
public class MasterSlaverService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init(){
        addData();
        queryData();
    }
    public void addData(){
        jdbcTemplate.execute("insert into test(content) values('hahahaahahahah')");
        System.out.println("add data");
    }


    public void queryData(){
        List list = jdbcTemplate.queryForList("select * from test");
        System.out.println("query data "+list.size());

    }
}