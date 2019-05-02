package net.ijiangtao.tech.designpattern.pubsub.guava;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * guava event bus tests
 *
 * @author ijiangtao
 * @create 2019-05-02 18:24
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class GuavaEventBusTests {

    @Test
    public void test() {

        EventBus eventBus = new EventBus();

        List<String> listenedMessageList = new ArrayList<>();
        CustomEventListener customEventListener = new CustomEventListener(listenedMessageList);

        eventBus.register(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 1"));

        eventBus.unregister(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 2"));

    }

}
