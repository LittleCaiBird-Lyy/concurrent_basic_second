package cn.second.basic.p13;

import java.util.concurrent.locks.ReentrantLock;

/**
 * tryLock()
 *      当锁对象没有被其他线程持有的情况下，才会获得该锁定
 */
public class ReentrantLockTest08 {

    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        public void serviceMethod(){
            try {
                if (lock.tryLock()){
                    System.out.println(Thread.currentThread().getName() + "获得锁定");
                    //模拟执行任务的时长
                    Thread.sleep(3000);
                }else {
                    System.out.println(Thread.currentThread().getName() + "没有获得锁定");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                service.serviceMethod();
            }
        };
        Thread t1 = new Thread(r);
        t1.start();
        //睡眠50毫秒,确保t1线程锁定
        Thread.sleep(50);
        Thread t2 = new Thread(r);
        t2.start();
    }
}
