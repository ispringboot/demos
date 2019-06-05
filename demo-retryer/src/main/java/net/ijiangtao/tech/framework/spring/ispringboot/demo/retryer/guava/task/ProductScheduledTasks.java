package net.ijiangtao.tech.demo.retryer.guava.task;

import com.github.rholder.retry.Retryer;
import net.ijiangtao.tech.demo.retryer.guava.service.ProductInformationHander;
import net.ijiangtao.tech.demo.retryer.guava.service.ProductRetryerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductScheduledTasks {

    @Autowired
    private ProductRetryerBuilder builder;

    @Autowired
    private ProductInformationHander hander;

    /**
     * 同步商品价格定时任务
     * @Scheduled(fixedDelay = 30000) ：上一次执行完毕时间点之后30秒再执行
     */
    //@Scheduled(fixedDelay = 30*1000)
    public void syncPrice() throws Exception{
        Retryer retryer=builder.build();
        retryer.call(hander);
    }

}
