package net.ijiangtao.tech.ispringboot.alipay.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/24 23:01
 */
@Data
public class RefundDTO {


    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "refund_amount")
    private String refundAmount;
    @JSONField(name = "refund_currency")
    private String refundCurrency;
    @JSONField(name = "refund_reason")
    private String refundReason;
    @JSONField(name = "out_request_no")
    private String outRequestNo;
    @JSONField(name = "operator_id")
    private String operatorId;
    @JSONField(name = "store_id")
    private String storeId;
    @JSONField(name = "terminal_id")
    private String terminalId;
    @JSONField(name = "goods_detail")
    private String goodsDetail;
    @JSONField(name = "refund_royalty_parameters")
    private String refundRoyaltyParameters;
}
