package net.ijiangtao.tech.framework.spring.ispringboot.demo.demostart.jcf;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class JcfTest {

    public static void main(String[] args) {

        List<String> arrayList = new ArrayList<>();
        arrayList.add("1.ijiangtao.net");
        arrayList.add("2.ijiangtao.net");
        arrayList.add("3.ijiangtao.net");
        arrayList.add("4.ijiangtao.net");

        //arrayList.
        //arrayList.add(index,element);
        //将某个对象复制n份放入一个List中，其中每个元素都是存储的同一个对象的引用。
        List<Date> copiesList = Collections.nCopies(6, new Date());
        log.info("*****************************[{}]", copiesList.get(2).equals(copiesList.get(5)));
        //输出：*****************************[true]

        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.push("1.ijiangtao.net");
        linkedList.push("2.ijiangtao.net");
        log.info(linkedList.pop());
        log.info(linkedList.pop());

        linkedList.push("3.ijiangtao.net");
        linkedList.push("4.ijiangtao.net");
        //linkedList.offer()
        // log.info(linkedList.removeFirstOccurrence());
        log.info(linkedList.peek());

        TreeSet<Item> treeSet = new TreeSet<>();
        treeSet.add(new Item("333"));
        treeSet.add(new Item("22"));
        treeSet.add(new Item("1"));
        log.info("first name={}",treeSet.first().name);

        TreeSet<Item> itemSet=new TreeSet<>((u,v)->
                u.name.length()<v.name.length()?-1:1
                );
        itemSet.add(new Item("333"));
        itemSet.add(new Item("22"));
        itemSet.add(new Item("1"));
        log.info("first name={}",itemSet.first().name);

    }

    @AllArgsConstructor
    public static class Item implements Comparable {

        @NonNull
        String name;

        //把name最短的Item放在第一个位置
        @Override
        public int compareTo(Object o) {
            return ((Item) o).name.length() > this.name.length() ? -1 : 1;
        }
    }
}
