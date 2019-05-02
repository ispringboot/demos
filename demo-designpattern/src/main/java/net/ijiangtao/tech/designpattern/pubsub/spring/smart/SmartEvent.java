package net.ijiangtao.tech.designpattern.pubsub.spring.smart;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * event
 *
 * @author ijiangtao
 * @create 2019-05-02 15:33
 **/
@Getter
public class SmartEvent extends ApplicationEvent {

    private String message;

    public SmartEvent(Object source, String message) {
        super(source);
    }

}
