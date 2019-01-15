package com.share1024.redis.apply;

import com.share1024.redis.util.RedisUtil;

/**
 * @author : yesheng
 * @Description : redis排行榜
 * 1w条，大于1w条自动过期
 * @Date : 2018/11/5
 */
public class Leaderboard {


    private static final int number = 10000;

    private static final String RANK = "rank";

    public void start(){

        double score = 1000;
        RedisUtil.zincrby(RANK,score,"ye");


    }
}
