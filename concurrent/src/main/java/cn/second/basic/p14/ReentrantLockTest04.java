package cn.second.basic.p14;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * int getWaitQueueLength(Condition condition) 返回在Condition条件上等待的线程预估数
 */
public class ReentrantLockTest04 {

    static class Service{
        private ReentrantLock lock = new ReentrantLock();
        //返回锁给定义的Condition
        private Condition condition = lock.newCondition();

        public void waitMethod(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() +
                        "进入等待前，现在该condition条件上的线程预估数 " + lock.getWaitQueueLength(condition));
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                lock.unlock();
            }
        }
        public void notifyMethod(){
            try {
                lock.lock();
                condition.signalAll();
                System.out.println("唤醒所有等待后 ，现在该condition条件上的线程预估数"+
                        lock.getWaitQueueLength(condition));
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                service.waitMethod();
            }
        };

        //创建10个线程调用waitMethod()
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }

        Thread.sleep(1000);

        //唤醒所有等待
        service.notifyMethod();
    }
}
