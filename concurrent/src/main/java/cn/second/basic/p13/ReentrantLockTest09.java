package cn.second.basic.p13;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用tryLock()避免死锁
 */
public class ReentrantLockTest09 {
    static class IntLock implements Runnable{
        private static ReentrantLock lock1 = new ReentrantLock();
        private static ReentrantLock lock2 = new ReentrantLock();
        //用于控制锁的顺序
        private int lockNum;

        public IntLock(int lockNum) {
            this.lockNum = lockNum;
        }

        @Override
        public void run() {
            if (lockNum % 2 == 0){
                //偶数，锁先一后二
                try {
                    while (true){
                        if (lock1.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "获得锁一还想获得锁二");
                            Thread.sleep(new Random().nextInt(10));
                            try {
                                if (lock2.tryLock()){
                                    System.out.println(Thread.currentThread().getName() + "获得锁一与获得锁二---");
                                    //结束run方法执行，当前线程结束
                                    return;
                                }
                            } finally {
                                if (lock2.isHeldByCurrentThread())
                                lock2.unlock();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock1.isHeldByCurrentThread()){
                        lock1.unlock();
                    }
                }
            }else {
                //奇数，锁先二后一
                try {
                    while (true){
                        if (lock2.tryLock()){
                            System.out.println(Thread.currentThread().getName() + "获得2还想获得锁1");
                            Thread.sleep(new Random().nextInt(10));
                            try {
                                if (lock1.tryLock()){
                                    System.out.println(Thread.currentThread().getName() + "获得锁一与获得锁二---");
                                    //结束run方法执行，当前线程结束
                                    return;
                                }
                            } finally {
                                if (lock1.isHeldByCurrentThread())
                                lock1.unlock();
                            }
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (lock2.isHeldByCurrentThread()){
                        lock2.unlock();
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        IntLock intLock1 = new IntLock(11);
        IntLock intLock2 = new IntLock(22);

        Thread t1 = new Thread(intLock1);
        Thread t2 = new Thread(intLock2);

        t1.start();
        t2.start();
        //运行后，使用tryLock()尝试获得锁，不会傻傻的等待，
        // 通过循环不停的再次尝试，如果等待的时间足够长，线程总是获得想要的资源
    }
}
