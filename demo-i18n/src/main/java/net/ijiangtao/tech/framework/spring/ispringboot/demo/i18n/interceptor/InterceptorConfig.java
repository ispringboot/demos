package net.ijiangtao.tech.demo.i18n.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LanguageInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/noi18n")
                .excludePathPatterns("/onelang");
    }

}
