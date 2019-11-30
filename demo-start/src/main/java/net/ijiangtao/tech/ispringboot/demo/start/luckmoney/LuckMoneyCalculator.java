package net.ijiangtao.tech.ispringboot.demo.start.luckmoney;

import java.math.BigDecimal;
import java.util.*;

/**
 * 红包计算器: 二倍均值法
 *
 * @author ijiangtao
 * @create 2019-09-21 23:21
 **/
public class LuckMoneyCalculator {

    /**
     * @param money 总钱数，单位为 分
     * @param num   参与抢红包的人数
     * @return
     */
    public static List<Integer> calculate(int money, int num) {

        List<Integer> result = new ArrayList<>();
        Random random = new Random();

        int restMoney = money;
        int restPeople = num;

        for (int i = 0; i < num - 1; i++) {
            int amount = random.nextInt(restMoney / restPeople * 2 - 2) + 1;
            restMoney -= amount;
            restPeople--;
            result.add(amount);
        }

        result.add(restMoney);

        return result;
    }

    public static void main(String[] args) {
        //Collections SynchronizedMap
        Map<String, Object> synchronizedMap = Collections.synchronizedMap(new HashMap<>());

        //5个人抢10块
        List<Integer> moneyList = calculate(1000, 5);
        for (Integer money : moneyList) {
            System.out.println("Grab luck money amout : " + new BigDecimal(money).divide(new BigDecimal(100)));
        }

        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            map.put(i, "" + i);
        }
        System.out.println(map.size());

    }
}
