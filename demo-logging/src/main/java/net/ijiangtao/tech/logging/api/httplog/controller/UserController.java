package net.ijiangtao.tech.logging.api.httplog.controller;

import net.ijiangtao.tech.logging.api.httplog.annotation.HttpLog;
import net.ijiangtao.tech.logging.api.httplog.domain.User;
import net.ijiangtao.tech.logging.api.httplog.response.APIResponse;
import net.ijiangtao.tech.logging.api.httplog.response.ResponseStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

/**
 * test
 *
 * @author ijiangtao
 * @create 2019-06-18 13:04
 **/
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * curl -X POST --header "Content-Type: application/json" -H 'username:12b4' --data '{"username":"12b4","password":"34ndd"}' -v 'http://localhost:8080/user/log?age=32'
     */
    @PostMapping("/log")
    @HttpLog()
    public APIResponse<User> addInfo(@RequestBody User user) {
        user.setId(new Random().nextInt());
        return APIResponse.build(ResponseStatus.SUCCESS, user);
    }

    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/123?age=32'
     */
    @GetMapping("/log/{id}")
    @HttpLog()
    public APIResponse<User> getInfo(@PathVariable("id") int id,
                                     @RequestParam("age") int age) {
        User user = new User();
        user.setId(id);
        user.setUsername(String.valueOf(new Random().nextLong()));
        user.setAge(age);
        return APIResponse.build(ResponseStatus.SUCCESS, user);
    }

    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/pwd/123?age=32'
     */
    @GetMapping("/log/pwd/{id}")
    @HttpLog(headerParams = "password")
    public APIResponse<User> getInfoWithPwd(@PathVariable("id") int id,
                                            @RequestHeader("username") String username,
                                            @RequestHeader("password") String password) {
        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        return APIResponse.build(ResponseStatus.SUCCESS, user);
    }

    /**
     * curl -H 'username:12b4' -H 'password:34ndd' -v 'http://localhost:8080/user/log/pwdExcludeResponse/123?age=32'
     */
    @GetMapping("/log/pwdExcludeResponse/{id}")
    @HttpLog(headerParams = "username", ignoreResponse = true)
    public APIResponse<User> getInfoWithPwd(@PathVariable("id") int id,
                                            @RequestParam("age") int age,
                                            @RequestHeader("password") String password) {
        User user = new User();
        user.setId(id);
        user.setPassword(password);
        user.setAge(age);
        return APIResponse.build(ResponseStatus.SUCCESS, user);
    }

}