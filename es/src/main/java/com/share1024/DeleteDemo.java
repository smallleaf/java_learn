package com.share1024;

import org.elasticsearch.action.delete.DeleteResponse;

/**
 * \* User: yesheng
 * \* Date: 2019/7/12 11:40
 * \* Description:
 * \
 */
public class DeleteDemo {

    public static void main(String[] args) {
        DeleteResponse response = EsConfig.client.prepareDelete("user_idx","user","1").get();
        System.out.println(response.toString());
    }

}