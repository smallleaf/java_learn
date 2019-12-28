package com.share1024.java.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Names;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2019-12-28
 */
public class MyAppModule extends AbstractModule {


    @Override
    protected void configure() {
        bind(UserService.class).annotatedWith(Names.named("userService2")).to(UserService2Impl.class);
        bind(UserService.class).annotatedWith(Names.named("userService")).to(UserServiceImpl.class);
        bind(BillService.class).to(BillServiceImpl.class);
        bindCustomConstant();
    }


   private void bindCustomConstant(){
        bind(String.class).annotatedWith(Names.named("username")).toInstance("yesheng");
        bind(Integer.class).annotatedWith(Names.named("size")).toInstance(30);
   }


   @Provides
   JdbcSetting jdbcSetting(){
        JdbcSetting jdbcSetting = new JdbcSetting();
        jdbcSetting.setUsername("yesheng");
        jdbcSetting.setPassword("yesheng");
        jdbcSetting.setPort(3306);
        return jdbcSetting;
   }
}
