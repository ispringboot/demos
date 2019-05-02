package net.ijiangtao.tech.designpattern.pubsub.spring;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.designpattern.pubsub.spring.async.annotation.RegisterEventPublisherAsyncAnnotation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Spring Events
 *
 * @author ijiangtao
 * @create 2019-05-02 12:53
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@EnableAsync
public class SpringEventsAsyncAnnotationTests {

    @Autowired
    private RegisterEventPublisherAsyncAnnotation registerEventPublisherAsyncAnnotation;

    @Test
    public void test2() {
        registerEventPublisherAsyncAnnotation.publish(" Danny is here (Async).");
        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

}
