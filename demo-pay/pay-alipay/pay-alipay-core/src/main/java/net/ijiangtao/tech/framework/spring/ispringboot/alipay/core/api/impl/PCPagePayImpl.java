package net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api.impl;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api.PCPagePay;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain.*;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.domain.*;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.enums.ResponseEnum;

/**
 *
 * @Desctiption: PC页面支付
 * @Date: 2019/01/24 21:14
 */
@Slf4j
public class PCPagePayImpl implements PCPagePay {

    private AlipayClient alipayClient;

    private String notifyUrl;

    private String returnUrl;

    @Override
    public AlipayTradePagePayResponse pay(PagePayDTO pagePayDTO) {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setNotifyUrl(notifyUrl);
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setBizContent(JSON.toJSONString(pagePayDTO));
        AlipayTradePagePayResponse response;
        try {
            response = alipayClient.pageExecute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            response = new AlipayTradePagePayResponse();
            response.setCode(ResponseEnum.PAGE_PAY_ERROR.getCode());
            response.setMsg(ResponseEnum.PAGE_PAY_ERROR.getMessage());
        }
        return response;
    }

    @Override
    public AlipayTradeQueryResponse query(PagePayQueryDTO queryDTO) {
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        alipayRequest.setBizContent(JSON.toJSONString(queryDTO));
        AlipayTradeQueryResponse response;
        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            response = new AlipayTradeQueryResponse();
            response.setCode(ResponseEnum.PAGE_PAY_QUERY_ERROR.getCode());
            response.setMsg(ResponseEnum.PAGE_PAY_QUERY_ERROR.getMessage());
        }
        return response;
    }

    @Override
    public AlipayTradeRefundResponse refund(RefundDTO refundDTO) {

        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        alipayRequest.setBizContent(JSON.toJSONString(refundDTO));
        AlipayTradeRefundResponse response;
        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            response = new  AlipayTradeRefundResponse();
            response.setCode(ResponseEnum.REFUND_ERROR.getCode());
            response.setMsg(ResponseEnum.REFUND_ERROR.getMessage());
        }
        return response;
    }

    @Override
    public AlipayTradeFastpayRefundQueryResponse refundQuery(RefundQueryDTO refundQueryDTO) {

        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        alipayRequest.setBizContent(JSON.toJSONString(refundQueryDTO));
        AlipayTradeFastpayRefundQueryResponse response;
        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            response = new AlipayTradeFastpayRefundQueryResponse();
            response.setCode(ResponseEnum.REFUND_QUERY_ERROR.getCode());
            response.setMsg(ResponseEnum.REFUND_QUERY_ERROR.getMessage());
        }

        return response;
    }

    @Override
    public AlipayTradeCloseResponse close(CloseDTO closeDTO) {
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        AlipayTradeCloseResponse response;
        try {
            response = alipayClient.execute(alipayRequest);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            response = new AlipayTradeCloseResponse();
            response.setCode(ResponseEnum.CLOSE_ERROR.getCode());
            response.setMsg(ResponseEnum.CLOSE_ERROR.getMessage());
        }
        return response;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public void setAlipayClient(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }
}
