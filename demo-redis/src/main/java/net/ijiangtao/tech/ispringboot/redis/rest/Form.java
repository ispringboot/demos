package net.ijiangtao.tech.ispringboot.redis.rest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 *
 * @author ijiangtao
 * @create 2019-05-21 23:31
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Form {

    private String timestamp;

    private String sign;

    private List<String> articleCodes;

}
