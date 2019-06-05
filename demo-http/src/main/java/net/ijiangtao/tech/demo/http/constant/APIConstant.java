package net.ijiangtao.tech.demo.http.constant;

import java.nio.charset.StandardCharsets;

/**
 * constant
 *
 * @author ijiangtao
 * @create 2019-06-05 22:45
 **/
public class APIConstant {

    /**
     * 不需要设置http status code
     */
    public static final int HTTPSTATUSCODE_NOT_AVAILABLE = 0;

    /**
     * Standard Charset
     */
    public static final String UTF_8 = StandardCharsets.UTF_8.name();

    /**
     * 请求的授权token
     */
    public static String AUTHORITY_TOKEN = "api_authority_token";
}
