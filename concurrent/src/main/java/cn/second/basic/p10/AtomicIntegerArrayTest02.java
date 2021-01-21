package cn.second.basic.p10;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 在多线程中使用AtomicIntegerArray原子数组
 */
public class AtomicIntegerArrayTest02 {
    //定义原子数组
    static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);
    public static void main(String[] args) {
        //定义线程数组
        Thread[] threads = new Thread[10];
        //给线程数组元素赋值
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new AddThread();
        }
        //开启子线程
        for (Thread thread : threads){
            thread.start();
        }

        //在主线程中 查看自增完以后原子数组中的各个元素值，在主线程中需要在所有子线程都指向完后再查看
        //把所有的子线程合并到当前主线程中
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(atomicIntegerArray);
    }

    //定义一个线程类，在线程类中修改原子数组
    static class AddThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                //把原子数组的每个元素自增一千次
                for (int j = 0; j < atomicIntegerArray.length(); j++) {
                    //给数组每一个元素自增一次
                    atomicIntegerArray.getAndIncrement( j % atomicIntegerArray.length());
                }
            }
        }
    }
}
