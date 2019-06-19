package net.ijiangtao.tech.designpattern.proxy.request;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 返回
 *
 * @author ijiangtao
 * @create 2019-06-07 16:07
 **/
@Data
@Builder
public class Result {

    private Long id;

    private String message;

    private Date time;

}

