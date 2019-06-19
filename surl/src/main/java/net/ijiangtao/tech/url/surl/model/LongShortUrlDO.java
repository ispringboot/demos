package net.ijiangtao.tech.url.surl.model;

import lombok.Data;

import java.util.Date;

/**
 * DO
 *
 * @author ijiangtao
 * @create 2019-06-19 11:40
 **/
@Data
public class LongShortUrlDO {

    private Long id;

    /**
     * 短链接
     */
    private String shortUrl;

    /**
     * 长连接
     */
    private String longUrl;

    /**
     * 短链域名
     */
    private String shortDomain;

    /**
     * 长链域名
     */
    private String longDomain;

    /**
     * 链接路径
     */
    private String path;

    /**
     * 可变变量
     */
    private String pathVar;

    /**
     * 该短链接是否可用：1可用 0不可用
     */
    private Short status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
