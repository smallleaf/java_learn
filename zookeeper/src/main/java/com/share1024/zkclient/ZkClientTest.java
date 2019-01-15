package com.share1024.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/10/19
 */
public class ZkClientTest {


    private ZkClient zkClient;

    private final String IP_PORT = "172.16.168.200:2181,172.16.168.200:2182,172.16.168.200:2183";

    private final int SESSION_TIMEOUT = 3000;

    private final int CONNECTION_TIMEOUT = 3000;

    private final String NODE = "/zkclient_test";

    @Before
    public void init(){
        zkClient = new ZkClient(IP_PORT,SESSION_TIMEOUT,CONNECTION_TIMEOUT,new MyZkSerializer());

        try {
            boolean isExist = zkClient.exists(NODE);
            if(!isExist)
                zkClient.createPersistent(NODE,"abc");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void getData(){

        zkClient.subscribeDataChanges(NODE, new IZkDataListener() {
            @Override
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("=handleDataChange=="+dataPath);
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("=handleDataDeleted=="+dataPath);
            }
        });

        System.out.println((String) zkClient.readData(NODE));

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }






}
