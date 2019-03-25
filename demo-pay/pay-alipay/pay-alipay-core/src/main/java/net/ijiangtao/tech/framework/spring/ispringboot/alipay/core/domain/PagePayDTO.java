package net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/24 21:02
 */
@Data
public class PagePayDTO {

    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "product_code")
    private String productCode = "FAST_INSTANT_TRADE_PAY";
    @JSONField(name = "total_amount")
    private String totalAmount;
    @JSONField(name = "subject")
    private String subject;
    @JSONField(name = "body")
    private String body;
    @JSONField(name = "goods_detail")
    private String goodsDetail;
    @JSONField(name = "passback_params")
    private String passbackParams;
    @JSONField(name = "extend_params")
    private String extendParams;
    @JSONField(name = "goods_type")
    private String goodsType;
    @JSONField(name = "timeout_express")
    private String timeoutExpress;
    @JSONField(name = "enable_pay_channels")
    private String enablePayChannels;
    @JSONField(name = "disable_pay_channels")
    private String disablePayChannels;


}
