package net.ijiangtao.tech.designpattern.prototype;

import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * 定义具体原型
 *
 * @author ijiangtao
 * @create 2019-07-17 21:21
 **/
@EqualsAndHashCode // lombok : over write equals and hashCode methods based on relevant fields.
public class FileConcretePrototype extends Prototype {

    public FileConcretePrototype(Integer id, String name, Map<String, Double> scores) {
        super(id, name, scores);
    }

    public void show() {
        System.out.println(" File Data : ");
        System.out.println("file name : " + this.getName());
        System.out.println("file id : " + this.getId());
        System.out.println("file scores : " + this.getScores());
    }

}
