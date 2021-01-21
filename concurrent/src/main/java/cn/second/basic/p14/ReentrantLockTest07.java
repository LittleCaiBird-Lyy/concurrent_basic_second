package cn.second.basic.p14;


import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**boolean isFair()                 判断是否为公平锁
 * boolean isHeldByCurrentThread()  判断当前锁是否被当前线程持有
 */
public class ReentrantLockTest07 {

    static class Service{
        private ReentrantLock lock;

        //通过构造方法接受一个布尔值，确定当前锁是否公平
        public Service(boolean isFair) {
            this.lock = new ReentrantLock(isFair);
        }

        public void serviceMethod(){
            try {
                System.out.println("是否公平锁？" + lock.isFair() + "---" +
                        Thread.currentThread().getName() + "调用Lock前是否持有锁" + lock.isHeldByCurrentThread());
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "调用LocK方法后是否持有锁" + lock.isHeldByCurrentThread());
            }finally {
                //如果锁对象被当前线程持有就释放锁
                if (lock.isHeldByCurrentThread())
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt();
                new Service(num % 2 == 0 ? true:false).serviceMethod();
            }
        };
        for (int i = 0; i < 3; i++) {
            new Thread(runnable,"thread-" + i).start();
        }
    }

}
