package net.ijiangtao.tech.designpattern.pubsub.spring.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 注册事件发布器
 * @author ijiangtao
 * @create 2019-05-02 13:01
 **/
@Component
@Slf4j
public class RegisterEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(final String message) {
        log.info("publis a RegisterEvent,message:{}", message + " time: " + LocalTime.now());
        RegisterEvent registerEvent = new RegisterEvent(this, message);
        applicationEventPublisher.publishEvent(registerEvent);
    }
}
