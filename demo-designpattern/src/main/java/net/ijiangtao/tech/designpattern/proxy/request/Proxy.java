package net.ijiangtao.tech.designpattern.proxy.request;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 代理（Proxy）类：
 * 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 *
 * @author ijiangtao
 * @create 2019-06-07 16:11
 **/
@AllArgsConstructor
@Slf4j
public class Proxy implements Subject<Result, Parameter> {

    private Subject<Result, Parameter> subject;


    public Result request(Parameter p) {

        log.info("handle preRequest");
        preRequest(p);

        log.info("handle request");
        Result r = subject.request(p);

        log.info("handle postRequest");
        postRequest(r);

        return r;
    }

    public Parameter preRequest(Parameter p) {
        p.setName(p.getName()+"[preRequest]");
        return p;
    }

    public Result postRequest(Result r) {
        r.setMessage(r.getMessage()+"[postRequest]");
        return r;
    }

}
