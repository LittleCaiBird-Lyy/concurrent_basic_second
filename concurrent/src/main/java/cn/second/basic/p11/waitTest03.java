package cn.second.basic.p11;

/**
 * 需要通过notify()唤醒等待的线程
 */
public class waitTest03 {
    public static void main(String[] args) throws InterruptedException {
        //定义一个字符串为锁对象
        String lock = "lyycto";
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    System.out.println("线程1开始等待： " + System.currentTimeMillis());
                    //线程等待
                    try {
                        lock.wait();
                        System.out.println("线程1，结束等待 ； " + System.currentTimeMillis());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        //定义第二个线程，唤醒第一个线程
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //notify方法也需要在同步代码块中有锁对象调用
                synchronized (lock){
                    System.out.println("线程2开始唤醒" + System.currentTimeMillis());
                    //唤醒在lock锁对象上等待的某一个线程
                    lock.notify();
                    System.out.println("线程2唤醒结束" + System.currentTimeMillis());
                }

            }
        });
        t1.start();
        Thread.sleep(3000);
        t2.start();
    }
}
