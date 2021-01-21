package cn.second.basic.p8;

/**
 * synchronized同步代码块
 * 使用常量对象作为锁对象
 * 不管是静态方法还是非静态方法，只要是一个锁对象就可以同步
 */
public class Test03 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建test01对象，通过对象调用mm()方法
        Test03 obj = new Test03();
        Test03 obj2 = new Test03();
        Test03 obj3 = new Test03();


        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj常量
                obj.mm();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj2常量
                obj2.mm();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                //使用锁对象this就是Obj2常量
                obj3.sm();
            }
        }).start();

    }

    public static final Object OBJ = new Object();

    //定义方法，打印100行字符串
    public void mm(){
        //经常使用常量对象作为锁对象
        synchronized(OBJ) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }

    public static void sm(){
        //经常使用常量对象作为锁对象
        synchronized(OBJ) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }
}
