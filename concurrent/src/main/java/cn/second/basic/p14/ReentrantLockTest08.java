package cn.second.basic.p14;

import java.util.concurrent.locks.ReentrantLock;

/**
 * boolean isLocked() 判断当前锁是否被线程持有
 */
public class ReentrantLockTest08 {

    static ReentrantLock lock = new ReentrantLock();

    public static void sm(){
        try {
            System.out.println("before lock -- " + lock.isLocked());
            lock.lock();
            System.out.println("after lock -- " + lock.isLocked());
            Thread.sleep(2000);
           //System.out.println(Thread.currentThread().getName() +" wait ....");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock.isHeldByCurrentThread())
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("11 -- " + lock.isLocked());

        new Thread(new Runnable() {
            @Override
            public void run() {
                sm();
            }
        }).start();

        //确保子线程执行结束
        Thread.sleep(3000);
        System.out.println("222==="  + lock.isLocked());
    }
}
