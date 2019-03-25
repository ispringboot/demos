package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer.guava.strategy;

import com.github.rholder.retry.BlockStrategy;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * 自旋锁的实现, 不响应线程中断
 */
@Slf4j
@NoArgsConstructor
public class SpinBlockStrategy implements BlockStrategy {

    @Override
    public void block(long sleepTime) throws InterruptedException {

        LocalDateTime startTime = LocalDateTime.now();

        long start = System.currentTimeMillis();
        long end = start;
        log.info("[SpinBlockStrategy]...begin wait.");

        while (end - start <= sleepTime) {
            end = System.currentTimeMillis();
        }

        //使用Java8新增的Duration计算时间间隔
        Duration duration = Duration.between(startTime, LocalDateTime.now());

        log.info("[SpinBlockStrategy]...end wait.duration={}", duration.toMillis());

    }
}