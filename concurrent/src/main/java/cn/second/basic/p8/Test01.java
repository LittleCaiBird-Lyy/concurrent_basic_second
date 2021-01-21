package cn.second.basic.p8;

/**
 * synchronized同步代码块
 * this锁对象
 */
public class Test01 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建test01对象，通过对象调用mm()方法
        Test01 obj = new Test01();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj对象
                obj.mm();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj对象
                obj.mm();
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
}
