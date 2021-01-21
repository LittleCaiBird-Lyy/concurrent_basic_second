package cn.second.basic.p8;

/**
 * 死锁
 *      在多线程程序中，同步时可能需要多个锁，如果获得锁的顺序不一致，可能会导致死锁
 *      获得锁的顺序一样可以避免死锁
 */
public class Test09 {
    public static void main(String[] args) {
        SubThread t1 = new SubThread();
        t1.setName("a");
        t1.start();

        SubThread t2 = new SubThread();
        t2.setName("b");
        t2.start();

    }


    static class SubThread extends Thread{
        private static final  Object lock1 = new Object();
        private static final  Object lock2 = new Object();

        @Override
        public void run() {
            if ("a".equals(Thread.currentThread().getName())){
                synchronized(lock1){
                    System.out.println("a线程获得了Lock1锁，还需要获得lock2");
                    synchronized(lock2){
                        System.out.println("a线程获得了lock1锁后又获得了Lock2，可以想做任何事");
                    }
                }
            }
            if ("b".equals(Thread.currentThread().getName())){
                synchronized(lock2){
                    System.out.println("a线程获得了Lock2锁，还需要获得lock1");
                    synchronized(lock1){
                        System.out.println("a线程获得了lock2锁后又获得了Lock1，可以想做任何事");
                    }
                }
            }
        }
    }
}
