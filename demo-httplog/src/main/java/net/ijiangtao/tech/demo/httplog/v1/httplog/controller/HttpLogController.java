package net.ijiangtao.tech.demo.httplog.v1.httplog.controller;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.httplog.v1.httplog.bind.ApiResponse;
import net.ijiangtao.tech.demo.httplog.v1.httplog.response.APIResponse;
import net.ijiangtao.tech.demo.httplog.v1.httplog.response.ResponseStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.Callable;

/**
 * http log
 *
 * @author ijiangtao
 * @create 2019-07-02 14:48
 **/
@Slf4j
@RestController
public class HttpLogController {

    @GetMapping("/v1/hi")
    @ApiResponse
    public APIResponse<String> hi() {
        return APIResponse.build(ResponseStatus.SUCCESS, "hi");
    }


    @GetMapping("/v1/e")
    @ApiResponse
    public APIResponse<String> e() {

        //There is a NPE
        String e = null;
        e.length();

        return APIResponse.build(ResponseStatus.SUCCESS, "hi");
    }

    @GetMapping("/v1/r1")
    @ApiResponse
    public APIResponse<String> r1(@RequestBody Object requestBody) {
        return APIResponse.build(ResponseStatus.SUCCESS, "r1");
    }


    @GetMapping("/v1/r2")
    @ApiResponse(excludeMethdParameters = {"hehe", "hh"})
    public APIResponse<String> r2(//
                                  String haha,
                                  String hehe,
                                  @RequestBody Object requestBody,
                                  Model model,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  String huhu) {
        return APIResponse.build(ResponseStatus.SUCCESS, "r2");
    }

    @GetMapping("/v1/r3/{id}")
    @ApiResponse
    public APIResponse<String> r3(//
                                  @PathVariable String id) {
        return APIResponse.build(ResponseStatus.SUCCESS, id);
    }

    @GetMapping("/v1/r4")
    @ApiResponse
    public APIResponse<String> r4(//
                                  @RequestParam(name = "aaa") String a
    ) {
        return APIResponse.build(ResponseStatus.SUCCESS, a);
    }

    @GetMapping("/v1/r5")
    @ApiResponse
    public APIResponse<String> r5(//
                                  @RequestParam(name = "aaa") String a,
                                  @RequestParam(name = "bbb") String bb) {
        return APIResponse.build(ResponseStatus.SUCCESS, a);
    }

    @GetMapping("/v1/r6")
    @ApiResponse
    public WebAsyncTask<APIResponse<String>> r6(//
                                                @RequestParam(name = "zzz") String z,
                                                @RequestParam(name = "xxx") String x) {

        Callable<APIResponse<String>> callable = new Callable<APIResponse<String>>() {
            @Override
            public APIResponse<String> call() throws Exception {
                Thread.sleep(3 * 1000);
                return APIResponse.build(ResponseStatus.SUCCESS, z);
            }
        };

        WebAsyncTask<APIResponse<String>> webAsyncTask = new WebAsyncTask<APIResponse<String>>(5 * 1000, callable);

        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                log.info("webAsyncTask.onCompletion");
            }
        });

        log.info("do some thing else");

        return webAsyncTask;

    }

}
