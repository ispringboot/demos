package net.ijiangtao.tech.demo.http.web.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.http.constant.APIConstant;
import net.ijiangtao.tech.demo.http.model.User;
import net.ijiangtao.tech.demo.http.web.cache.AuthorityCache;
import net.ijiangtao.tech.demo.http.web.annotation.API;
import net.ijiangtao.tech.demo.http.web.response.APIResponse;
import net.ijiangtao.tech.demo.http.web.util.ResponseJsonWriter;
import net.ijiangtao.tech.demo.http.web.response.ResponseStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限校验器
 */
@Slf4j
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean auth = true;
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            API api = method.getMethodAnnotation(API.class);
            if (api == null) {
                api = method.getMethod().getDeclaringClass().getAnnotation(API.class);
            }
            if (api != null && api.authorizedRequired()) {
                log.debug("authorized required, RequestURI:[{}]", request.getRequestURI());
                auth = validate(request);
            } else {
                log.debug("no authorized required, RequestURI:[{}]", request.getRequestURI());
            }
        }
        if (!auth) {
            response.setStatus(ResponseStatus.CLIENT_ERROR_UNAUTHORIZED.getHttpStatusCode());
            ResponseJsonWriter.write(response, APIResponse.build(ResponseStatus.CLIENT_ERROR_UNAUTHORIZED));
            return false;
        }

        return true;
    }


    private boolean validate(HttpServletRequest request) {
        String authorityToken = request.getHeader(APIConstant.AUTHORITY_TOKEN);
        if (null == authorityToken || authorityToken.length() < 1) {
            return false;
        }

        User user = AuthorityCache.getUser(authorityToken);
        if (null == user) {
            return false;
        }
        return true;
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           @Nullable ModelAndView modelAndView) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                @Nullable Exception ex) throws Exception {
    }

    /**
     * This implementation is empty.
     */
    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response,
                                               Object handler) throws Exception {
    }

}
