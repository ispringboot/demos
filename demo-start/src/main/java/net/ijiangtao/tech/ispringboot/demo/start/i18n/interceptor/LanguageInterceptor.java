package net.ijiangtao.tech.demo.start.i18n.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.demo.start.i18n.util.LanguageUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
public class LanguageInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("preHandle:请求前调用");

        //Request只能根据请求头 Accept-Language

        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);

        java.util.Locale local= localeResolver.resolveLocale(request);

        log.info("US  "+Locale.US.equals(local));
        log.info("SIMPLIFIED_CHINESE "+Locale.SIMPLIFIED_CHINESE.equals(local));

        log.info(local.toString());
        log.info(local.getDisplayName());

        LanguageUtil.setCurrentLang(local.toString());
        log.info(LanguageUtil.getCurrentLang());

        //Locale.SIMPLIFIED_CHINESE
        //返回 false 则请求中断
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        log.info("postHandle:请求后调用");

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        log.info("afterCompletion:请求调用完成后回调方法，即在视图渲染完成后回调");

    }

}
