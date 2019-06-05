package net.ijiangtao.tech.demo.popup.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ijiangtao.net
 */
@Controller
public class HelloController {

    @RequestMapping("/")
    public String testThymeleaf(ModelMap modelMap){
        modelMap.addAttribute("msg", "Hello!");
        return "hello";
    }
}
