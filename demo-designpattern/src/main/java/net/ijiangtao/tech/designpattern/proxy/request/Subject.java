package net.ijiangtao.tech.designpattern.proxy.request;

/**
 * 抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 *
 * @author ijiangtao
 * @create 2019-06-07 16:03
 **/
public interface Subject<R, P> {

    R request(P p);

}
