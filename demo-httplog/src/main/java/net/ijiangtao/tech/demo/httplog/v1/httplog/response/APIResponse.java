package net.ijiangtao.tech.demo.httplog.v1.httplog.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class APIResponse<T> implements Serializable {

	private static final long serialVersionUID = -77490973397753430L;

	/** response status code */
	private String statusCode;

	/** response error message */
	private String message;

	/** response data */
	private T data;

	/**
	 * build a ApiResponse.
	 * 
	 * @param responseStatus
	 * @param data
	 * @return
	 */
	public static <T> APIResponse<T> build(@NonNull ResponseStatus responseStatus, T data) {
		return new APIResponse<T>(responseStatus.getCode(), responseStatus.getMessage(), data);
	}

	/**
	 * build a ApiResponse.
	 *
	 * @param responseStatus
	 * @return
	 */
	public static <T> APIResponse<T> build(@NonNull ResponseStatus responseStatus) {
		return new APIResponse<T>(responseStatus.getCode(), responseStatus.getMessage(), null);
	}
}
