package net.ijiangtao.tech.demo.http.web.annotation;

import java.lang.annotation.*;

/**
 * http
 *
 * @author ijiangtao
 * @create 2019-06-05 14:11
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AuthorizedUser {

}
