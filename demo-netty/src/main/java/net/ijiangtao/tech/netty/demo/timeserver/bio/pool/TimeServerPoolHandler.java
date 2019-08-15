package net.ijiangtao.tech.netty.demo.timeserver.bio.pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * TimeServer
 *
 * @author ijiangtao
 * @create 2019-07-31 14:05
 **/
@Slf4j
public class TimeServerPoolHandler {

    private ExecutorService pool;

    public TimeServerPoolHandler() {
        pool = Executors.newFixedThreadPool(20);
    }

    public void execute(Runnable r) {
        pool.execute(r);
    }

}
