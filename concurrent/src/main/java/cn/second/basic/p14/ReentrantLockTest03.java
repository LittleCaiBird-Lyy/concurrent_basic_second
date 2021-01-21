package cn.second.basic.p14;

import java.util.concurrent.locks.ReentrantLock;

/**
 * int getQueueLength() 返回等待获得锁的线程预估数
 */
public class ReentrantLockTest03 {

    static ReentrantLock lock = new ReentrantLock();

    public static void sm() {
        try {
            lock.lock();
            System.out.println(Thread.currentThread().getName() +
                    "获得锁，执行方法，估计等待获得锁的线程数 ： " + lock.getQueueLength());
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ReentrantLockTest03.sm();
            }
        };

        //开启10个线程，执行sm()方法
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }


}



