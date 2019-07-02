package net.ijiangtao.tech.demo.httplog.v1.httplog.event;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.httplog.v1.httplog.persistence.HttpLogPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 发送优惠券
 *
 * @author ijiangtao
 * @create 2019-05-02 13:07
 **/
@Component
@Slf4j
public class ApiHttpLogListener implements ApplicationListener<ApiHttpLogEvent> {

    @Autowired
    @Qualifier("httpLogPersistenceLogFile")
    private HttpLogPersistence httpLogPersistence;

    @Async
    @Override
    public void onApplicationEvent(ApiHttpLogEvent event) {
        try {
            httpLogPersistence.save(event.getApiHttpLog());
        } catch (Exception e) {
            log.error("ApiHttpLogListenerError", e);
        }
    }

}
