package com.share1024.hbase.help.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018-12-31
 */
@Configuration
@PropertySource("classpath:hbase.properties")
@ComponentScan("com.share1024.hbase.help")
public class JavaConfig {
}
