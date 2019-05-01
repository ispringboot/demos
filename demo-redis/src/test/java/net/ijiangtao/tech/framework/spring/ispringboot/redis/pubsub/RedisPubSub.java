package net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

/**
 * Redis Pub/Sub tests
 *
 * @author ijiangtao
 * @create 2019-05-01 19:12
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisPubSub {

    @Autowired
    private RedisMessagePublisher redisMessagePublisher;



    @Test
    public void test1() {


        for (int i = 0; i < 10; i++) {
            String message = "Message=" + UUID.randomUUID();
            redisMessagePublisher.publish(message);
        }


        List<String> messageList = RedisMessageSubscriber.messageList;
        for (String msg : messageList) {
            log.info(msg);
        }
    }

}
