package net.ijiangtao.tech.demo.http.web.handler;

import com.alibaba.fastjson.JSON;
import net.ijiangtao.tech.demo.http.constant.APIConstant;
import net.ijiangtao.tech.demo.http.web.annotation.API;
import net.ijiangtao.tech.demo.http.web.response.APIResponse;
import net.ijiangtao.tech.demo.http.web.util.ResponseJsonWriter;
import org.springframework.core.MethodParameter;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;

/**
 * response handler
 * <p>
 * controller返回值处理器
 *
 * @author ijiangtao
 * @create 2019-06-05 11:36
 **/
public class ResponseHandler implements HandlerMethodReturnValueHandler {

    /**
     * 是否支持给定的方法返回类型:
     * <p>
     * 只有返回true才回去调用handleReturnValue
     *
     * @param returnType
     * @return
     */
    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return (returnType.getMethodAnnotation(API.class) != null) && (returnType.getParameterType() == APIResponse.class);
    }

    /**
     * @param returnValue
     * @param returnType
     * @param mavContainer
     * @param webRequest
     * @throws Exception
     */
    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {

        //标识请求是否已经在该方法内完成处理
        mavContainer.setRequestHandled(true);

        //修改response的http status code
        changeHttpCode(returnValue, returnType, webRequest);

        //将返回json写入response
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        ResponseJsonWriter.write(response, JSON.toJSON((APIResponse) returnValue));
    }

    /**
     * @param returnValue
     * @param returnType
     * @param webRequest
     */
    private void changeHttpCode(Object returnValue, MethodParameter returnType, NativeWebRequest webRequest) {
        API apiAnnotation = returnType.getMethodAnnotation(API.class);
        if (!apiAnnotation.adaptHttpStatusCode()) {
            return;
        }
        APIResponse apiResponse = (APIResponse) returnValue;
        if (apiResponse.getHttpStatusCode() == APIConstant.HTTPSTATUSCODE_NOT_AVAILABLE) {
            return;
        }

        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        if (null == response) {
            return;
        }
        response.setStatus(apiResponse.getHttpStatusCode());
    }

}
