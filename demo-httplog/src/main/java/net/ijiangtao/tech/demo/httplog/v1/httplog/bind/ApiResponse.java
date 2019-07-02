package net.ijiangtao.tech.demo.httplog.v1.httplog.bind;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApiResponse {

    /*** http log begin ***/

    /**
     * 哪些请求参数不需要记录log
     *
     * @return
     */
    String[] excludeRequestParametersHttpLog() default {};

    /**
     * 哪些请求头需要记录log
     *
     * @return
     */
    String[] includeRequestHeadersHttpLog() default {"Accept-Language", "memberToken"};

    /**
     * 哪些方法的参数不需要记录log: 参数的变量名
     *
     * @return
     */
    String[] excludeMethdParameters() default {};

    /**
     * 是否所有的方法参数都不需要记录log，默认false
     *
     * @return
     */
    boolean ignoreAllMethdParameters() default false;

    /**
     * 是否不需要记录log，默认false
     *
     * @return
     */
    boolean ignoreHttpLog() default false;

    /**
     * 是否所有的方法参数都不需要记录参数，默认false
     *
     * @return
     */
    boolean ignoreResponseHttpLog() default false;

    /**
     * 是否所有的请求都不需要记录log，默认false
     *
     * @return
     */
    boolean ignoreRequestHttpLog() default false;

    /**
     * 是否所有的异常返回都不需要记录log，默认false
     *
     * @return
     */
    boolean ignoreExceptionHttpLog() default false;

    /*** http log end ***/

    /**
     * 是否需要根据 APIResponse.statusCode 修改 Response 的 http code
     *
     * @return
     */
    boolean httpCode() default false;

}
