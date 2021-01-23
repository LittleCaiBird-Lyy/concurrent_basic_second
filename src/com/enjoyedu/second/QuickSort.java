package com.enjoyedu.second;
/**
 * 类说明：快速排序
 */
public class QuickSort {

    public static int[] sort(int[] array , int start , int end){
        if (array.length < 1 || start < 0 || end >= array.length || start > end){
            return null;
        }
        /*数据分割成独立的两部分时，从哪儿分区的指示器*/
        int zoneIndex = partition(array,start,end);
        if (zoneIndex > start){
            sort(array,start,zoneIndex-1);
        }
        if (zoneIndex < end){
            sort(array,zoneIndex+1,end);
        }
        System.out.println("本轮排序后的数组");
        PrintArray.printIndex(array,start,end);
        System.out.println("--------------------");
        return array;
    }


    /**
     * 快速排序算法——partition
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] array , int start , int end){
        int chaos = (int) (start + Math.random()*(end - start + 1));
        System.out.println("开始下标："+start+",结束下标:"+end+",基准数下标："
                +chaos+",元素值:"+array[chaos]);
        /*zoneIndex是分割指示器
        从业务上来说：比基准数小的，放到指示器的左边，比基准数大的，放到指示器的右边，
        * 但在实际实现时，通过移动比基准数小的元素和分割指示器本身也可以达到一样的效果*/
        int zoneIndex = start -1;
        /*将基准数和数组尾元素交换位置*/
        swap(array,zoneIndex,end);
        for (int i = start; i <= end; i++) {
            /*当前元素小于等于基准数*/
            if (array[i] <= array[zoneIndex]){
                zoneIndex++;
                /*当前元素在分割指示器的右边时，交换当前元素和分割指示器元素*/
                if (i>zoneIndex){
                    swap(array,array[i],array[zoneIndex]);
                }
            }
            System.out.println("zoneIndex："+zoneIndex+",i:"+i);
            PrintArray.printIndex(array,start,end);
        }
        System.out.println("---------------");
        return  zoneIndex;
    }

    /**
     * 交换数组内两个元素
     * @param array
     * @param i
     * @param j
     */
    public static int[] swap(int[] array , int i , int j){
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
        return array;
    }

    public static void main(String[] args) {
        com.enjoyedu.PrintArray.print(com.enjoyedu.PrintArray.SRC);
        System.out.println("============================================");
        int[] dest = com.enjoyedu.QuickSort.sort(com.enjoyedu.PrintArray.SRC,0, com.enjoyedu.PrintArray.SRC.length-1);
        com.enjoyedu.PrintArray.print(dest);
    }
}
