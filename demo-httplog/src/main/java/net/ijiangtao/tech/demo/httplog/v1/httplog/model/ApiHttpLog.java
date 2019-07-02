package net.ijiangtao.tech.demo.httplog.v1.httplog.model;

import lombok.Data;
import net.ijiangtao.tech.demo.httplog.v1.httplog.bind.ApiResponse;

import java.io.Serializable;
import java.util.Date;

/**
 * api http log
 *
 * @author ijiangtao
 * @create 2019-06-18 14:46
 **/
@Data
public class ApiHttpLog implements Serializable {

    private ApiResponse apiResponse;

    // identify information------------------------------------------
    private Long id;

    private String traceId;

    // rquest information------------------------------------------
    private String clientFingerprint;
    private String requestHeaders;
    private String httpMethod;
    private String requestParameters;
    /**
     * 请求方法的参数
     */
    private String methodParameters;
    private String requestPath;

    // response information------------------------------------------
    private String responseBody;
    private String responseHeaders;
    private Integer httpCode;
    private String exceptionMsg;

    // time information------------------------------------------
    private Long startTime;
    private Long completionTime;
    private Long costTime;

    private Date createdTime;


}
