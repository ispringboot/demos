package net.ijiangtao.tech.url.surl.defult.sina;

/**
 * sina
 *
 * @author ijiangtao
 * @create 2019-06-19 17:43
 **/
public class SinaLongUrlBuilder {

    private static final String long_url_prefix = "https://www.baidu.com/s?wd=";

    public static String build(String pathVar) {
        return long_url_prefix + pathVar;
    }
    
}
