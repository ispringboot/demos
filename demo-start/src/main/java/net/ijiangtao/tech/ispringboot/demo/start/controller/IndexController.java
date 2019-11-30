package net.ijiangtao.tech.demo.start.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @Value("${current.environment}")
    private String currentEnvironment;

    @GetMapping("/hello")
    public String hello() {

        return "Hello, I Spring Boot, current environment is "+currentEnvironment;

    }

}
