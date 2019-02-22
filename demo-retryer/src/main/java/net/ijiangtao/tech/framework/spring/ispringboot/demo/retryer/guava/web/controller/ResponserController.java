package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer.guava.web.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResponserController {

    @PostMapping("/response/hello")
    public String hello() throws Exception{

        Thread.sleep(20*1000L);

        return "Hello,Retryer!";
    }

}
