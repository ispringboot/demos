package net.ijiangtao.tech.ispringboot.redis.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * t
 *
 * @author ijiangtao
 * @create 2019-05-21 23:26
 **/
@Component
@Slf4j
public class RestTest implements IRestTest {

    //调用spring里面RestTemplate需要配置bean
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void test() {

        String url="https://stage.adidas.com.cn/api/v1/cache/inventory";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String plainClientCredentials="adidas:ad20170731";
        String base64ClientCredentials = new String(Base64.encodeBase64(plainClientCredentials.getBytes()));
        headers.add("Authorization", "Basic " + base64ClientCredentials);

        HttpEntity<Form> entity = new HttpEntity<Form>(Form.builder()
                .timestamp("2019-05-17")
                .sign("6efa1ad060efe76e39752afbee160bc8d4d90879")
                .articleCodes(Arrays.asList("EE3710")).build(), headers);

        ResponseEntity<String> entityResponse = restTemplate.postForEntity(url, entity, String.class);

        String body = entityResponse.getBody();
        log.info(body);
    }
}
