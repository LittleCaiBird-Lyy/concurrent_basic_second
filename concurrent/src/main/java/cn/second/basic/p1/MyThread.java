package cn.second.basic.p1;

/**
 * 1.定义一个类继承Thread
 *
 */
public class MyThread extends Thread{
    //2.重新run方法

    @Override
    public void run() {
        System.out.println("只是子线程打印的内容");
    }
}
