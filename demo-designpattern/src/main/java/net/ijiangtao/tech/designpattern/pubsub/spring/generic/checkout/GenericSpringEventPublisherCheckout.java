package net.ijiangtao.tech.designpattern.pubsub.spring.generic.checkout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * GenericSpringEventPublisher
 *
 * @author ijiangtao
 * @create 2019-05-02 13:55
 **/
@Component
@Slf4j
public class GenericSpringEventPublisherCheckout {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(final Long userId, boolean success) {

        log.info("publis a GenericSpringEventPublisher, userId:{}", userId + " time: " + LocalTime.now());

        GenericSpringEventCheckout eventCheckout = new GenericSpringEventCheckout(userId, success);

        applicationEventPublisher.publishEvent(eventCheckout);
    }

}
