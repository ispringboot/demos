package net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Redis Message Subscriber
 * <p>
 * RedisMessageSubscriber implements the Spring Data Redis-provided MessageListener interface
 *
 * @author ijiangtao
 * @create 2019-05-01 19:39
 **/
@Service
public class RedisMessageSubscriber implements MessageListener {

    public static List<String> messageList = new ArrayList<>();

    public void onMessage(Message message, byte[] pattern) {
        messageList.add(message.toString());
    }
}
