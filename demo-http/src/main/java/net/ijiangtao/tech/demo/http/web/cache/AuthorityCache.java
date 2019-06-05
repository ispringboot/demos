package net.ijiangtao.tech.demo.http.web.cache;

import net.ijiangtao.tech.demo.http.model.User;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 模拟权限系统，用于注册和校验token
 *
 * @author ijiangtao
 * @create 2019-06-05 19:28
 **/
public class AuthorityCache {

    private static ConcurrentHashMap<String, User> authorityMap = new ConcurrentHashMap<>();

    private static Set<User> keySet = new HashSet<>();

    static {
        keySet.add(new User("luohua", 1));
        keySet.add(new User("liushui", 2));
        keySet.add(new User("busan", 1));
        keySet.add(new User("busi", 5));
    }

    public static String getToken(User user) {
        if (keySet.contains(user)) {
            String token = UUID.randomUUID().toString().replace("-", "");
            authorityMap.put(token, user);
            return token;
        }
        return null;
    }

    public static User getUser(String token) {
        return authorityMap.get(token);
    }
}
