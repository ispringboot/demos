package net.ijiangtao.tech.designpattern.pubsub.spring;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.designpattern.pubsub.spring.generic.checkout.GenericSpringEventPublisherCheckout;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEvent;
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
public class SpringEventsGenericTests {

    @Autowired
    private GenericSpringEventPublisherCheckout checkoutPubliser;


    @Test
    public void test1() {

        ApplicationEvent applicationEvent;
        checkoutPubliser.publish(101L, true);

        checkoutPubliser.publish(202L, false);
    }

}
