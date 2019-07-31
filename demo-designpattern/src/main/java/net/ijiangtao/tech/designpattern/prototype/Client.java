package net.ijiangtao.tech.designpattern.prototype;

import java.util.HashMap;
import java.util.Map;

/**
 * client
 *
 * @author ijiangtao
 * @create 2019-07-17 21:27
 **/
public class Client {


    public static void main(String[] args) {

        String fileName = "scores文件";
        int fileId = 1;
        Map<String, Double> fileScores = new HashMap<>();
        fileScores.put("张三", 99.99);
        fileScores.put("李斯", 79.99);

        //第一步创建出一个实例对象
        FileConcretePrototype fileA = new FileConcretePrototype(fileId, fileName, fileScores);

        //第二步克隆出来几个
        FileConcretePrototype fileB = (FileConcretePrototype) fileA.clone();
        FileConcretePrototype fileC = (FileConcretePrototype) fileA.clone();

        //第三步：引用赋值
        FileConcretePrototype fileA2 =fileA;

        System.out.println(fileA.equals(fileB));//输出true
        System.out.println(fileC.equals(fileB));//输出true

        System.out.println(fileA.getClass()==fileC.getClass()); //输出true

        System.out.println(fileA==fileB);//输出false
        System.out.println(fileA==fileA2);//输出true

    }
}
