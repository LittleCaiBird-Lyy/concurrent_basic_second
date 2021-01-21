package cn.second.basic.p16;

/**
 * 创建线程组
 */
public class TreadGroupTest01 {
    public static void main(String[] args) {
        //1.返回当前main线程的线程组
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        System.out.println(threadGroup);

        //2.定义线程组，如果不指定所属线程组，则自动归属到当前所属的线程组中
        ThreadGroup group1 = new ThreadGroup("group1");
        System.out.println(group1);

        //3.定义线程组，同时指定父线程组
        ThreadGroup group2 = new ThreadGroup("group2");
        //group1,group2的父线程组是main
        System.out.println(group1.getParent() == threadGroup);
        System.out.println(group2.getParent() == threadGroup);

        //4.在创建线程时指定所属的线程组
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread());
            }
        };
        //在创建线程时，如果没有指定线程组，则默认到父线程的线程组中
        //在main线程中创造了t1线程，其父线程为main   Thread[t1,5,main]
        Thread t1 = new Thread(runnable,"t1");
        System.out.println(t1);

        //指定线程组所属
        //Thread[t2,5,group1]
        Thread t2  = new Thread(group1 , runnable, "t2");
        //Thread[t3,5,group2]
        Thread t3  = new Thread(group2 , runnable, "t3");
        System.out.println(t2);
        System.out.println(t3);
    }
}
