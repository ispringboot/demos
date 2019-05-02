package net.ijiangtao.tech.designpattern.pubsub.redis;

/**
 * Message Publisher
 *
 * @author ijiangtao
 * @create 2019-05-01 19:35
 **/
public interface MessagePublisher {
    /**
     * publish message
     * @param message
     */
    void publish(String message);
}
