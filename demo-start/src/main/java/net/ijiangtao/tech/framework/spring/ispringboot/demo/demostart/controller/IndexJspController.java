package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexJspController {

    @GetMapping("/jsp")
    public String hello() {

        return "index";

    }

}
