package cn.second.basic.p14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Condition等待与通知
 */
public class ConditionTest01 {


    //定义锁
    static Lock lock = new ReentrantLock();
    //获得Condition对象
    static Condition condition = lock.newCondition();

    //定义线程子类
    static class SubThread extends Thread{
        @Override
        public void run() {
            try {
                //在调用await()方法前必须获得锁
                lock.lock();
                System.out.println("method lock");
                //等待
                condition.await();
                System.out.println("method await");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁
                lock.unlock();
                System.out.println("method unlock");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SubThread t = new SubThread();
        t.start();
        //子线程启动后会转入等待状态
        Thread.sleep(3000);
        try {
            lock.lock();
            condition.signal();
        } finally {
            lock.unlock();
        }
    }
}
