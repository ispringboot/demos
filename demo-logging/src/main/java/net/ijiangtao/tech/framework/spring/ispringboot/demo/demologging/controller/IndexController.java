package net.ijiangtao.tech.demo.demologging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    private static final Logger LOGGER= LoggerFactory.getLogger(IndexController.class);

    @Value("${demo.log.db.username}")
    private String dbUserName;

//    @Value("${info.author.name}")
//    private String authorName;

    @GetMapping("/log")
    public String log(){
        String preFix="********************";

        LOGGER.debug("{} debug",preFix);
        LOGGER.info("{} info",preFix);
        LOGGER.warn("{} warn",preFix);
        LOGGER.error("{} error",preFix);

        LOGGER.info("******************************** dbUserName={}",dbUserName);
       // LOGGER.info("******************************** authorName={}",authorName);

        return "log has been writtten";
    }
}
