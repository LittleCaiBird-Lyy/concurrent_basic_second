package cn.second.basic.p13;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import org.w3c.dom.ls.LSOutput;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用Lock锁同步不同方法的同步代码块
 */
public class  ReentrantLockTest03 {
    //定义锁对象
    static Lock lock = new ReentrantLock();
    public static void sm1(){
        //Lock锁获得后经常在try获得锁finally子句中释放锁
        try {
            //获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "-- method --" + System.currentTimeMillis());
                Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "-- method --" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void sm2(){
        //Lock锁获得后经常在try获得锁finally子句中释放锁
        try {
            //获得锁
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "-- method2 --" + System.currentTimeMillis());
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "-- method2 --" + System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                sm1();
            }
        };
        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                sm2();
            }
        };

        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r1).start();
        new Thread(r2).start();
        new Thread(r2).start();
        new Thread(r2).start();
    }
}
