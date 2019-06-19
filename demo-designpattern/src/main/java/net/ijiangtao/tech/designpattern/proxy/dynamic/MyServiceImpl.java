package net.ijiangtao.tech.designpattern.proxy.dynamic;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Service实现
 *
 * @author ijiangtao
 * @create 2019-06-14 15:10
 **/
public class MyServiceImpl implements MyService {

    @Override
    public String call(Date date) {
        return new SimpleDateFormat().format(date);
    }

}
