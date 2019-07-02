package net.ijiangtao.tech.demo.httplog.v1.httplog.config;

import net.ijiangtao.tech.demo.httplog.v1.httplog.aspect.HttpLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * config
 *
 * @author ijiangtao
 * @create 2019-07-02 14:53
 **/
@Configuration
public class HttpLogConfig {

    @Bean
    public HttpLogAspect httpLogAspect() {
        return new HttpLogAspect();
    }

}
