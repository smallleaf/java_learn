package com.share1024.split;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * \* @Author: yesheng
 * \* Date: 2021/2/7 11:41
 * \* Description:
 * \
 */
@Service
public class WeekSplitShardingService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init(){
        jdbcTemplate.execute("insert into `order`(create_time) values(1612669548000)");
    }
}