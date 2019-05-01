package net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

/**
 * Redis message publisher
 *
 * @author ijiangtao
 * @create 2019-05-01 19:36
 **/
@Service
public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

//    @Autowired
    private ChannelTopic topic = new ChannelTopic("messageQueue");

    /*@Bean
    ChannelTopic topic() {
        return new ChannelTopic("messageQueue");
    }*/

    public RedisMessagePublisher() {

    }

    public RedisMessagePublisher(RedisTemplate<String, String> redisTemplate, ChannelTopic topic) {
        this.redisTemplate = redisTemplate;
        this.topic = topic;
    }


    public void publish(String message) {
        redisTemplate.convertAndSend(topic.getTopic(), message);
    }
}
