package cn.second.basic.p13;

/**
 * 演示锁的可重入性
 */
public class ReentrantLockTest01 {
    public synchronized void sm1(){
        //线程执行sm1默认this作为锁对象，在sm1()方法中调用了sm2()方法，注意当前线程还是持有this对象的
        //sm2()同步方法默认的锁对象也是this,要执行sm2()必须先获得this锁对象，当前this锁对象被当前线程持有
        //可以再次获得，this锁对象，这就是锁的可重用性
        //假设锁不可重用，容易造成死锁
        System.out.println("同步方法一");
        sm2();
    }

    private synchronized void sm2() {
        System.out.println("同步方法二");
        sm3();
    }

    private synchronized void sm3() {
        System.out.println("同步方法三");
    }

    public static void main(String[] args) {
        ReentrantLockTest01 obj = new ReentrantLockTest01();
        new Thread(new Runnable() {
            @Override
            public void run() {
              obj.sm1();
            }
        }).start();
    }
}
