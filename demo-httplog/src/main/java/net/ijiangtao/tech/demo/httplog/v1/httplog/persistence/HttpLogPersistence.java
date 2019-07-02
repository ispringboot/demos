package net.ijiangtao.tech.demo.httplog.v1.httplog.persistence;

import net.ijiangtao.tech.demo.httplog.v1.httplog.model.ApiHttpLog;

/**
 * HttpLogPersistence
 *
 * @author ijiangtao
 * @create 2019-06-25 19:06
 **/
public interface HttpLogPersistence {

    void save(ApiHttpLog apiHttpLog);

}
