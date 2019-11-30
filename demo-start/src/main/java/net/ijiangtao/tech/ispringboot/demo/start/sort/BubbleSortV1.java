package net.ijiangtao.tech.ispringboot.demo.start.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author ijiangtao
 * @create 2019-09-23 23:23
 **/
public class BubbleSortV1 {


    public static void sort1(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public static void sort2(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            //初始化有序标记
            boolean sorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //因为发生了替换，因此不是有序的
                    sorted = false;
                }
            }
            //如果没有发生任何交换，证明该轮已经全部有序，不需要再继续比较
            if (sorted) {
                break;
            }
        }
    }

    public static void sort3(int array[]) {
        for (int i = 0; i < array.length - 1; i++) {
            boolean sorted = true;
            //无序边界，每次只比较到这里为止
            int sortBorder = array.length - 1;
            for (int j = 0; j < sortBorder; j++) {
                int temp = 0;
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    sorted = false;
                    //把无序边界更新为最后一次交换元素的位置
                    sortBorder = j;
                }
            }
            if (sorted) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] array1 = new int[]{3, 2, 9, 6, 1, 7};
        sort1(array1);
        System.out.println(Arrays.toString(array1));

        int[] array2 = new int[]{3, 2, 9, 6, 1, 7};
        sort1(array2);
        System.out.println(Arrays.toString(array2));

        int[] array3 = new int[]{3, 2, 9, 6, 1, 7};
        sort1(array3);
        System.out.println(Arrays.toString(array3));

    }
}
