package cn.second.basic.p15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 *ReadWriteLock读写锁可以实现读读共享，允许多个线程同时获得读锁
 */
public class ReentrantReadWriteLockTest01 {
    static class Service{
        //定义读写锁
        ReadWriteLock writeLock = new ReentrantReadWriteLock();
        //定义方法读取数据
        public void read(){
            try {
                writeLock.readLock().lock();
                System.out.println(Thread.currentThread().getName() +" 获得读锁,读取数据的时间 " + System.currentTimeMillis());
                //模拟读取数据用时
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                //释放读锁
                writeLock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

        //创建线程调用read方法
        for (int i = 0; i <=5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.read();
                }
            }).start();
        }

        //运行程序后几个线程几乎同时获得读锁获得Lock后面的代码
    }
}
