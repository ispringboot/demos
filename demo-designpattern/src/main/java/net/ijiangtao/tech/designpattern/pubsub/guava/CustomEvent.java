package net.ijiangtao.tech.designpattern.pubsub.guava;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * CustomEvent
 *
 * @author ijiangtao
 * @create 2019-05-02 18:21
 **/
@AllArgsConstructor
@Data
public class CustomEvent {
    private String message;
}
