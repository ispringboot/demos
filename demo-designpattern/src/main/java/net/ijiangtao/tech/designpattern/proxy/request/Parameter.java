package net.ijiangtao.tech.designpattern.proxy.request;

import lombok.Builder;
import lombok.Data;

/**
 * 参数
 *
 * @author ijiangtao
 * @create 2019-06-07 16:08
 **/
@Data
@Builder
public class Parameter {

    private Long id;

    private String name;

}
