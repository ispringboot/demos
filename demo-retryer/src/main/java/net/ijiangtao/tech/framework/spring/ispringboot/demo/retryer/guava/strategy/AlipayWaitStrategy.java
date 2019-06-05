package net.ijiangtao.tech.demo.retryer.guava.strategy;

import com.github.rholder.retry.Attempt;
import com.github.rholder.retry.WaitStrategy;

/**
 * 自定义等待策略：根据重试次数动态调节等待时间，第一次请求间隔1s，第二次间隔10s，第三次及以后都是20s。
 *
 *
 * 在创建Retryer的时候通过withWaitStrategy将该等待策略生效即可。
 *
 *  RetryerBuilder.<Boolean>newBuilder()
 *                .withWaitStrategy(new AlipayWaitStrategy())
 *
 *  类似的效果也可以通过自定义 BlockStrategy 来实现，你可以写一下试试。
 *
 */
public class AlipayWaitStrategy implements WaitStrategy {

    @Override
    public long computeSleepTime(Attempt failedAttempt) {
        long number = failedAttempt.getAttemptNumber();
        if (number==1){
            return 1*1000;
        }
        if (number==2){
            return 10*1000;
        }
        return 20*1000;
    }

}
