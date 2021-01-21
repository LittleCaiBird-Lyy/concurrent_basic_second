package cn.second.basic.p13;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *通过ReentrantLock的lockInterruptibly()这个方法可以避免死锁的产生
 */
public class ReentrantLockTest06 {
    static class inlock implements Runnable{
        //创建两个锁对象
        public static ReentrantLock lock1 = new ReentrantLock();
        public static ReentrantLock lock2 = new ReentrantLock();
        //定义整数变量，决定使用哪个锁
        int lockNum;

        public inlock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            try {//锁的顺序不对会死锁
                if (lockNum % 2 == 1){
                    //奇数-->先锁一再锁2
                    //lock1.lock();
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁一，还需要获得锁二");
                    Thread.sleep(new Random().nextInt(500));
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁一与获得锁二");
                }else {
                    //偶数-->先锁2再锁1
                    lock2.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁2，还需要获得锁1");
                    Thread.sleep(new Random().nextInt(500));
                    lock1.lockInterruptibly();
                    System.out.println(Thread.currentThread().getName() + "获得锁一与获得锁二");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //判断当前线程是否持有该锁
                if (lock1.isHeldByCurrentThread()){
                    lock1.unlock();
                }
                if (lock2.isHeldByCurrentThread()){
                    lock2.unlock();
                }
                System.out.println(Thread.currentThread().getName() + "线程退出");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        inlock inlock1 = new inlock(11);
        inlock inlock2 = new inlock(22);

        Thread t1 = new Thread(inlock1);
        Thread t2 = new Thread(inlock2);
        t1.start();
        t2.start();

    //在main线程中，等待3000秒，如果线程没有结束就中断该线程
        Thread.sleep(3000);

        //可以中断任何一个线程来解决死锁，t2线程会放弃对锁1的申请，同时释放锁2-->先拿到的锁2，t1线程会完成他的任务
        if (t2.isAlive()){t2.interrupt();}
    }
}
