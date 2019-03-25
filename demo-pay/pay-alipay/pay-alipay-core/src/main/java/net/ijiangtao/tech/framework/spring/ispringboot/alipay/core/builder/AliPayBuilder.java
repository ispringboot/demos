package net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.builder;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import lombok.Setter;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api.PCPagePay;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.api.impl.PCPagePayImpl;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.util.AliPaySignUtil;
import net.ijiangtao.tech.framework.spring.ispringboot.alipay.core.config.AliPayProperties;

import java.util.Objects;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 10:22
 */
public class AliPayBuilder {


    private static AliPayBuilder aliPayBuilder;

    @Setter
    private AlipayClient alipayClient;

    @Setter
    private AliPayProperties properties;

    public static final String DEFAULT_FORMAT = "json";

    private AliPayBuilder(){}

    public static final AliPayBuilder builder(AliPayProperties properties){
        Objects.requireNonNull(properties," properties must be not null");
        if(aliPayBuilder == null){
            aliPayBuilder = new AliPayBuilder();
        }
        AlipayClient alipayClient = new DefaultAlipayClient(
                properties.getGatewayUrl(),
                properties.getAppId(),
                properties.getMerchantPrivateKey(),
                DEFAULT_FORMAT,properties.getCharset(),
                properties.getAlipayPublicKey(),properties.getSignType());
        aliPayBuilder.setAlipayClient(alipayClient);
        aliPayBuilder.setProperties(properties);
        return aliPayBuilder;
    }

    public PCPagePay preparePcPagePay(){
        PCPagePayImpl pcPagePay = new PCPagePayImpl();
        pcPagePay.setAlipayClient(this.alipayClient);
        pcPagePay.setNotifyUrl(this.properties.getNotifyUrl());
        pcPagePay.setReturnUrl(this.properties.getReturnUrl());
        return pcPagePay;
    }

    public AliPaySignUtil prepareSignUtil(){
        AliPaySignUtil paySignUtil = new AliPaySignUtil();
        paySignUtil.setPayProperties(this.properties);
        return paySignUtil;
    }



}
