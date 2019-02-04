package net.ijiangtao.tech.framework.spring.ispringboot.demo.demologging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger LOGGER= LoggerFactory.getLogger(IndexController.class);

    @GetMapping("/log")
    public String log(){
        String preFix="********************";

        LOGGER.debug("{} debug",preFix);
        LOGGER.info("{} info",preFix);
        LOGGER.warn("{} warn",preFix);
        LOGGER.error("{} error",preFix);

        return "log has been writtten";
    }
}
