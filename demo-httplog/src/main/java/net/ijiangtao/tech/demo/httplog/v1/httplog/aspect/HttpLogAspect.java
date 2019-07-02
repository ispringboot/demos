package net.ijiangtao.tech.demo.httplog.v1.httplog.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.httplog.v1.httplog.bind.ApiResponse;
import net.ijiangtao.tech.demo.httplog.v1.httplog.event.ApiHttpLogEventPublisher;
import net.ijiangtao.tech.demo.httplog.v1.httplog.model.ApiHttpLog;
import net.ijiangtao.tech.demo.httplog.v1.httplog.response.APIResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.*;

/**
 * HttpLog切面
 *
 * @author ijiangtao
 * @create 2019-06-18 12:58
 **/
@Aspect
@Slf4j
public class HttpLogAspect {

    @Autowired
    private ApiHttpLogEventPublisher publisher;

    private ThreadLocal<ApiHttpLog> apiHttpLogThreadLocal = new ThreadLocal<ApiHttpLog>() {
        @Override
        protected ApiHttpLog initialValue() {
            return new ApiHttpLog();
        }
    };

    /**
     * 注解切点
     */
    @Pointcut("@annotation(net.ijiangtao.tech.demo.httplog.v1.httplog.bind.ApiResponse)")
    public void logAnnotation() {
        log.debug("@ApiResponse annnotation ... ");
    }

    /**
     * 记录request日志
     *
     * @param joinPoint
     */
    @Before("logAnnotation()")
    public void requestLog(JoinPoint joinPoint) {
        ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
        try {
            //ApiResponse 注解
            ApiResponse apiResponse = getLogAnnotation(joinPoint);
            apiHttpLog.setApiResponse(apiResponse);

            //日志创建时间
            apiHttpLog.setCreatedTime(new Date());
            if (null == apiResponse || apiResponse.ignoreHttpLog() || apiResponse.ignoreRequestHttpLog()) {
                return;
            }

            HttpServletRequest request = getRequest();

            //日志追踪id: SessionId-UUID
            String traceId = request.getRequestedSessionId() + "-" + UUID.randomUUID().toString().replace("-", "");
            apiHttpLog.setTraceId(traceId);
            apiHttpLog.setStartTime(System.currentTimeMillis());

            // ApiHttpLog: HttpMethod
            apiHttpLog.setHttpMethod(request.getMethod());

            // ApiHttpLog: RequestPath
            apiHttpLog.setRequestPath(getRequestPath(request));

            // ApiHttpLog: requestParameters json
            List<String> excludes = Arrays.asList(apiResponse.excludeRequestParametersHttpLog());
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                Map<String, String[]> requestParameters = new HashMap<>();
                if (!excludes.contains(entry.getKey())) {
                    requestParameters.put(entry.getKey(), entry.getValue());
                }
                apiHttpLog.setRequestParameters(JSON.toJSONString(requestParameters));
            }

            // ApiHttpLog: requestHeaders json
            if (apiResponse.includeRequestHeadersHttpLog().length > 0) {
                Map<String, String> requestHeaders = new HashMap<>();
                for (String headerName : apiResponse.includeRequestHeadersHttpLog()) {
                    requestHeaders.put(headerName, request.getHeader(headerName));
                }
                apiHttpLog.setRequestHeaders(JSON.toJSONString(requestHeaders));
            }

            // ApiHttpLog: method parameter list json
            if (!apiResponse.ignoreAllMethdParameters()) {
                Map<String, Object> methodParameterMap = getMethodParameters(joinPoint, apiResponse);
                if (null != methodParameterMap) {
                    apiHttpLog.setMethodParameters(JSON.toJSONString(methodParameterMap));
                }
            }

        } catch (Exception exception) {
            log.error("save http log exception", exception);
        } finally {
            apiHttpLogThreadLocal.set(apiHttpLog);
        }
    }

    /**
     * 记录resposne日志: 返回值伟标准的 APIResponse 对象
     *
     * @param joinPoint
     * @param aPIResponse
     */
    @AfterReturning(returning = "aPIResponse", pointcut = "logAnnotation()")
    public void responseLog(JoinPoint joinPoint, APIResponse aPIResponse) {
        try {
            ApiResponse apiResponse = apiHttpLogThreadLocal.get().getApiResponse();
            if (null == apiResponse || apiResponse.ignoreHttpLog() || apiResponse.ignoreResponseHttpLog()) {
                return;
            }

            ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
            apiHttpLog.setResponseBody(JSON.toJSONString(aPIResponse));

        } catch (Exception exception) {
            log.error("save http log exception", exception);
        } finally {
            finallyBlock();
        }
    }

    /**
     * 异步保存日志
     */
    private void finallyBlock() {

        ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
        apiHttpLog.setCompletionTime(System.currentTimeMillis());
        apiHttpLog.setCostTime(costTime());

        publisher.publish(apiHttpLog);

        apiHttpLogThreadLocal.remove();
    }

    /**
     * 记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(throwing = "e", pointcut = "logAnnotation()")
    public void throwing(JoinPoint joinPoint, Exception e) {
        try {
            ApiResponse apiResponse = apiHttpLogThreadLocal.get().getApiResponse();
            if (null == apiResponse || apiResponse.ignoreHttpLog() || apiResponse.ignoreExceptionHttpLog()) {
                return;
            }

            ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
            if (null != apiHttpLog) {
                apiHttpLog.setResponseBody(JSON.toJSONString(e));
            }
        } catch (Exception exception) {
            log.error("save http log exception", exception);
        } finally {
            finallyBlock();
        }
    }

    private ApiResponse getLogAnnotation(JoinPoint joinPoint) {
        if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                if (method.isAnnotationPresent(ApiResponse.class)) {
                    return method.getAnnotation(ApiResponse.class);
                }
            }
        }
        return null;
    }

    /**
     * 获取
     *
     * @param joinPoint
     * @return
     */
    private Map<String, Object> getMethodParameters(JoinPoint joinPoint, ApiResponse apiResponse) {

        if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {

                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();

                String[] parameterNames = methodSignature.getParameterNames();
                Class[] parameterTypes = method.getParameterTypes();
                Object[] args = joinPoint.getArgs();

                if (null == parameterTypes || null == args || args.length < 1 || parameterTypes.length != args.length) {
                    return null;
                }

                List<String> excludeMethdParameters = Arrays.asList(apiResponse.excludeMethdParameters());
                Map<String, Object> methodParameterMap = new HashMap<>();
                for (int i = 0; i < args.length; i++) {

                    //默认不需要记录的参数
                    Class parameterType = parameterTypes[i];
                    if ("javax.servlet.http.HttpServletResponse".equalsIgnoreCase(parameterType.getName())) {
                        continue;
                    }
                    if (HttpServletRequest.class == parameterType) {
                        continue;
                    }
                    if (Model.class == parameterType) {
                        continue;
                    }

                    //根据配置不需要记录的参数
                    if (excludeMethdParameters.contains(parameterNames[i])) {
                        continue;
                    }

                    methodParameterMap.put(parameterNames[i], args[i]);
                }

                return methodParameterMap;

            }
        }
        return null;
    }

    /**
     * 获取 HttpServletRequest
     *
     * @return
     */
    private HttpServletRequest getRequest() {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        return sra.getRequest();
    }

    /**
     * @param request
     * @return
     */
    private String getRequestPath(HttpServletRequest request) {
        return (null != request.getServletPath() && request.getServletPath().length() > 0)
                ? request.getServletPath() : request.getPathInfo();
    }

    /**
     * 距离当前时间耗时（毫秒数）
     *
     * @return
     */
    private long costTime() {
        return System.currentTimeMillis() - apiHttpLogThreadLocal.get().getStartTime();
    }

}
