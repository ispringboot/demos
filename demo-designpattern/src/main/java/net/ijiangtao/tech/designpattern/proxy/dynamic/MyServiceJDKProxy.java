package net.ijiangtao.tech.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * JDKProxy
 *
 * @author ijiangtao
 * @create 2019-06-14 15:13
 **/
public class MyServiceJDKProxy implements InvocationHandler {

    private Object target;

    public MyServiceJDKProxy(Object target) {
        this.target = target;
    }

    private LogService logService = new LogServiceImpl();

    /**
     * @param proxy  将来所产生的代理对象 Proxy
     * @param method 将来需要调用到的目标对象里面真正的那个方法的镜像
     * @param args   将来调用方法的时候所传的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获得将来所调用方法的名字
        String methodName = method.getName();
        logService.print("MyServiceJDKProxy invoke methodName : " + methodName);

        // 用反射的方式去调用将来需要真正调用的方法.
        Object o = method.invoke(target, args);
        logService.print("MyServiceJDKProxy invoke return : " + o);

        return o;
    }

}
