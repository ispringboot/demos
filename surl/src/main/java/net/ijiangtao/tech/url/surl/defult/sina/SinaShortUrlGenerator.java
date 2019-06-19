package net.ijiangtao.tech.url.surl.defult.sina;

import com.alibaba.fastjson.JSON;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 短链生成器
 *
 * @author ijiangtao
 * @create 2019-06-19 17:42
 **/
@Slf4j
@Builder
public class SinaShortUrlGenerator {

    public SinaGenerateShortUrlResult generate(List<String> pathVarList) {

        //生成长链
        Map<String, String> longUrlPathVarMap = buildPathVarLongUrlMap(pathVarList);

        //生成短链
        List<String> longUrlList = new ArrayList<>(longUrlPathVarMap.keySet());
        List<SinaGenerateShortUrlRespose> shortUrlList = buildShortUrlByLongUrlList(longUrlList);

        //构造长链和短链Map
        Map<String, String> pathVarLongUrlMap = new HashMap<>();
        Map<String, String> pathVarShortgUrlMap = new HashMap<>();
        for (SinaGenerateShortUrlRespose generateShortUrlRespose : shortUrlList) {
            String pathVar = longUrlPathVarMap.get(generateShortUrlRespose.getLongUrl());
            if (StringUtils.isEmpty(pathVar)) {
                continue;
            }
            pathVarShortgUrlMap.put(pathVar, generateShortUrlRespose.getShortUrl());
            pathVarLongUrlMap.put(pathVar, generateShortUrlRespose.getLongUrl());
        }

        //构造返回值
        return SinaGenerateShortUrlResult
                .builder()
                .pathVarList(pathVarList)
                .pathVarLongUrlMap(pathVarLongUrlMap)
                .pathVarShortgUrlMap(pathVarShortgUrlMap)
                .build();
    }

    /**
     * @param pathVarList
     * @return key: longUrl , value: pathVar
     */
    private Map<String, String> buildPathVarLongUrlMap(List<String> pathVarList) {
        Map<String, String> pathVarLongUrlMap = new HashMap<>();
        for (String pathVar : pathVarList) {
            if (pathVarLongUrlMap.containsValue(pathVar)) {
                continue;
            }
            String longUrl = SinaLongUrlBuilder.build(pathVar);
            pathVarLongUrlMap.put(longUrl, pathVar);
        }
        return pathVarLongUrlMap;
    }


    /**
     * 根据长链生成短链
     *
     * @param longUrlList
     * @return
     */
    private List<SinaGenerateShortUrlRespose> buildShortUrlByLongUrlList(List<String> longUrlList) {
        List<SinaGenerateShortUrlRespose> longShortUrlList = new ArrayList<>();
        List<List<String>> partitionLongUrlList = SinaShortUrlUtil.splitList(longUrlList, 20);
        for (List<String> partitionLongUrls : partitionLongUrlList) {
            String requestUrl = SinaShortUrlUtil.buildRequestUrl(partitionLongUrls);
            String result = SinaShortUrlUtil.getResponseFromSina(requestUrl);
            List<SinaGenerateShortUrlRespose> resposeList = JSON.parseArray(result, SinaGenerateShortUrlRespose.class);
            if (!CollectionUtils.isEmpty(resposeList)) {
                longShortUrlList.addAll(resposeList);
            }
        }
        return longShortUrlList;
    }

    // Tests
    public static void main(String[] args) {
        List<String> pathVarList = new ArrayList<>();
        pathVarList.add("java");
        pathVarList.add("php");
        pathVarList.add("php");
        pathVarList.add("python");
        SinaGenerateShortUrlResult result = SinaShortUrlGenerator.builder().build().generate(pathVarList);
        log.info(JSON.toJSONString(result, true));
    }

}
