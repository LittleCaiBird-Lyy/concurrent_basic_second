package cn.second.basic.p13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock锁的可重入性
 */
public class ReentrantLockTest04 {

    static class Subthread extends Thread{
        //定义锁对象-->static是让不同Thread持有同一个锁对象
        private static Lock lock = new ReentrantLock();
        //定义一个变量
        public static int num = 0;

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                try {
                    //可重入锁指可以反复获得的锁
                    lock.lock();
                    lock.lock();
                    num++;
                } finally {
                    lock.unlock();
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Subthread t1 = new Subthread();
        Subthread t2 = new Subthread();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Subthread.num);
    }
}
