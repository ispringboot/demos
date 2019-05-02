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

        // 构造一个事件总线
        EventBus eventBus = new EventBus();

        List<String> listenedMessageList = new ArrayList<>();
        // 构造一个事件监听器
        CustomEventListener customEventListener = new CustomEventListener(listenedMessageList);

        // 把事件监听器注册到事件总线上
        eventBus.register(customEventListener);

        // 事件总线发布事件，触发监听器方法
        eventBus.post(new CustomEvent("post a custom event ---- 1"));
        eventBus.post(new CustomEvent("post a custom event ---- 2"));

        // 把事件监听器从事件总线上移除
        eventBus.unregister(customEventListener);

        eventBus.post(new CustomEvent("post a custom event ---- 3"));

    }

}
