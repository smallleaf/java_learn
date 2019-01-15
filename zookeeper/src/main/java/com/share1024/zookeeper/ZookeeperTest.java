package com.share1024.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/19
 */
public class ZookeeperTest {

    private ZooKeeper zk;

    private final String IP_PORT = "172.16.168.200:2181,172.16.168.200:2182,172.16.168.200:2183";

    private final int SESSION_TIMEOUT = 3000;

    private final String NODE = "/zookeeper_test";

    @Before
    public void create() throws IOException, KeeperException, InterruptedException {

        zk = new ZooKeeper(IP_PORT, SESSION_TIMEOUT,(event)->{
            System.out.println(event.getType());
        });

        Stat stat = zk.exists(NODE,event -> {
            System.out.println("create:"+event.getType());
        });
        if(stat == null){
            zk.create(NODE,"test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

    }




    @Test
    public void getDate() throws Exception{
        System.out.println("==="+new String(zk.getData(NODE,true,null)));
        // System.in.read();
    }

    @Test
    public void setDate () throws  Exception{
        Stat stat = zk.exists(NODE,true);
        if(null != stat){
            zk.setData(NODE,"hehahaha".getBytes(),stat.getVersion());
        }
        getDate();
    }


    @After
    public void destory(){
        if(null != zk){
            try {
                zk.close();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
