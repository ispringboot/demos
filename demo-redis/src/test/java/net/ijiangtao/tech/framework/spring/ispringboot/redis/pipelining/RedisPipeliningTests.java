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

    @Test
    public void contextLoads() {

        log.info("begin .............. ");
        Instant beginTime = Instant.now();

        ListOperations<String, String> listOperations = redisTemplate.opsForList();

        //10 万次 leftPush 用时 17 秒
//        for (int i = 0; i < (10 * 10000); i++) {
//            listOperations.leftPush(RLIST, i + "");
//        }

        //10 万次 leftPop 用时 17 秒
//        for (int i = 0; i < (10 * 10000); i++) {
//            listOperations.leftPop(RLIST);
//        }

        //10 万次 leftPush 用时 17 秒
//        redisTemplate.executePipelined(new RedisCallback<Object>() {
//            @Override
//            public Object doInRedis(RedisConnection var1) throws DataAccessException {
//                for (int i = 0; i < (10 * 10000); i++) {
//                    listOperations.leftPush(RLIST, i + "");
//                }
//                return null;
//            }
//        });

        //10 万次 leftPush 用时 17 秒
        redisTemplate.executePipelined(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection var1) throws DataAccessException {
                for (int i = 0; i < (10 * 10000); i++) {
                    listOperations.leftPop(RLIST);
                }
                return null;
            }
        });


        log.info("time duration : {}", Duration.between(beginTime, Instant.now()).getSeconds());

    }

}
