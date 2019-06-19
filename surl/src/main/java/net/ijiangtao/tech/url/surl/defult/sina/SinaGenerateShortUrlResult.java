package net.ijiangtao.tech.url.surl.defult.sina;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * result to return
 *
 * @author ijiangtao
 * @create 2019-06-19 17:34
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SinaGenerateShortUrlResult {

    List<String> pathVarList;

    Map<String, String> pathVarLongUrlMap;

    Map<String, String> pathVarShortgUrlMap;

}
