package net.ijiangtao.tech.designpattern.pubsub.spring.common;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * 注册事件
 * @author ijiangtao
 * @create 2019-05-02 12:59
 **/
@Getter
public class RegisterEvent extends ApplicationEvent {

    private String message;

    public RegisterEvent(Object source, String message) {
        super(source);
        this.message = message;
    }

}
