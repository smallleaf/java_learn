package com.share1024.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Date;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/12/11
 */
public class Helloworld {

    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        //蜂窝下划线转化
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        //去掉null
        mapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        mapper.disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS);
        User user = new User();
        user.setBigHouse("xixii");
        user.setDate(new Date());
        try {
            String test = mapper.writeValueAsString(user);

            System.out.println(test);

            System.out.println(mapper.readValue(test,User.class).toString());

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
