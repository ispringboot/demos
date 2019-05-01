package net.ijiangtao.tech.framework.spring.ispringboot.redis;

import net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub.MessagePublisher;
import net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub.RedisMessagePublisher;
import net.ijiangtao.tech.framework.spring.ispringboot.redis.pubsub.RedisMessageSubscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * config
 *
 * @author ijiangtao
 * @create 2019-05-01 19:57
 **/
@Configuration
@ComponentScan("net.ijiangtao.tech.framework.spring.ispringboot.redis")
@EnableRedisRepositories(basePackages = "net.ijiangtao.tech.framework.spring.ispringboot")
@PropertySource("classpath:application.properties")
public class RedisConfig {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    /*@Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        final RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<>(Object.class));
        return template;
    }
*/
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisMessageSubscriber());
    }

   /* @Bean
    RedisMessageListenerContainer redisContainer() {
        final RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(jedisConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }*/

    @Bean
    RedisMessageListenerContainer redisContainer() {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(redisTemplate.getConnectionFactory());
        container.addMessageListener(messageListener(), topic());
        return container;
    }

    @Bean
    MessagePublisher redisPublisher() {
        return new RedisMessagePublisher(redisTemplate, topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("messageQueue");
    }

}
