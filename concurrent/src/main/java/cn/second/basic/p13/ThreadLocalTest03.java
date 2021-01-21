package cn.second.basic.p13;

import java.util.Date;
import java.util.Random;

/**
 * ThreadLocal初始值问题,定义ThreadLocal类的子类，在子类中重写initialValue方法指定初始值
 */
public class ThreadLocalTest03 {

    ///1.定义ThreadLocal的子类
    static class SubThreadLocal extends ThreadLocal<Date>{
        //重写initialValue，设置初始值
        @Override
        protected Date initialValue() {
            //当前日期设为初始值
            return new Date();
        }
    }


    //定义ThreadLocal对象
    //static ThreadLocal threadLocal = new ThreadLocal();
    static SubThreadLocal threadLocal = new SubThreadLocal();
    //定义线程类
    static class SubThread extends Thread{
        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                //第一次调用ThreadLocal的get()方法会返回null
                System.out.println("------" +
                        Thread.currentThread().getName() + "value=" + threadLocal.get());
                //如果没有初始值，就设置当前日期
                if (threadLocal.get() == null){
                    System.out.println("**************");
                    threadLocal.set(new Date());
                }
                try {
                    Thread.sleep(new Random().nextInt(500));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public static void main(String[] args) {
            SubThread t1 = new SubThread();
            t1.start();
            SubThread t2 = new SubThread();
            t2.start();
        }
    }

}
