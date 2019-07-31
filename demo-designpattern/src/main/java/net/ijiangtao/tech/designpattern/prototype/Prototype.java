package net.ijiangtao.tech.designpattern.prototype;

import lombok.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个抽象原型
 *
 * @author ijiangtao
 * @create 2019-07-17 21:20
 **/
@Getter
@Setter
@NoArgsConstructor  //lombok : 无参构造
@AllArgsConstructor //lombok : 全参构造
@ToString
public class Prototype implements Cloneable{

    private Integer id;

    private String name;

    private Map<String, Double> scores;

    @Override
    protected Prototype clone() {
        Prototype filePrototype = null;
        try {
            //有了下面这句话，基本类型就能克隆了
            filePrototype = (FileConcretePrototype) super.clone();

            //下面要对每一个复杂对象进行分别克隆
            filePrototype.scores = (Map<String, Double>) ((HashMap)this.scores).clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return filePrototype;
    }

}
