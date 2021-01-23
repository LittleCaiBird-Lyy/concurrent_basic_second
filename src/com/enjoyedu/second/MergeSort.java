package com.enjoyedu.second;

import com.enjoyedu.PrintArray;

import java.util.Arrays;

/**
 * 类说明：归并排序
 */
public class MergeSort {

    public static int[] sort(int[] array){
        if (array.length < 2) {
        return array;
        }
        int mid = array.length/2;
        int[] left = Arrays.copyOfRange(array, 0 ,mid);
        int[] right = Arrays.copyOfRange(array,mid,array.length);
        return merge(sort(left),sort(right));
    }


/**
 * 归并排序——将两段排序好的数组结合成一个排序数组
 *
 * @param left
 * @param right
 * @return
 */
public static int[] merge(int[] left ,int[] right) {
    //创建一个存放结果的空白数组
    int[] result = new int[left.length + right.length];
    for (int index = 0, i = 0, j = 0; index < result.length; index++) {
        /*左边数组已经取完，完全取右边数组的值即可*/
        if (index > left.length) {
            result[index] = right[j++];
            /*右边数组已经取完，完全取左边数组的值即可*/
        } else if (index > right.length) {
            result[index] = left[i++];
            /*左边数组的元素值大于右边数组，取右边数组的值*/
        } else if (left[i] > right[j]) {
            result[index] = right[j++];
        } else {
            /*右边数组的元素值大于左边数组，取左边数组的值*/
            result[index] = left[i++];
        }
    }
    return result;
}
    public static void main(String[] args) {
        com.enjoyedu.PrintArray.print(com.enjoyedu.PrintArray.SRC);
        System.out.println("============================================");
        int[] dest = com.enjoyedu.MergeSort.sort(com.enjoyedu.PrintArray.SRC);
        PrintArray.print(dest);
    }
}
