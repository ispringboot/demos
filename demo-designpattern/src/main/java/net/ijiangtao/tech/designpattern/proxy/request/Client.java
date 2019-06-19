package net.ijiangtao.tech.designpattern.proxy.request;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * client
 *
 * @author ijiangtao
 * @create 2019-06-07 16:14
 **/
@Builder
@Slf4j
public class Client {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(RealSubject.builder().build());
        Result result = proxy.request(Parameter.builder().id(1024L).name("proxy").build());
        log.info("client get result : " + JSON.toJSONString(result));
    }

}
