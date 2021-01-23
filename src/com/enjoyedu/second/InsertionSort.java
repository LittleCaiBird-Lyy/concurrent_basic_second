package com.enjoyedu.second;
/*
    类说明：简单插入排序（升序）
 */
public class InsertionSort {
    public static int[] sort(int[] array){
        if (array.length == 0){
            return array;
        }
        /*当前待排序数据，该元素之前的元素均已被排序过*/
        int curValue;
        //默认有一个被排序所以array.length - 1
        for (int i = 0; i < array.length - 1; i++) {
            /*已被排序数据的索引*/
            int preIndex = i;
            curValue = array[preIndex+1];
            System.out.println("待排序元素索引:"+(i + 1)+"，值为：" +curValue+
                    ",已被排序数据的索引:"+preIndex);
            /*在已被排序过数据中倒序寻找合适的位置，如果当前待排序数据比比较的元素要小，
            将比较的元素元素后移一位*/
            while (preIndex>=0 && curValue<array[preIndex]){
                //当前元素往后移一位
                array[preIndex+1] = array[preIndex];
                preIndex--;
            }
            /*while循环结束时，说明已经找到了当前待排序数据的合适位置，插入*/
            array[preIndex+1] = curValue;
            System.out.println("本轮被插入排序后的数组");
            PrintArray.print(array);
            System.out.println("--------------------");
        }
        return array;
    }
    public static void main(String[] args) {
        com.enjoyedu.PrintArray.print(com.enjoyedu.PrintArray.SRC);
        System.out.println("============================================");
        int[] dest = com.enjoyedu.InsertionSort.sort(com.enjoyedu.PrintArray.SRC);
        com.enjoyedu.PrintArray.print(dest);
    }
}
