package net.ijiangtao.tech.ispringboot.alipay.core.util;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.ijiangtao.tech.ispringboot.alipay.core.config.AliPayProperties;

import java.util.Map;

/**
 *
 * @Desctiption:
 * @Date: 2019/01/25 16:47
 */
@Slf4j
public class AliPaySignUtil {

    @Setter
    private AliPayProperties payProperties;

    public boolean signVerified(Map<String, String> paramsMap){
        boolean signVerified = false;//调用SDK验证签名
        try {
            //FIXME 沙箱环境验签失败？？？？
            signVerified = AlipaySignature.rsaCheckV1(paramsMap, payProperties.getAlipayPublicKey(), payProperties.getCharset(), payProperties.getSignType());

        } catch (AlipayApiException e) {
            e.printStackTrace();
            log.error("安全签名验证失败："+ JSON.toJSONString(paramsMap),e);
        }
        return signVerified;
    }

}
