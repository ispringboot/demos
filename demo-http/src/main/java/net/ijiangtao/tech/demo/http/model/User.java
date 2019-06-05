package net.ijiangtao.tech.demo.http.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * user
 *
 * @author ijiangtao
 * @create 2019-06-05 23:09
 **/
@Data
@AllArgsConstructor
@EqualsAndHashCode
public class User {
    private String key;
    private int auth;
}
