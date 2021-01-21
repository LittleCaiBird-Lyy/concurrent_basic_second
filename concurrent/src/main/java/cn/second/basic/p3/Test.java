package cn.second.basic.p3;

/**
 * 测试实现Runnable接口的形式创建线程
 */
public class Test {
    public static void main(String[] args) {
        //3.创建Runnble接口的实现类对象
        MyRunnable runnable = new MyRunnable();
        //4.创建线程对象
        Runnable target;
        Thread thread = new Thread(runnable);
        //5.开启线程
        thread.start();

        //当前是Main线程
        for (int i = 0; i <=100; i++) {
            System.out.println("main thread == >" + i);
        }

        //有时调用Thread(Runnble)构造方法时，实参也会传递匿名内部类对象
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <=100; i++) {
                    System.out.println("sub2 thread ..... >" + i);
                }
            }
        });
        thread2.start();
    }
}
