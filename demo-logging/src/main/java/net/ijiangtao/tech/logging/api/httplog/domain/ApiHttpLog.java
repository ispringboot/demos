package net.ijiangtao.tech.logging.api.httplog.domain;

import lombok.Data;

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

    // identify information------------------------------------------
    private Long id;

    private String traceId;

    // rquest information------------------------------------------
    private String clientFingerprint;
    private String requestHeaders;
    private String httpMethod;
    private String requestParameters;
    private String requestBody;
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
