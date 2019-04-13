package net.ijiangtao.tech.framework.spring.ispringboot.redis.pipelining;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.Instant;

/**
 * Redis Pipelining
 *
 * @author ijiangtao
 * @create 2019-04-13 22:32
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisPipeliningTests {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String RLIST = "test_redis_list";

    /**
     * 队列 10万次leftPush+10万次rightPop，使用和不使用管道耗时对比，用时对比
     */
    @Test
    public void test1() {

        //10万次leftPush+10万次rightPop 用时 5秒 7秒
        Instant beginTime2 = Instant.now();
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                for (int i = 0; i < (10 * 10000); i++) {
                    connection.lPush(RLIST.getBytes(),(i + "").getBytes());
                }
                for (int i = 0; i < (10 * 10000); i++) {
                    connection.rPop(RLIST.getBytes());
                }
                return null;
            }
        });
        log.info(" ***************** pipeling time duration : {}", Duration.between(beginTime2, Instant.now()).getSeconds());


        log.info("begin .............. ");
        //10万次leftPush+10万次rightPop 用时 33秒 34秒
        Instant beginTime1 = Instant.now();
        ListOperations<String, String> listOperations = redisTemplate.opsForList();
        for (int i = 0; i < (10 * 10000); i++) {
            listOperations.leftPush(RLIST, i + "");
        }
        for (int i = 0; i < (10 * 10000); i++) {
            listOperations.rightPop(RLIST);
        }
        log.info(" ################# no pipeling time duration : {}", Duration.between(beginTime1, Instant.now()).getSeconds());


    }

}
