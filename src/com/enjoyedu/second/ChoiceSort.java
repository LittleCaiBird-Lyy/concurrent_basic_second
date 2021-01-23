package com.enjoyedu.second;

import com.enjoyedu.PrintArray;

/*
    简单选择排序（升序）
 */
public class ChoiceSort {
    public static int[] sort(int[] array){
        if (array.length == 0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            /*最小数的下标，每个循环开始总是假设第一个数最小*/
            int minIndex = i;
            for (int j = i; j < array.length; j++) {
                if (array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            System.out.println("最小数为："+array[minIndex]);
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
            PrintArray.print(array);
            System.out.println("---------------");
        }
        return array;
    }
    public static void main(String[] args) {
        PrintArray.print(PrintArray.SRC);
        System.out.println("============================================");
        int[] dest = ChoiceSort.sort(PrintArray.SRC);
        PrintArray.print(dest);
    }
}
