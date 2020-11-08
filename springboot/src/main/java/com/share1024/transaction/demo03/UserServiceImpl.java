package com.share1024.transaction.demo03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-25
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Transactional
    @Override
    public void save() {
        String sql = "insert into user (username,password) values('yesheng','hahah22')";
        jdbcTemplate.execute(sql);
        int i = 5/0;
    }
}
