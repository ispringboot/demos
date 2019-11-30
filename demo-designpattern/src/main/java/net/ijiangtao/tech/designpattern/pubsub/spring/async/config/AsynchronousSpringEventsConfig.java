package net.ijiangtao.tech.designpattern.pubsub.spring.async.config;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步事件监听配置
 *
 * @author ijiangtao
 * @create 2019-05-02 13:23
 **/
@Configuration
@Slf4j
public class AsynchronousSpringEventsConfig {

    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();

        //使用默认的异步事件处理器
        //eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());

        //自定义事件处理器
        eventMulticaster.setTaskExecutor(taskExecutor());

        return eventMulticaster;
    }

    private AsyncTaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //线程池相关配置
        executor.setMaxPoolSize(10);
        executor.setCorePoolSize(5);
        executor.setKeepAliveSeconds(60);
        executor.setQueueCapacity(2048);
        //using guava ThreadFactory
        executor.setThreadFactory(new ThreadFactoryBuilder().build());

        executor.setThreadNamePrefix("my-test-taskExecutor-thread");

        // 设置拒绝策略
        executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                new Exception("rejected by taskExecutor");
            }
        });
        // 使用预定义的异常处理类
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        return executor;
    }


}