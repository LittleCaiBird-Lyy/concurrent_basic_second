package cn.second.basic.p8;

/**
 *同步过程中出现异常、会自动释放锁对象
 */
public class Test08 {
    public static void main(String[] args) {
        //创建两个线程，分别调用mm()方法
        //先创建test01对象，通过对象调用mm()方法
        Test08 obj = new Test08();

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
               Test08.sm2();
            }
        }).start();

    }

    //定义方法，打印100行字符串
    public void m1(){
        //经常使用当前类的运行时类对象作为锁对象，可以简单的理解为把当前类的字节码文件作为锁对象
        synchronized(Test08.class) {
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
                if (i == 50){
                    //把字符串转换为Int类型时，如果字符串不符合数字格式会产生异常
                    Integer.parseInt("abc");
                }
            }
        }
    }

    //使用synchronized修饰实例方法，同步静态方法，默认当前运行时类Test05.class作为锁对象
    public   static void sm2(){
        synchronized(Test08.class) {
            //经常使用this当前对象作为锁对象
            for (int i = 1; i <= 100; i++) {
                System.out.println(Thread.currentThread().getName() + "-->" + i);
            }
        }
    }
}
