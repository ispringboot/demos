package net.ijiangtao.tech.designpattern.pubsub.guava;

import com.google.common.eventbus.Subscribe;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * EventListener
 *
 * @author ijiangtao
 * @create 2019-05-02 18:15
 **/
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CustomEventListener {

    private List<String> listenedMessageList;

    @Subscribe
    public void onEvent(CustomEvent event) {
        log.info("Guava EventListener listened one message : {}", event.getMessage());
        listenedMessageList.add(event.getMessage());
    }

}
