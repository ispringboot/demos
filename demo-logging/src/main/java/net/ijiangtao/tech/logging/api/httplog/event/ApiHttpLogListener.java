package net.ijiangtao.tech.logging.api.httplog.event;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
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

    @Async
    @Override
    public void onApplicationEvent(ApiHttpLogEvent event) {
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            log.error("{}", e);
        }
        log.info("ApiHttpLogListener = {}", JSON.toJSONString(event.getApiHttpLog()));
    }

}
