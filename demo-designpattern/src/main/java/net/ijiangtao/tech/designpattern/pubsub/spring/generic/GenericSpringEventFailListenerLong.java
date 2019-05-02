package net.ijiangtao.tech.designpattern.pubsub.spring.generic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author ijiangtao
 * @create 2019-05-02 13:52
 **/
@Component
@Slf4j
public class GenericSpringEventFailListenerLong {

    @EventListener(condition = "#event.success eq false")
    public void handle(GenericSpringEvent<Long> event) {
        log.info("Handling generic event  Fail (conditional). {}",event.getWhat());
    }

}