package cn.second.basic.p8;

/**
 * synchronized同步静态方法
 *      把整个方法体作为同步代码块
 *      默认的锁对象是当前类的运行时类对象Test05.class,有人称它为类锁
 *
 */
public class Test05 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建test01对象，通过对象调用mm()方法
        Test05 obj = new Test05();

        //一个线程调用Mm方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Test05.class
                obj.m1();
            }
        }).start();

        //一个线程调用mm22方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Test05.class
               Test05.sm2();
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void m1(){
        //经常使用当前类的运行时类对象作为锁对象，可以简单的理解为把当前类的字节码文件作为锁对象
        synchronized(Test05.class) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    //使用synchronized修饰实例方法，同步静态方法，默认当前运行时类Test05.class作为锁对象
    public synchronized  static void sm2(){
        //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
    }
}
