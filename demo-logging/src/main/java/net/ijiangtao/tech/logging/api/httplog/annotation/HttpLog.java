package net.ijiangtao.tech.logging.api.httplog.annotation;

import java.lang.annotation.*;

/**
 * HttpLog注解
 *
 * @author ijiangtao
 * @create 2019-06-18 12:56
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface HttpLog {

    /**
     * 忽略参数，避免文件or无意义参数打印
     *
     * @return 忽略参数数组
     */
    String[] exclude() default {};

    /**
     * 需要打印的header参数
     *
     * @return header参数名数组
     */
    String[] headerParams() default {};

    boolean ignoreResponse() default false;

    boolean ignoreRequest() default false;

    boolean ignoreException() default false;

}
