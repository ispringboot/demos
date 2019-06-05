package net.ijiangtao.tech.demo.http.web.response;

import net.ijiangtao.tech.demo.http.constant.APIConstant;
import net.ijiangtao.tech.demo.http.model.User;
import net.ijiangtao.tech.demo.http.web.cache.AuthorityCache;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

public class AuthorityResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthorizedUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        String authorityToken = webRequest.getNativeRequest(HttpServletRequest.class).getHeader(APIConstant.AUTHORITY_TOKEN);
        if (null == authorityToken || authorityToken.length() < 1) {
            return null;
        }

        User user = AuthorityCache.getUser(authorityToken);
        if (null == user) {
            return null;
        }

        return user;

    }

}
