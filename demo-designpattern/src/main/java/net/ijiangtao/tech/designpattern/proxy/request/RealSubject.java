package net.ijiangtao.tech.designpattern.proxy.request;

import lombok.Builder;

import java.util.Date;

/**
 * 真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 *
 * @author ijiangtao
 * @create 2019-06-07 16:09
 **/
@Builder
public class RealSubject implements Subject<Result, Parameter> {

    @Override
    public Result request(Parameter parameter) {

        return Result.builder()
                .id(parameter.getId())
                .message("Received Message From: " + parameter.getName())
                .time(new Date())
                .build();

    }
}
