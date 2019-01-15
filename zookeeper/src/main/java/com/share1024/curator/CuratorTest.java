package com.share1024.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/19
 */
public class CuratorTest {

    private CuratorFramework framework;

    private final String IP_PORT = "172.16.168.200:2181,172.16.168.200:2182,172.16.168.200:2183";

    private final int SESSION_TIMEOUT = 3000;

    private final int CONNECTION_TIMEOUT = 3000;

    private final String NODE = "/curator_test";

    @Before
    public void init(){
        framework = CuratorFrameworkFactory.
                newClient(IP_PORT,SESSION_TIMEOUT,CONNECTION_TIMEOUT,new ExponentialBackoffRetry(1000,10));
        framework.start();
        // 获取连接状态
        CuratorFrameworkState frameworkState = framework.getState();
        System.out.println(frameworkState);
    }

    @Test
    public void create(){
        try {
            String rs = framework.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).inBackground((client,event)->{

            }).forPath(NODE,"hhahaa".getBytes());
            System.out.println(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getData() throws Exception {
        System.out.println(new String(framework.getData().forPath(NODE)));
    }


    @Test
    public void listener() throws Exception {
        framework.getData().usingWatcher((Watcher) event -> {
            System.out.println(event.getType());
        }).forPath(NODE);
        System.in.read();
    }

    @Test
    public void listenerChild() throws Exception {
        framework.getChildren().usingWatcher((Watcher) event -> {
            System.out.println(event.getType());
        }).forPath(NODE);

        System.in.read();
    }




}
