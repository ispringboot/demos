package net.ijiangtao.tech.demo.http.web.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * <p>
 * Response cache to the method caller.
 * </p>
 *
 * @author jiangtao.li
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> {

    /**
     * response status code
     */
    private String statusCode;

    /**
     * response message
     */
    private String message;

    /**
     * http状态码
     */
    private int httpStatusCode;

    /**
     * response data
     */
    private T data;


    /**
     * build a APIResponse.
     *
     * @param responseStatus
     * @param data
     * @return
     */
    public static <T> APIResponse<T> build(@NonNull ResponseStatus responseStatus, T data) {
        return new APIResponse<T>(responseStatus.getCode(), responseStatus.getMessage(), responseStatus.getHttpStatusCode(), data);
    }

    /**
     * build a APIResponse.
     *
     * @param responseStatus
     * @return
     */
    public static <T> APIResponse<T> build(@NonNull ResponseStatus responseStatus) {
        return new APIResponse<T>(responseStatus.getCode(), responseStatus.getMessage(), responseStatus.getHttpStatusCode(), null);
    }
}
