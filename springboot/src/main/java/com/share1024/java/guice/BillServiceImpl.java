package com.share1024.java.guice;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-12-28
 */
public class BillServiceImpl implements BillService {

    @Inject
    @Named("userService")
    private UserService userService;

    @Inject
    @Named("username")
    private String userName;


    @Inject
    @Named("size")
    private Integer size;

    @Inject
    private JdbcSetting jdbcSetting;

    @Override
    public void bill() {
        userService.process();
        System.out.println("username:"+userName + ",size:"+size);
        System.out.println(jdbcSetting.toString());
    }



}
