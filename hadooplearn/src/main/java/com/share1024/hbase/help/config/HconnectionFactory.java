package com.share1024.hbase.help.config;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: Sorin
 * @Descriptions: 加载Hbase连接
 * @Date: Created in 2018/3/21
 */
@Component
public class HconnectionFactory implements InitializingBean {

    protected final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    private static Configuration conf = HBaseConfiguration.create();

    public static Connection connection;

    @Autowired
    private Environment evn;

    @Override
    public void afterPropertiesSet(){
        String zkQuorum = evn.getProperty("hbase.zookeeper.quorum");
        String hBaseMaster = evn.getProperty("hbase.master");
        String zkPort = evn.getProperty("hbase.zookeeper.property.clientPort");
        String znode = evn.getProperty("zookeeper.znode.parent");
        conf.set("hbase.zookeeper.quorum", zkQuorum);
        conf.set("hbase.zookeeper.property.clientPort", zkPort);
        conf.set("zookeeper.znode.parent", znode);
        try {
            connection = ConnectionFactory.createConnection(conf);
            logger.info("获取connectiont连接成功！");
        } catch (IOException e) {
            e.printStackTrace ();
            logger.error("获取connectiont连接失败！");
        }
    }
}
