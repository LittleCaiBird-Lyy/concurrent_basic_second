package cn.second.basic.p11;

/**
 * notity()与notityAoo()
 */
public class waitTest06 {
    public static void main(String[] args) throws InterruptedException {
        //定义一个对象为子线程的锁对象
        Object lock = new Object();
        SubThread t1 = new SubThread(lock);
        SubThread t2 = new SubThread(lock);
        SubThread t3 = new SubThread(lock);
        t1.setName("t1");
        t2.setName("t2");
        t3.setName("t3");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(2000);
        //调用notify()唤醒子线程,调用一次只能唤醒其中的一个线程
        synchronized (lock){
            //lock.notify();
            lock.notifyAll();
        }
    }

    static class SubThread extends Thread{
        //定义实例变量作为锁对象
        private Object lock;

        public SubThread(Object lock){
            this.lock = lock;
        }
        @Override
        public void run() {
            synchronized (lock){
                try {
                    System.out.println(Thread.currentThread().getName() +" -- begin wait ..");
                    lock.wait();
                    System.out.println(Thread.currentThread().getName() + "-- end wait ...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
