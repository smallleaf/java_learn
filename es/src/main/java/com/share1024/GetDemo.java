package com.share1024;

import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.get.MultiGetResponse;
import org.junit.Test;

/**
 * \* User: yesheng
 * \* Date: 2019/7/12 11:37
 * \* Description:
 * \
 */
public class GetDemo {

    private static final String INDEX = "user_idx";

    private static final String TYPE = "user";

    @Test
    public void get(){
        GetResponse response = EsConfig.client.prepareGet("user_idx","user","1").get();
        System.out.println(response.toString());
    }


    @Test
    public void multiGet(){

        MultiGetResponse multiGetItemResponses = EsConfig.client.prepareMultiGet()
                .get();


    }



}