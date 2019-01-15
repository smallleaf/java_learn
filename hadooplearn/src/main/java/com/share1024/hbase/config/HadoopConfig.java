package com.share1024.hbase.config;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.hadoop.config.annotation.EnableHadoop;
import org.springframework.data.hadoop.config.annotation.SpringHadoopConfigurerAdapter;
import org.springframework.data.hadoop.config.annotation.builders.HadoopConfigConfigurer;
import org.springframework.data.hadoop.hbase.HbaseTemplate;


/**
 * @author : yesheng
 * @Description :
 * @Date : 2017/12/29
 */
@Configuration
@EnableHadoop
@ComponentScan(basePackages = "com.share1024.hbase")
public class HadoopConfig extends SpringHadoopConfigurerAdapter {

    @Override
    public void configure(HadoopConfigConfigurer config) throws Exception {
        config.fileSystemUri("hdfs://master:9000");
    }


    public org.apache.hadoop.conf.Configuration hBaseConfiguration(){
        org.apache.hadoop.conf.Configuration hBaseConfiguration = HBaseConfiguration.create();
        return hBaseConfiguration;
    }


    @Bean
    public HbaseTemplate hbaseTemplate(){
        HbaseTemplate hbaseTemplate = new HbaseTemplate(hBaseConfiguration());
        return  hbaseTemplate;
    }

}
