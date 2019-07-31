package net.ijiangtao.tech.demo.webasynctask.controller;

import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * WebAsyncTask
 *
 * @author ijiangtao
 * @create 2019-07-03 11:31
 **/
@RestController
public class WebAsyncTask2Controller {

    @GetMapping("/r3")
    public WebAsyncTask<Map<String, String>> r2() {

        Instant now = Instant.now();

        Callable<Map<String, String>> callable = new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                return buildResult();
            }
        };

        SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        executor.setThreadNamePrefix("WebAsyncTask2-");

        WebAsyncTask<Map<String, String>> webAsyncTask = new WebAsyncTask<>(2 * 1000L, executor, callable);

        webAsyncTask.onCompletion(new Runnable() {
            @Override
            public void run() {
                System.out.println("Completion");
            }
        });

        webAsyncTask.onError(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                System.out.println("Error");
                return new HashMap<>();
            }
        });

        webAsyncTask.onTimeout(new Callable<Map<String, String>>() {
            @Override
            public Map<String, String> call() throws Exception {
                System.out.println("Timeout");
                Map<String, String> timeOutResutl = new HashMap<>();
                timeOutResutl.put("timeout", "result");
                return timeOutResutl;
            }
        });

        doTask();

        System.out.println("r2 time consumption: " + ChronoUnit.SECONDS.between(now, Instant.now()) + " seconds");

        return webAsyncTask;
    }


    private Map<String, String> buildResult() {
        System.out.println("building result");
        Map<String, String> result = new HashMap<>();
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 1 * 1000; i++) {
            result.put(i + "-key", i + "value");
        }
        return result;
    }

    private void doTask() {
        System.out.println("do some tasks");
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
