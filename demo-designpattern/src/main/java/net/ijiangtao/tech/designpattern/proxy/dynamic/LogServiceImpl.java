package net.ijiangtao.tech.designpattern.proxy.dynamic;

/**
 * LogService
 *
 * @author ijiangtao
 * @create 2019-06-14 15:11
 **/
public class LogServiceImpl implements LogService {

    @Override
    public void print(String log) {
        System.out.println(log);
    }

}

