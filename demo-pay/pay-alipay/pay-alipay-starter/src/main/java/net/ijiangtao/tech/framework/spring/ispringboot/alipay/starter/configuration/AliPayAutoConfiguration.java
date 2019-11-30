package net.ijiangtao.tech.ispringboot.alipay.starter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import net.ijiangtao.tech.ispringboot.alipay.core.api.PCPagePay;
import net.ijiangtao.tech.ispringboot.alipay.core.builder.AliPayBuilder;
import net.ijiangtao.tech.ispringboot.alipay.core.config.AliPayProperties;
import net.ijiangtao.tech.ispringboot.alipay.core.util.AliPaySignUtil;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/24 17:37
 */
@Configuration
@ComponentScan("net.ijiangtao.tech.ispringboot.alipay.starter")
public class AliPayAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "alipay")
    public AliPayProperties getAliPayProperties(){
        return new AliPayProperties();
    }

    @Bean
    public PCPagePay getPCPagePay(){
        return  AliPayBuilder.builder(getAliPayProperties()).preparePcPagePay();
    }

    @Bean
    public AliPaySignUtil getSignUtil(){
        return  AliPayBuilder.builder(getAliPayProperties()).prepareSignUtil();
    }

}
