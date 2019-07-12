package com.share1024;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * \* User: yesheng
 * \* Date: 2019/7/12 11:25
 * \* Description:
 * \
 */
public class IndexDemo {

    private static final String INDEX = "user_idx";

    private static final String TYPE = "user";

    class  User{
        private String user;
        private String location;
        private int age;

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @Test
    public void create() throws IOException {
        IndexResponse response = EsConfig.client.prepareIndex("user_idx","user","1")
                .setSource(jsonBuilder().startObject().field("user","yesheng")
                .field("age",24).field("location","hshdhdhs").endObject()).get();
        System.out.println(response.toString());
    }

    @Test
    public void createByJson(){
        User user = new User();
        user.setAge(45);
        user.setLocation("是的是的多所");
        user.setUser("yesheng");
        IndexResponse response = EsConfig.client.prepareIndex("user_idx","user")
                .setSource(JSON.toJSONString(user), XContentType.JSON).get();
        System.out.println(response.toString());
    }

    @Test
    public void createBatchJson(){
        BulkRequestBuilder bulkRequest = EsConfig.client.prepareBulk();
        for(int i = 0;i< 10000;i++){
            User user = new User();
            user.setAge(new Random().nextInt(60));
            user.setLocation("是的是的多所"+i);
            user.setUser("yesheng"+i);
            bulkRequest.add(EsConfig.client.prepareIndex("user_idx","user")
                    .setSource(JSON.toJSONString(user), XContentType.JSON));
        }
        System.out.println("===");
        BulkResponse bulkResponse = bulkRequest.get();

        System.out.println(bulkResponse.toString());
    }



}