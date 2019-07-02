package net.ijiangtao.tech.demo.httplog.v1.httplog.persistence;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.httplog.v1.httplog.model.ApiHttpLog;
import org.springframework.stereotype.Component;

/**
 * HttpLogPersistence Log File
 *
 * @author ijiangtao
 * @create 2019-06-25 19:06
 **/
@Component("httpLogPersistenceLogFile")
@Slf4j
public class HttpLogPersistenceLogFile implements HttpLogPersistence {

    @Override
    public void save(ApiHttpLog apiHttpLog) {
        log.info("[{}]", JSON.toJSONString(apiHttpLog, true));
    }
}
