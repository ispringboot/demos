package net.ijiangtao.tech.framework.spring.ispringboot.redis.rest;

import net.ijiangtao.tech.framework.spring.ispringboot.redis.BaseTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Rest tests
 *
 * @author ijiangtao
 * @create 2019-05-22 0:18
 **/
public class Tests extends BaseTests {

    @Autowired
    private IRestTest restTest;

    @Test
    public void test(){
        restTest.test();
    }
}
