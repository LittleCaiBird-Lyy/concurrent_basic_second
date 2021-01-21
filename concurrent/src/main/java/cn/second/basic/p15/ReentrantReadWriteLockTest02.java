package cn.second.basic.p15;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock写锁互斥，只能有一个线程持有 */
public class ReentrantReadWriteLockTest02 {
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
    }

    public static void main(String[] args) {
        Service service = new Service();

        //创建线程调用read方法
        for (int i = 0; i <=5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    service.write();
                }
            }).start();
        }

        //同一时间只有一个线程获得写锁，写写互斥
    }
}
