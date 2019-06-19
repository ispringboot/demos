package net.ijiangtao.tech.url.surl.defult.sina;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * util
 *
 * @author ijiangtao
 * @create 2019-06-19 18:52
 **/
public class SinaShortUrlUtil {

    private static final String APP_KEY = "2815391962";

    public static List<List<String>> splitList(List<String> list, int groupSize) {
        return Lists.partition(list, groupSize);
    }

    public static String getResponseFromSina(String requestUrl) {
        return new RestTemplate().getForObject(requestUrl, String.class);
    }

    public static String buildRequestUrl(List<String> longUrls) {
        if (CollectionUtils.isEmpty(longUrls)) {
            return null;
        }
        String address = "";
        for (String url : longUrls) {
            String longNew = url.replace("&", "%26");
            address += "&url_long=" + url;
        }
        return "http://api.t.sina.com.cn/short_url/shorten.json?source=" + APP_KEY + address;
    }

}
