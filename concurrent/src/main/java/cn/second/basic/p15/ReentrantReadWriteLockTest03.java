package cn.second.basic.p15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock读写互斥：
 *      一个线程获得读锁时，写线程等待
 *      一个线程获得写锁时，其他线程等待
 */
public class ReentrantReadWriteLockTest03 {
    static class Service{
        //定义读写锁
        ReadWriteLock writeLock = new ReentrantReadWriteLock();
        //定义方法写数据
        public void write(){
            try {
                writeLock.writeLock().lock();
                System.out.println(Thread.currentThread().getName() +" 获得写锁,修改数据的时间 " + System.currentTimeMillis());
                //模拟读取数据用时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放写锁
                System.out.println(Thread.currentThread().getName() +" 写完时间 " + System.currentTimeMillis());
                writeLock.writeLock().unlock();
            }
        }

        public void read(){
            try {
                writeLock.readLock().lock();
                System.out.println(Thread.currentThread().getName() +" 获得du锁,du数据的时间 " + System.currentTimeMillis());
                //模拟读取数据用时
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放写锁
                System.out.println(Thread.currentThread().getName() +" du完时间 " + System.currentTimeMillis());
                writeLock.readLock().unlock();
            }
        }
    }

    public static void main(String[] args) {
        Service service = new Service();

       //定义一个线程读数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.read();
            }
        }).start();
        //定义一个线程写数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                service.write();
            }
        }).start();

        //同一时间只有一个线程获得写锁，写写互斥
    }
}
