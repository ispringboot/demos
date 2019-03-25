package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer.guava.listener;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.RetryListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RetryLogListener implements RetryListener {

    @Override
    public <V> void onRetry(Attempt<V> attempt) {

        // 第几次重试,(注意:第一次重试其实是第一次调用)
        log.info("retry time : [{}]", attempt.getAttemptNumber());

        // 距离第一次重试的延迟
        log.info("距离第一次重试的延迟 retry delay : [{}]", attempt.getDelaySinceFirstAttempt());

        // 重试结果: 是异常终止, 还是正常返回
        log.info("hasException={}", attempt.hasException());
        log.info("hasResult={}", attempt.hasResult());

        // 是什么原因导致异常
        if (attempt.hasException()) {
            log.info("causeBy={}" , attempt.getExceptionCause().toString());
            //TODO sms
        } else {
            // 正常返回时的结果
            log.info("result={}" , attempt.getResult());
        }


        log.info("log listen over.");

    }
}
