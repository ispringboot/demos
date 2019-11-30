package net.ijiangtao.tech.ispringboot.alipay.core.domain;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 9:58
 */
@Data
public class CloseDTO {

    @JSONField(name = "trade_no")
    private String tradeNo;
    @JSONField(name = "out_trade_no")
    private String outTradeNo;
    @JSONField(name = "operator_id")
    private String operatorId;

}
