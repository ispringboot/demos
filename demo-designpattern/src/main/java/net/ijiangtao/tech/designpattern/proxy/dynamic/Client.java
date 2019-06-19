package net.ijiangtao.tech.designpattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Date;

/**
 * client
 *
 * @author ijiangtao
 * @create 2019-06-14 15:21
 **/
public class Client {

    public static void main(String[] args) {

        MyService myService = new MyServiceImpl();

        //获得目标对象的Class
        Class myServiceClass = myService.getClass();

        //获得目标对象的类加载器对象
        ClassLoader classLoader = myServiceClass.getClassLoader();

        //获得目标对象所实现的所有接口
        Class[] interfaces = myServiceClass.getInterfaces();

        //获得一个InvocationHandler接口的实现类对象,并把目标对象传进去
        InvocationHandler handler = new MyServiceJDKProxy(myService);

        //参数1 目标对象的类加载器对象
        //参数2 目标对象所实现的所有接口. Class类型数组
        //参数3 InvocationHandler接口的实现类对象
        MyService proxy = (MyService) Proxy.newProxyInstance(classLoader, interfaces, handler);

        //这里的proxy是一个实现了MyService接口动态生成的代理类的对象
        proxy.call(new Date());
    }


}
