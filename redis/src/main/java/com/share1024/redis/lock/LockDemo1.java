package com.share1024.redis.lock;

import com.share1024.redis.util.RedisUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * @author : yesheng
 * @Description :
 * @Date : 2018/11/5
 */
public class LockDemo1 {


    private static final String LOCK = "lock_test";

    private static final int LOCK_TIME = 1000 * 60;

    private static final String REQUEST_ID = "client1";


    @Test
    public void wrongtest1(){

        Long result = RedisUtil.setntx(LOCK,REQUEST_ID);
        /**
         * 如果这步，服务器挂了，那么lock就一直锁着在，那么这个地方就挂了
         */
        if(result == 1){//获得锁成功
            //设置锁失效时间
            RedisUtil.expire(LOCK,LOCK_TIME);
            //进行业务处理

            /**
             * 这种方式会导致任何人都会删除这把锁
             */
            RedisUtil.del(LOCK);

            /**
             * 先判断是否是自己加锁的然后再删除，乍看之下也是可以的，但是
             * 如果 在执行del之前锁过期了，B再加锁，此时del会把B的锁删除掉，也是有问题的
             */
            if(REQUEST_ID.equals(RedisUtil.get(LOCK))){
                RedisUtil.del(LOCK);
            }
        }
    }


    /**
     * 这种方式存在的一个问题就是，System.currentTimeMillis() 必须要是一致的
     */
    public void test2(){

        String lockTime = String.valueOf(System.currentTimeMillis() + LOCK_TIME);

        //获得锁了
        if(RedisUtil.setntx(LOCK,lockTime) == 1){
            //业务处理
            return;
        }
        //获得上锁的时间
        String lockTimeStr = RedisUtil.get(LOCK);

        //表示锁已经过期了
        if(lockTimeStr != null && Long.valueOf(lockTimeStr) < System.currentTimeMillis()){
            //加上新锁的时间
            String oldLockTimeStr = RedisUtil.getSet(LOCK,lockTime);
            if(oldLockTimeStr!= null && lockTimeStr.equals(oldLockTimeStr)){
                //进行业务处理
            }

        }

    }


    private static final String LOCK_SUCCESS = "OK";
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    public void right(){


        //加锁
        String result = RedisUtil.set(LOCK, REQUEST_ID, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, LOCK_TIME);

        if (LOCK_SUCCESS.equals(result)) {
            //进行业务处理
        }
        //解锁
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result2 = RedisUtil.eval(script, Collections.singletonList(LOCK), Collections.singletonList(REQUEST_ID));

    }


    @Test
    public void testEva(){
        //循环插入值
        String[] values = {"haha","ahhaha","sdsds"};
        String script = " for k,v in ipairs(ARGV) do redis.call('lpush','listtest',v) end";
        Object result = RedisUtil.eval(script,new ArrayList<>(), Arrays.asList(values));
    }

}
