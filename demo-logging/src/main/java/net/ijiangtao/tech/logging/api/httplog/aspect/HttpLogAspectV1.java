package net.ijiangtao.tech.logging.api.httplog.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.logging.api.httplog.annotation.HttpLog;
import net.ijiangtao.tech.logging.api.httplog.domain.ApiHttpLog;
import net.ijiangtao.tech.logging.api.httplog.event.ApiHttpLogEventPublisher;
import net.ijiangtao.tech.logging.api.httplog.response.APIResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.*;

/**
 * HttpLog切面
 *
 * @author ijiangtao
 * @create 2019-06-18 12:58
 **/
@Aspect
@Slf4j
public class HttpLogAspectV1 {

    @Autowired
    private ApiHttpLogEventPublisher publisher;

    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<>();

    private ThreadLocal<String> traceIdThreadLocal = new ThreadLocal<>();

    private ThreadLocal<ApiHttpLog> apiHttpLogThreadLocal = new ThreadLocal<>();

    /**
     * 注解切点
     */
    @Pointcut("@annotation(net.ijiangtao.tech.logging.api.httplog.annotation.HttpLog)")
    public void logAnnotation() {
        log.debug("log annnotation...");
    }

    /**
     * 记录request日志
     *
     * @param joinPoint
     */
    @Before("logAnnotation()")
    public void requestLog(JoinPoint joinPoint) {
        ApiHttpLog apiHttpLog = new ApiHttpLog();
        try {
            HttpLog httpLog = getLogAnnotation(joinPoint);
            if (null == httpLog || httpLog.ignoreRequest()) {
                return;
            }

            HttpServletRequest request = getRequest();

            traceIdThreadLocal.set(request.getRequestedSessionId() + "-" + UUID.randomUUID().toString().replace("-", ""));
            startTimeThreadLocal.set(System.currentTimeMillis());
            apiHttpLog.setTraceId(traceIdThreadLocal.get());
            apiHttpLog.setStartTime(startTimeThreadLocal.get());

            // ApiHttpLog: requestParameters json
            List<String> excludes = Arrays.asList(httpLog.exclude());
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> entry : parameterMap.entrySet()) {
                Map<String, String[]> requestParameters = new HashMap<>();
                if (!excludes.contains(entry.getKey())) {
                    requestParameters.put(entry.getKey(), entry.getValue());
                }
                apiHttpLog.setRequestParameters(JSON.toJSONString(requestParameters));
            }

            // ApiHttpLog: requestHeaders json
            if (httpLog.headerParams().length > 0) {
                Map<String, String> requestHeaders = new HashMap<>();
                for (String headerName : httpLog.headerParams()) {
                    requestHeaders.put(headerName, request.getHeader(headerName));
                }
                apiHttpLog.setRequestHeaders(JSON.toJSONString(requestHeaders));
            }

            // ApiHttpLog: requestBody json
            Object[] requestBody = getRequestBodyParam(joinPoint);
            apiHttpLog.setRequestBody(JSON.toJSONString(requestBody));

            // ApiHttpLog: HttpMethod
            apiHttpLog.setHttpMethod(request.getMethod());

            // ApiHttpLog: RequestPat
            apiHttpLog.setRequestPath(getRequestPath(request));

        } catch (Exception exception) {
            log.warn("requestLog fail![{}]", exception);
        } finally {
            apiHttpLogThreadLocal.set(apiHttpLog);
        }
    }

    /**
     * 记录resposne日志
     *
     * @param joinPoint
     * @param APIResponse
     */
    @AfterReturning(returning = "APIResponse", pointcut = "logAnnotation()")
    public void responseLog(JoinPoint joinPoint, APIResponse APIResponse) {
        try {
            HttpLog httpLog = getLogAnnotation(joinPoint);
            if (null == httpLog || httpLog.ignoreResponse()) {
                return;
            }
            // 记录 response
            ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
            apiHttpLog.setResponseBody(JSON.toJSONString(APIResponse));

        } catch (Exception exception) {
            log.warn("response log fail![{}]", exception);
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
        apiHttpLog.setCreatedTime(new Date());
        publisher.publish(apiHttpLog);

        startTimeThreadLocal.remove();
        traceIdThreadLocal.remove();
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
            HttpLog httpLog = getLogAnnotation(joinPoint);
            if (null == httpLog || httpLog.ignoreException()) {
                return;
            }

            // 记录Exception
            ApiHttpLog apiHttpLog = apiHttpLogThreadLocal.get();
            apiHttpLog.setResponseBody(JSON.toJSONString(e));

        } catch (Exception exception) {
            log.warn("print error log fail![{}]", exception);
        } finally {
            finallyBlock();
        }
    }

    private HttpLog getLogAnnotation(JoinPoint joinPoint) {
        if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                if (method.isAnnotationPresent(HttpLog.class)) {
                    return method.getAnnotation(HttpLog.class);
                }
            }
        }
        return null;
    }

    /**
     * 获取 RequestBody
     *
     * @param joinPoint
     * @return
     */
    private Object[] getRequestBodyParam(JoinPoint joinPoint) {
        if (joinPoint instanceof MethodInvocationProceedingJoinPoint) {
            Signature signature = joinPoint.getSignature();
            if (signature instanceof MethodSignature) {
                MethodSignature methodSignature = (MethodSignature) signature;
                Method method = methodSignature.getMethod();
                Parameter[] methodParameters = method.getParameters();
                if (null != methodParameters && Arrays.stream(methodParameters).anyMatch(p -> AnnotationUtils.findAnnotation(p, RequestBody.class) != null)) {
                    return joinPoint.getArgs();
                }
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
        return System.currentTimeMillis() - startTimeThreadLocal.get();
    }

}
