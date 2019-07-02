package net.ijiangtao.tech.demo.http.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * redirect
 *
 * @author ijiangtao
 * @create 2019-06-21 16:32
 **/
@RestController
public class RedirectController {

    @GetMapping("/foo1")
    public void foo1(HttpServletResponse response) throws Exception {
        Thread.sleep(2000);
        response.sendRedirect("http://localhost:8080/bar1");
    }

    @GetMapping("/bar1")
    public String bar1() throws Exception {
        Thread.sleep(2000);
        return "bar1";
    }

    //------------------------------

    @GetMapping("/foo301")
    public void foo301(HttpServletResponse response) throws Exception {
        Thread.sleep(2000);
        response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        response.setHeader("Location", "http://localhost:8080/bar301");
    }

    @GetMapping("/bar301")
    public String bar301() throws Exception {
        Thread.sleep(2000);
        return "bar301";
    }

    @GetMapping("/foo302")
    public void foo302(HttpServletResponse response) throws Exception {
        Thread.sleep(2000);
        response.setStatus(HttpStatus.FOUND.value());
        response.setHeader("Location", "http://localhost:8080/bar302");
    }

    @GetMapping("/bar302")
    public String bar302() throws Exception {
        Thread.sleep(2000);
        return "bar302";
    }

    @GetMapping("/foo307")
    public void foo307(HttpServletResponse response) throws Exception {
        Thread.sleep(2000);
        response.setStatus(HttpStatus.MOVED_PERMANENTLY.value());
        response.setHeader("Location", "http://localhost:8080/bar307");
    }

    @GetMapping("/bar307")
    public String bar307() throws Exception {
        Thread.sleep(2000);
        return "bar307";
    }

    @GetMapping("/foo308")
    public void foo308(HttpServletResponse response) throws Exception {
        Thread.sleep(2000);
        response.setStatus(HttpStatus.FOUND.value());
        response.setHeader("Location", "http://localhost:8080/bar308");
    }

    @GetMapping("/bar308")
    public String bar308() throws Exception {
        Thread.sleep(2000);
        return "bar308";
    }

}
