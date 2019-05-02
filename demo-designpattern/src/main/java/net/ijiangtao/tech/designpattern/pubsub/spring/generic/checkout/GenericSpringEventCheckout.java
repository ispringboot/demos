package net.ijiangtao.tech.designpattern.pubsub.spring.generic.checkout;

import lombok.Getter;
import net.ijiangtao.tech.designpattern.pubsub.spring.generic.GenericSpringEvent;

/**
 * GenericSpringEventCheckout
 *
 * @author ijiangtao
 * @create 2019-05-02 13:58
 **/
@Getter
public class GenericSpringEventCheckout extends GenericSpringEvent<Long> {

    private Long userId;

    public GenericSpringEventCheckout(Long userId, boolean success) {
        super(userId, success);
    }

}
