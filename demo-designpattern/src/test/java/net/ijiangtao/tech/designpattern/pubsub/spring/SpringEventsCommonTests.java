package net.ijiangtao.tech.designpattern.pubsub.spring;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.designpattern.pubsub.spring.common.RegisterEventPublisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
public class SpringEventsCommonTests {

    @Autowired
    private RegisterEventPublisher registerEventPublisher;


    @Test
    public void test1(){

        registerEventPublisher.publish(" Danny is here.");

        try {
            Thread.sleep(10 * 1000);
        } catch (Exception e) {
            log.error("{}", e);
        }
    }

}
