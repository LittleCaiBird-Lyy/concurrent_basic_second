package cn.second.basic.p14;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁与非公平锁
 */
public class ReentrantLockTest01 {
    //默认是非公平锁
    //ReentrantLock(true)定义公平锁
    static ReentrantLock lock = new ReentrantLock(true);

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + "获得了锁对象");
                    } finally {
                        lock.unlock();
                    }
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(runnable).start();
        }
        /**
         * 运行程序
         *      如果是非公平锁，系统倾向与让一个线程再次获得已经持有的锁，这种分配策略是高效但非公平
         *      如果是公平锁，多个线程不会发生同一个线程连续多次获得锁的可能，保证锁的公平性
         */
    }

}
