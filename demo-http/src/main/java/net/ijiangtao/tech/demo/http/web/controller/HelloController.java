package net.ijiangtao.tech.demo.http.web.controller;

import com.alibaba.fastjson.JSON;
import net.ijiangtao.tech.demo.http.model.User;
import net.ijiangtao.tech.demo.http.web.cache.AuthorityCache;
import net.ijiangtao.tech.demo.http.web.dto.Hello;
import net.ijiangtao.tech.demo.http.web.annotation.API;
import net.ijiangtao.tech.demo.http.web.response.APIResponse;
import net.ijiangtao.tech.demo.http.web.annotation.AuthorizedUser;
import net.ijiangtao.tech.demo.http.web.response.ResponseStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * hello
 *
 * @author ijiangtao
 * @create 2019-06-05 10:44
 **/
@Controller
public class HelloController extends BaseController {

    @RequestMapping(value = "/token")
    @API
    public APIResponse<String> getToken( //
                                         @RequestParam String key,
                                         @RequestParam int auth,
                                         HttpServletRequest request,
                                         HttpServletResponse response) {
        String token = AuthorityCache.getToken(new User(key, auth));
        if (null == token) {
            return APIResponse.build(ResponseStatus.CLIENT_ERROR_NOT_FOUND);
        } else {
            return APIResponse.build(ResponseStatus.SUCCESS, token);
        }

    }

    @RequestMapping(value = "/hello1")
    @ResponseBody //不会走自定义的return value handler
    public APIResponse<Hello> hello1( //
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        return APIResponse.build(ResponseStatus.SUCCESS, new Hello("hi1"));
    }

    @RequestMapping(value = "/hello2")
    @API(authorizedRequired = true, adaptHttpStatusCode = true)
    public APIResponse<Hello> hello2( //
                                      @AuthorizedUser User user,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        return APIResponse.build(ResponseStatus.CLIENT_ERROR_BAD_REQUEST_EMPTY_PARAM, new Hello("hi2: " + JSON.toJSONString(user)));
    }


    @RequestMapping(value = "/hello3")
    @API(authorizedRequired = true)
    public APIResponse<Hello> hello3( //
                                      @AuthorizedUser User user,
                                      HttpServletRequest request,
                                      HttpServletResponse response) {

        return APIResponse.build(ResponseStatus.SUCCESS, new Hello("hi2: " + JSON.toJSONString(user)));
    }

    @RequestMapping(value = "/hello4")
    @API(adaptHttpStatusCode = true)
    public APIResponse<Hello> hello4( //
                                      HttpServletRequest request,
                                      HttpServletResponse response) {
        return APIResponse.build(ResponseStatus.SERVER_ERROR_SERVICE_UNAVAILABLE_PART, new Hello("hi4"));
    }

    @RequestMapping(value = "/hello5", method = RequestMethod.GET)
    @API(adaptHttpStatusCode = true)
    public APIResponse<Hello> hello5(HttpServletRequest request,
                                     HttpServletResponse response) {
        Cookie cookie = new Cookie("name1", "v111");
        cookie.setPath("/itao/123");
        response.addCookie(cookie);

        Cookie cookie2 = new Cookie("name1", "v111");
        cookie2.setPath("/itao/456");
        response.addCookie(cookie2);
        return APIResponse.build(ResponseStatus.SUCCESS, new Hello("hi4"));
    }

    @RequestMapping(value = "/hello6")
    public Object hello6(HttpServletRequest request,
                         HttpServletResponse response) {


        String cv = getCookieValueTemp(request, "name1", 2);

        ResponseEntity<APIResponse<Hello>> responseEntity = new ResponseEntity<APIResponse<Hello>>(//
                APIResponse.build(ResponseStatus.SERVER_ERROR_SERVICE_UNAVAILABLE_PART, new Hello(cv)),
                HttpStatus.SERVICE_UNAVAILABLE);


        return responseEntity;
    }

    public static String getCookieValueTemp(HttpServletRequest request, String cookieName, int v) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(cookieName) && v == cookie.getVersion()) {
                return cookie.getValue();
            }
        }
        return null;
    }

}
