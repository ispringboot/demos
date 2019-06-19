package net.ijiangtao.tech.demo.http.config;

import net.ijiangtao.tech.demo.http.web.handler.ResponseHandler;
import net.ijiangtao.tech.demo.http.web.interceptor.AuthorityInterceptor;
import net.ijiangtao.tech.demo.http.web.resolver.AuthorityResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfiger implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //自定义argumentResolvers
        argumentResolvers.add(new AuthorityResolver());
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        //自定义Controller返回hander
        handlers.add(new ResponseHandler());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //权限拦截器
        registry.addInterceptor(new AuthorityInterceptor()).addPathPatterns("/**");
    }

}

