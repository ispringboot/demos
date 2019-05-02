package net.ijiangtao.tech.designpattern.pubsub.spring.async.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * 发送注册成功邮件提醒
 *
 * @author ijiangtao
 * @create 2019-05-02 13:07
 **/
@Component
@Slf4j
public class SendRegisterEmailListenerAsyncAnnotation implements ApplicationListener<RegisterEvent> {

    @Async
    @Override
    public void onApplicationEvent(RegisterEvent event) {
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            log.error("{}", e);
        }

        log.info("SendRegisterEmailListener message: " + event.getMessage()+" time: "+ LocalTime.now());
    }

}
