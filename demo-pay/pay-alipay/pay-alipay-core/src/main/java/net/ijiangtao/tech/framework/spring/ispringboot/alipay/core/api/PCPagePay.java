package net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api;

import com.alipay.api.response.*;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain.*;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain.*;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/24 18:22
 */
public interface PCPagePay{
    /**
     *
     * @Desctiption:
     * 统一收单下单并支付页面接口
     * @Date: 2019/01/24 21:13
     */
    AlipayTradePagePayResponse pay(PagePayDTO pagePayDTO);

    /**
     *
     * @Desctiption:
     * 统一收单线下交易查询接口
     * @Date: 2019/01/25 9:46
     */
    AlipayTradeQueryResponse query(PagePayQueryDTO queryDTO);

    /**
     *
     * @Desctiption:
     * 统一收单交易退款接口
     * @Date: 2019/01/25 9:50
     */
    AlipayTradeRefundResponse refund(RefundDTO refundDTO);

    /**
     *
     * @Desctiption:
     * 统一收单交易退款查询接口
     * @Date: 2019/01/25 9:50
     */
    AlipayTradeFastpayRefundQueryResponse refundQuery(RefundQueryDTO refundQueryDTO);

    /**
     *
     * @Desctiption:
     * 统一收单交易关闭接口
     * @Date: 2019/01/25 9:57
     */
    AlipayTradeCloseResponse close(CloseDTO closeDTO);


}
