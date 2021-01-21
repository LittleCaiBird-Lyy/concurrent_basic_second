package cn.second.basic.p13;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * LockInterruptibly() 方法的作用：如果当前线程未被中断则获得锁，如果当前线程被中断则出点异常
 */
public class ReentrantLockTest05 {
    static class Servier{
        //定义锁对象
        private Lock lock = new ReentrantLock();
        public void serviceMethod(){
            try {
                //获得锁定，即使调用了线程的interrupt()方法也没有真正中断这个线程
                //lock.lock();

                //如果线程被中断了不会获得锁，会产生异常
                lock.lockInterruptibly();
                System.out.println(Thread.currentThread().getName() + "-- begin lock");
                //执行一段耗时的操作
                for (int i = 0; i < Integer.MAX_VALUE; i++) {
                    new StringBuffer();
                }
                System.out.println(Thread.currentThread().getName() + "-- end lock ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + " ====== 释放锁");
                lock.unlock();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Servier s = new Servier();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                s.serviceMethod();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();

        Thread.sleep(50);
        Thread t2 = new Thread(r);
        t2.start();
        Thread.sleep(50);
        //中断t2线程
        t2.interrupt();
    }
}
