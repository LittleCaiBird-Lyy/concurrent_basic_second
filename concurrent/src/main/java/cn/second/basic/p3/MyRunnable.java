package cn.second.basic.p3;

/**
 * 当前线程已经有父类了，就不能用继承Thread类的形式创建线程，可以用实现Runnable接口的形式
 * 1.定义一个类实现Runnble接口
 */
public class MyRunnable implements Runnable{
    //2.重写Runnble接口中的抽象方法run方法，run()方法就是子线程要执行的代码
    @Override
    public void run() {
        for (int i = 0; i <=100; i++) {
            System.out.println("sub thread -- >" + i);
        }
    }
}
