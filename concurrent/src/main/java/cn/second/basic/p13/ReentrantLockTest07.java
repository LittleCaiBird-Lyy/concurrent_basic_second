package cn.second.basic.p13;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock的基本使用
 */
public class ReentrantLockTest07 {
    static class TimeLock implements Runnable{
        //定义锁对象
        private static ReentrantLock lock = new ReentrantLock();

        @Override
        public void run() {
            try {
                if (lock.tryLock(3, TimeUnit.SECONDS)){
                    System.out.println(Thread.currentThread().getName() + "获得锁，执行耗时任务");
                    //假设第一个线程持有锁完成任务需要4秒，第二个线程在三秒钟没获得锁会放弃
                    Thread.sleep(4000);
                }else { //没有获得锁
                    System.out.println(Thread.currentThread().getName() + "没有获得锁");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        TimeLock timeLock = new TimeLock();
        Thread t1 = new Thread(timeLock);
        Thread t2 = new Thread(timeLock);
        t1.start();
        t2.start();
    }
}
