package cn.second.basic.p14;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用condition实现生产者/消费者设计模式，多对多，即有多个线程打印---，有多个线程打印***，实现交替打印
 */
public class ConditionTest04 {
    static class MyService{
        //创建锁对象
        private Lock lock = new ReentrantLock();
        //创建线程对象
        private Condition condition = lock.newCondition();
        //定义交替打印方式
        private boolean flag = true;

        //定义方法只打印-----横线
        public void printOne(){
            try {
                //锁定
                lock.lock();
                while (flag){
                    //当flag为true等待
                    System.out.println(Thread.currentThread().getName() + "waiting ...");
                    condition.await();
                }
                //flag为false打印
                System.out.println(Thread.currentThread().getName() + "-------");
                //修改交替打印标志
                flag = true;
                //通知另外的线程打印
                //signal();多对多会产生假死，所以要变成signalAll();
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁对象
                lock.unlock();
            }
        }

        //定义方法只打印*********横线
        public void printTwo(){
            try {
                //锁定
                lock.lock();
                while (!flag){
                    //当flag为false等待
                    System.out.println(Thread.currentThread().getName() + "waiting ...");
                    condition.await();
                }
                //flag为true打印
                System.out.println(Thread.currentThread().getName() + "**********");
                //修改交替打印标志
                flag = false;
                //通知另外的线程打印
                condition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //释放锁对象
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        MyService myService = new MyService();
        //创建线程打印--
        for ( int i = 0 ; i < 10 ; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printOne();
                    }
                }
            }).start();

            //创建线程打印--
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 100; i++) {
                        myService.printTwo();
                    }
                }
            }).start();
        }
    }
}
