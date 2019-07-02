package net.ijiangtao.tech.demo.httplog.v1.httplog.event;

import lombok.Getter;
import net.ijiangtao.tech.demo.httplog.v1.httplog.model.ApiHttpLog;
import org.springframework.context.ApplicationEvent;

/**
 * 注册事件
 *
 * @author ijiangtao
 * @create 2019-05-02 12:59
 **/
@Getter
public class ApiHttpLogEvent extends ApplicationEvent {

    private ApiHttpLog apiHttpLog;

    public ApiHttpLogEvent(Object source, ApiHttpLog apiHttpLog) {
        super(source);
        this.apiHttpLog = apiHttpLog;
    }

}
