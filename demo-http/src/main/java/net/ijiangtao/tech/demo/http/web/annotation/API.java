package net.ijiangtao.tech.demo.http.web.annotation;

import java.lang.annotation.*;

/**
 *
 * API注解
 *
 * @author ijiangtao
 * @create 2019-06-05 14:11
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface API {

    /**
     * 是否需要适配http  status code
     * <p>
     * https://www.iana.org/assignments/http-status-codes/http-status-codes.xhtml
     *
     * @return
     */
    boolean adaptHttpStatusCode() default false;

    /**
     * 是否需要权限校验
     *
     * @return
     */
    boolean authorizedRequired() default false;
}
