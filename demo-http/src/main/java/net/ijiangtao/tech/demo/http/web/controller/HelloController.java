package net.ijiangtao.tech.demo.http.web.controller;

import com.alibaba.fastjson.JSON;
import net.ijiangtao.tech.demo.http.model.User;
import net.ijiangtao.tech.demo.http.web.cache.AuthorityCache;
import net.ijiangtao.tech.demo.http.web.dto.Hello;
import net.ijiangtao.tech.demo.http.web.response.API;
import net.ijiangtao.tech.demo.http.web.response.APIResponse;
import net.ijiangtao.tech.demo.http.web.response.AuthorizedUser;
import net.ijiangtao.tech.demo.http.web.response.ResponseStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
