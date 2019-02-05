package net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.enums;

import lombok.Getter;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/24 21:47
 */
@Getter
public enum ResponseEnum {
    PAGE_PAY_ERROR("90001","支付接口调用失败"),
    PAGE_PAY_QUERY_ERROR("90002","查询接口调用失败"),
    REFUND_ERROR("90003","退款接口调用失败"),
    REFUND_QUERY_ERROR("90004","退款查询接口调用失败"),
    CLOSE_ERROR("90005","关闭交易接口调用失败"),
    ;
    private String code;
    private String message;
    ResponseEnum(String code,String message){
        this.code = code;
        this.message = message;
    }
}
