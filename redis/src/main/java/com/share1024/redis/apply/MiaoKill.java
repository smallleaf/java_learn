package com.share1024.redis.apply;

import com.share1024.redis.util.RedisUtil;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.IOException;
import java.util.List;

/**
 * @author : yesheng
 * @Description : redis来实现秒杀
 *
 * @Date : 2018/11/6
 */
public class MiaoKill {





    private static final String MIAO_KILL = "miao_kill";

    /**
     * 使用list，redis是单线程，按照顺序将用户排列进去
     * pop是原子的，所以不管并发如何，都是一个个取的，
     *
     *
     *
     * 如果用户特别多的话，队列将占用特别大的内存
     */
    public void test(){

        //用户来抢购放入队列enter
        enter();


        //取用户

        //商品队列取商品

    }


    /**
     * 采用事务
     */
    public void test2() {

        //watch key 如果key发生变化，取消事务

        //开启事务

        //执行处理，商品减一

        //执行事务
    }


    /**
     * 进入队列
     */
    public void enter(){
        //判断是否存在，等等其他操作
        RedisUtil.lpush(MIAO_KILL,"yonghu");
    }




    @Test
    public void test5() throws IOException {


        RedisUtil.set(MIAO_KILL,10+"");
        Jedis jedis = RedisUtil.getJedis();
        jedis.watch(MIAO_KILL);
        Integer miaoSha = Integer.valueOf(RedisUtil.get(MIAO_KILL));
        if(miaoSha >0){
            Transaction transaction = jedis.multi();

            transaction.incrBy(MIAO_KILL,-1);

            List<Object> result  = transaction.exec();
            if(result == null){
                System.out.println("秒杀失败");
            }else{
                System.out.println("秒杀成功");
            }
        }
    }

    private void miaosha(){


    }
}
