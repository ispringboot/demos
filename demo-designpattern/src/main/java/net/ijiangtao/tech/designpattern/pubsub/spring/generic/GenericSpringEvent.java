package net.ijiangtao.tech.designpattern.pubsub.spring.generic;

import lombok.Getter;

/**
 * GenericSpringEvent
 *
 * @author ijiangtao
 * @create 2019-05-02 13:47
 **/
@Getter
public class GenericSpringEvent<T> {

    private T what;

    protected boolean success;

    public GenericSpringEvent(T what, boolean success) {
        this.what = what;
        this.success = success;
    }

}
