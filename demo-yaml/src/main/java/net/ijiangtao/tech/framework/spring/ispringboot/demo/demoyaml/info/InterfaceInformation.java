package net.ijiangtao.tech.demo.demoyaml.info;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Component
@ConfigurationProperties(prefix="interface-information") //接收application.yml中的interface-information下面的属性
public class InterfaceInformation {

    //String类型的一定需要setter来接收属性值；maps, collections 和 arrays 不需要

    private String simpleProp;

    private String[] arrayProps;

    //接收listProps里面的属性值
    private List<String> listProps = new ArrayList<>();

    //接收prop1里面的属性值
    private Map<String, String> mapProps = new HashMap<>();

    public String getSimpleProp() {
        return simpleProp;
    }

    public void setSimpleProp(String simpleProp) {
        this.simpleProp = simpleProp;
    }

    //------------------------

    public String[] getArrayProps() {
        return arrayProps;
    }

    public List<String> getListProps() {
        return listProps;
    }

    public Map<String, String> getMapProps() {
        return mapProps;
    }
}
