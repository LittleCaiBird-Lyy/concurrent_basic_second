package cn.second.basic.p11;

/**
 * wait()方法notify()方法放到同步代码块当中，通过锁对象来调用，
 */
public class waitTest02 {
    public static void main(String[] args) {
        try {
            String test = "lyycto";
            String test2 = "aaa";
            System.out.println("同步前的代码块...");
            synchronized(test){
                System.out.println("同步代码块开始...");
                //调用wait方法后，当前线程就会等待，释放锁对象，当前线程需要被唤醒，如果没有唤醒会一直等待
                //test2.wait()--->不是锁对象会产生IllegalMonitorStateException异常
                test.wait();
                System.out.println("wait后面的代码....");
            }
            System.out.println("同步代码块后面的代码");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("后面的其他代码....");
    }
}
