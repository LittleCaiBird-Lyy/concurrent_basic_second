package cn.second.basic.p14;

import java.util.concurrent.locks.ReentrantLock;

/**
 * int getHoldCount() 方法可以返回当前线程调用Lock()方法的次数
 */
public class ReentrantLockTest02 {
    //定义一个锁对象
    static ReentrantLock lock = new ReentrantLock();

    public static void m1(){
        try{
            lock.lock();
            //打印线程调用lock()次数
            System.out.println(Thread.currentThread().getName() + "--hold count : " + lock.getHoldCount());
            //调用m2方法，ReentrantLock是可重入锁，在m2方法中可以再次获得该锁对象
            m2();
        }finally {
            lock.unlock();
        }
    }

    public static void m2(){
        try{
            lock.lock();
            //打印线程调用lock()次数
            System.out.println(Thread.currentThread().getName() + "-===hold count : " + lock.getHoldCount());
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        //main线程调用
        m1();
    }
}
