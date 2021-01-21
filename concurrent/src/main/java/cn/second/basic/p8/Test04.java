package cn.second.basic.p8;

/**
 * synchronized同步代码块
 *      把整个方法体作为同步代码块
 *      默认的锁对象是this对象
 *
 */
public class Test04 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建test01对象，通过对象调用mm()方法
        Test04 obj = new Test04();

        //一个线程调用Mm方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj对象
                obj.mm();
            }
        }).start();

        //一个线程调用mm22方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj对象
                obj.mm22();
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void mm(){
        //经常使用this当前对象作为锁对象
        synchronized(this) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    //使用synchronized修饰实例方法，同步实例方法，默认this作为锁对象
    public synchronized void mm22(){
        //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
    }
}
