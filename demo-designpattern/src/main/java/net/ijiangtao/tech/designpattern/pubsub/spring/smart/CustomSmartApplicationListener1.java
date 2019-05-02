package net.ijiangtao.tech.designpattern.pubsub.spring.smart;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * SmartApplicationListener
 *
 * @author ijiangtao
 * @create 2019-05-02 15:32
 **/
@Component
@Slf4j
public class CustomSmartApplicationListener1 implements SmartApplicationListener {


    /**
     * 自定义支持的事件类型
     * @param eventType
     * @return
     */
    @Override
    public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
        return eventType == SmartEvent.class;
    }

    /**
     * 定义支持的事件源类型
     * @param sourceType
     * @return
     */
    @Override
    public boolean supportsSourceType(Class<?> sourceType) {
        return sourceType == String.class;
    }

    /**
     * 自定义优先级别
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        log.info("CustomSmartApplicationListener {}",applicationEvent.getSource());
    }

}
