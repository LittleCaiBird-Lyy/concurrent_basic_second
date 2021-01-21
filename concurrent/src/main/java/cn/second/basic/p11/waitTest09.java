package cn.second.basic.p11;

/**
 * notify()通知过早，就让线程等待了
 */
public class waitTest09 {
    //定义静态变量作为是否第一个运行的标志
    static boolean isFirst = true;
    public static void main(String[] args) {
        //定义锁对象
        final Object lock = new Object();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    //当线程是第一个开启的线程就等待
                    while (isFirst){
                        try {
                            System.out.println("begin wait");
                            lock.wait();
                            System.out.println("wait end ...");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                        System.out.println("begin notify");
                        lock.notify();
                        System.out.println("notify end ...");
                        isFirst = false;
                }
            }
        });
        //如果先开启ti,再开启t2线程，大多数情况下，t1先等待，t2再把t1唤醒
        //t1.start();
        //t2.start();

        //如果先开启t2通知线程，再开启t1等待线程，可能会出现t1线程等待没有收到通知的情况
        t2.start();
        t1.start();

        //实际上，调用start()就是告诉线程调度器，当前线程准备就绪，线程调度器在什么时候开启这个
        //线程不确定，即调用start()方法的顺序，并不一定就是线程实际开启的顺序
        //当前实例中，t1等待后让t2线程唤醒，如果t2线程先唤醒了，就不让t1线程等待了


    }
}
