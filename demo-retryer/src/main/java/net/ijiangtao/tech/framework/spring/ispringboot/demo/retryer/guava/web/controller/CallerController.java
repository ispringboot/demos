package net.ijiangtao.tech.framework.spring.ispringboot.demo.retryer.guava.web.controller;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RestController
public class CallerController {

    @PostMapping("/call")
    public String hello() throws Exception {

        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost("http://localhost:8080/response/hello");

        CloseableHttpResponse httpResponse = client.execute(httpPost);
        client.close();

        if (httpResponse.getStatusLine().getStatusCode() == 200) {
            // 得到httpResponse的实体数据
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                try {
                    BufferedReader bufferedReader = new BufferedReader(
                            new InputStreamReader(httpEntity.getContent(), "UTF-8"), 8 * 1024);
                    StringBuilder entityStringBuilder = new StringBuilder();
                    String line = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        entityStringBuilder.append(line + "/n");
                    }

                    String result = entityStringBuilder.toString();
                    System.out.println(result);
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


        return "";
    }

}
