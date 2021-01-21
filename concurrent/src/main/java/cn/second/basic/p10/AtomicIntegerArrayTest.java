package cn.second.basic.p10;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * AtomicIntegerArray的基本操作
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) {
        //1.创建一个指定元素的原子数组
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
        //[0,.....0]
        System.out.println(atomicIntegerArray);
        //2.返回指定位置的元素
        System.out.println(atomicIntegerArray.get(0));
        System.out.println(atomicIntegerArray.get(1));
        //3.设置指定位置的元素
        atomicIntegerArray.set(0,10);
        //在设置数组元素的新值时，同时返回数组元素的旧值
        System.out.println(atomicIntegerArray.getAndSet(1,11));//0
        System.out.println(atomicIntegerArray);
        //4.修改数组元素的值，把数组元素加上某个值
        System.out.println(atomicIntegerArray.addAndGet(0,22));//32
        System.out.println(atomicIntegerArray.getAndAdd(1,33));//11
        //[32, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray);

        //5.CAS操作: 如果数组中索引值为0的元素的值是32，就修改为222
        System.out.println(atomicIntegerArray.compareAndSet(0,32,222));//true
        //[222, 44, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray);
        System.out.println(atomicIntegerArray.compareAndSet(1,111,222));//false
        System.out.println(atomicIntegerArray);
        //6.自增/自减
        System.out.println(atomicIntegerArray.incrementAndGet(0));//223 相当于前缀
        System.out.println(atomicIntegerArray.getAndIncrement(1));//44  相当于后缀
        //[223, 45, 0, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray);
        //-1
        System.out.println(atomicIntegerArray.decrementAndGet(2));
        //[223, 45, -1, 0, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray);
        //0
        System.out.println(atomicIntegerArray.getAndDecrement(3));
        //[223, 45, -1, -1, 0, 0, 0, 0, 0, 0]
        System.out.println(atomicIntegerArray);

    }

}
