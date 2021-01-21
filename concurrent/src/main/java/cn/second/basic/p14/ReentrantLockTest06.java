package cn.second.basic.p14;


import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 是否有线程在等待指定的以Condition条件
 */
public class ReentrantLockTest06 {

    static ReentrantLock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void sm(){
        try {
            lock.lock();
            System.out.println("是否有线程正等待当前Condition条件？" +
                    lock.hasWaiters(condition) + "----waitqueeulenth "  + lock.getWaitQueueLength(condition));
            System.out.println(Thread.currentThread().getName() +" wait ....");
            //超时后自动唤醒
            condition.await(new Random().nextInt(1000) , TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName()+"超时唤醒，是否有线程正等待当前Condition条件？" +
                    lock.hasWaiters(condition) + "----waitqueeulenth "  + lock.getWaitQueueLength(condition));

            //System.out.println(Thread.currentThread().getName() +"unlock");
            lock.unlock();
        }
    }


    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                sm();
            }
        };
        //开启10个线程都调用sm方法
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

}
