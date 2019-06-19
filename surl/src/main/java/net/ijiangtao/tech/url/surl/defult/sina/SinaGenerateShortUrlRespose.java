package net.ijiangtao.tech.url.surl.defult.sina;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * GenerateShortUrlRespose
 *
 * @author ijiangtao
 * @create 2019-06-19 17:45
 **/
@Data
public class SinaGenerateShortUrlRespose {

    /**
     * 短链接
     */
    @JSONField(name = "url_short")
    private String shortUrl;

    /**
     * 长连接
     */
    @JSONField(name = "url_long")
    private String longUrl;

    /**
     * 响应类型
     */
    @JSONField(name = "type")
    private Integer type;

}
