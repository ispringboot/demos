package net.ijiangtao.tech.demo.httplog.v1.httplog.event;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.httplog.v1.httplog.model.ApiHttpLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author ijiangtao
 * @create 2019-05-02 13:01
 **/
@Component
@Slf4j
public class ApiHttpLogEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(ApiHttpLog apiHttpLog) {
        ApiHttpLogEvent apiHttpLogEvent = new ApiHttpLogEvent(this, apiHttpLog);
        applicationEventPublisher.publishEvent(apiHttpLogEvent);
    }
}
