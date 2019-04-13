package net.ijiangtao.tech.framework.spring.ispringboot.demo.i18n.interceptor;

import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.framework.spring.ispringboot.demo.i18n.util.LanguageUtil;
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

        //请求头 当前语言
        // Accept-Language: zh-CN
        // Accept-Language: en-US
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale local= localeResolver.resolveLocale(request);

        log.info("local={} , localDisplayName={}",local.toString(),local.getDisplayName());

        LanguageUtil.setCurrentLang(local.toString());
        log.info("LanguageUtil.getCurrentLang() = {}",LanguageUtil.getCurrentLang());

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
