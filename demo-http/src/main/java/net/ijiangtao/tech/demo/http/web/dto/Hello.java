package net.ijiangtao.tech.demo.http.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * hello
 *
 * @author ijiangtao
 * @create 2019-06-05 10:53
 **/
@Data
@AllArgsConstructor
public class Hello extends BaseResponse {
    private String msg;
}
