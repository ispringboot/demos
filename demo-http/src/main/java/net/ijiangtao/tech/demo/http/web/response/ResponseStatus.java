package net.ijiangtao.tech.demo.http.web.response;

import lombok.Getter;
import net.ijiangtao.tech.demo.http.constant.APIConstant;
import org.springframework.http.HttpStatus;

/**
 * 1xx: Informational - Request received, continuing process
 * 2xx: Success - The action was successfully received, understood, and accepted
 * 3xx: Redirection - Further action must be taken in order to complete the request
 * 4xx: Client Error - The request contains bad syntax or cannot be fulfilled
 * 5xx: Server Error - The server failed to fulfill an apparently valid request
 * <p>
 * Response Business Status Code.
 */
@Getter
public enum ResponseStatus {

    /**
     * *************** 100开头:全局系统 ****************
     */

    SUCCESS("100200001", "success", HttpStatus.OK.value()),

    CLIENT_ERROR_BAD_REQUEST("100400001", "Client Error:Bad Request", 400),
    CLIENT_ERROR_BAD_REQUEST_EMPTY_PARAM("100400002", "Client Error:Bad Request", 400),

    CLIENT_ERROR_UNAUTHORIZED("100401001", "Client Error:Unauthorized", HttpStatus.UNAUTHORIZED.value()),
    CLIENT_ERROR_PAYMENT_REQUIRED("1000402001", "Client Error:Payment Required", 402),
    CLIENT_ERROR_FORBIDDEN("100403001", "Client Error:Forbidden", 403),
    CLIENT_ERROR_NOT_FOUND("100404001", "Client Error:Not found", 404),
    CLIENT_ERROR_REQUEST_TIMEOUT("100408001", "Client Error:Request Timeout", 408),
    CLIENT_ERROR_CONFLICT("100409001", "Client Error:Conflict", 409),
    CLIENT_ERROR_UNSUPPORTED_MEDIA_TYPE("100415001", "Client Error:Unsupported Media Type", 415),


    SERVER_ERROR("100500001", "Server Error:Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR.value()),

    SERVER_ERROR_NOT_IMPLEMENTED("100501001", "Server Error:Not Implemented", 501),

    SERVER_ERROR_SERVICE_UNAVAILABLE_ALL("100503001", "Server Error:Service Unavailable", 503),
    SERVER_ERROR_SERVICE_UNAVAILABLE_PART("100503002", "Server Error:Service Unavailable", 503),

    SERVER_ERROR_SERVICE_UNAVAILABLE_PART_2("100503002", "Server Error:Service Unavailable");

    ResponseStatus(String code, String message) {
        this.code = code;
        this.message = message;
    }

    ResponseStatus(String code, String message, int httpStatusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

    /**
     * response code
     * 9位:
     * xxx 所属模块
     * xxx HTTP状态码
     * xxx 具体业务
     */
    private String code;

    /**
     * response message
     */
    private String message;

    /**
     * 自定义http状态码
     */
    private int httpStatusCode = APIConstant.HTTPSTATUSCODE_NOT_AVAILABLE;

}
