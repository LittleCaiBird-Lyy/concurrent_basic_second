package cn.second.basic.p14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多个Condition实现通知部分线程
 */
public class ConditionTest02 {
    static class Service{
        //定义锁对象
        private ReentrantLock lock = new ReentrantLock();
        //定义两个Condition对象
        private Condition conditionA = lock.newCondition();
        private Condition conditionB = lock.newCondition();

        //定义方法使用conditionA等待
        public void waitMethodA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "begin wait :" + System.currentTimeMillis());
                //等待
                conditionA.await();
                System.out.println(Thread.currentThread().getName() + "end wait :" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void waitMethodB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "begin wait :" + System.currentTimeMillis());
                //等待
                conditionB.await();
                System.out.println(Thread.currentThread().getName() + "end wait :" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        //定义方法唤醒conditionA对象上的等待
        public void signalA(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "singal A time = "  + System.currentTimeMillis());
                conditionA.signal();
                System.out.println(Thread.currentThread().getName() + "singal A time = "  + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }


        public void signalB(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "singal B time = "  + System.currentTimeMillis());
                conditionB.signal();
                System.out.println(Thread.currentThread().getName() + "singal B time = "  + System.currentTimeMillis());
            } finally {
                lock.unlock();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        //开启两个线程，分别调用waitMethodA(),waitMethodB()方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodA();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                service.waitMethodB();
            }
        }).start();

        //main线程睡眠三秒
        Thread.sleep(3000);

        //唤醒conditionA对象上的等待
        //service.signalA();

        service.signalB();

    }
}
