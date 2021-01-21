package cn.second.basic.p9;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用原子类进行自增
 */
public class Test04 {
    public static void main(String[] args) {
        //在main中创建10个子线程
        for (int i = 0; i < 1000; i++) {
            new MyThread().start();
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(MyThread.count.get());

    }

    static class MyThread extends Thread{
        //使用AtomicInteger对象
        private static AtomicInteger count = new AtomicInteger();

        public  synchronized static void addCount(){
            for (int i = 0; i < 1000; i++) {
                //自增的后缀形式
                count.getAndIncrement();
            }
            System.out.println(Thread.currentThread().getName() + "count=" + count.get());
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
