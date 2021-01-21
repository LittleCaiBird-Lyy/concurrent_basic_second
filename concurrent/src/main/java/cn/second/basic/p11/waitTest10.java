package cn.second.basic.p11;

import java.util.ArrayList;
import java.util.List;

/**
 * wait条件发生变化
 * 定义一个集合：
 * 定义一个线程向集合中添加数据，添加完数据后，通知另外的线程从集合中取数据
 * 定义一个线程从集合中取数据，如果集合中没有数据就等待
 */
public class waitTest10 {
    public static void main(String[] args) {
        //定义添加数据的线程对象
        ThreadAdd  threadAdd = new ThreadAdd();
        //定义取数据的线程对象
        ThreadSubstract threadSubstract = new ThreadSubstract();
        threadSubstract.setName("subtract 1 ");

        //测试1：先开启添加数据的线程，再开启一个取数据的线程，大多数情况下会正常取数据
        /*threadAdd.start();
        threadSubstract.start();*/

        //测试2：先开启取数据的线程，再开启添加数据的线程,取数据的线程会先等待，等到添加数据后，再取数据
        /*threadSubstract.start();
        threadAdd.start();*/

        //测试三：开启两个取数据的线程，再开启添加数据的线程
        ThreadSubstract threadSubstract2 = new ThreadSubstract();
        threadSubstract2.setName(" substract 2 ");
        threadSubstract.start();
        threadSubstract2.start();
        threadAdd.start();
        /**
         * 某次执行结果如下：
         *      subtract 1 begin wait ...
         *      substract 2 从集合中取了data后，集合中数据的数量：0
         *      subtract 1  end wait ..
         *      Exception in thread "subtract 1 " java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
         * 分析可能执行的顺序：
         *      threadSubstract线程先启动，取数据，集合中没有数据，wait()等待
         *      threadAdd获得CPU执行权，添加数据，把threadStract线程唤醒，
         *      结果threadStract2线程开启后获得cpu执行权，正常取数据
         *      threadStrac获得cpu执行权，打印 end wait ....,然后再执行list,remove(0)取数据时，
         *      现在List集合中已经没有数据了，再调用remove方法会产生异常
         *  出现异常的原因时：向List集合中添加了一个数据，取了两次
         *  如何解决？
         *      当等待的线程被唤醒后，再判断一次集合中是否有数据可取，即需要把substract()方法中的if判断改为while
         */


    }

    //1.定义List集合
    static List list = new ArrayList();

    //2.定义方法从集合中取数据
    public static void subtract(){
        synchronized (list){
            //if (list.size() == 0){
            while (list.size() == 0){
                try {
                    System.out.println(Thread.currentThread().getName() + "begin wait ...");
                    list.wait();
                    System.out.println(Thread.currentThread().getName() + " end wait ..");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从集合中取出一个数据
            Object data = list.remove(0);
            System.out.println(Thread.currentThread().getName()
                    + "从集合中取了" + data + "后，集合中数据的数量：" + list.size());
        }
    }

    //3.定义方法向集合中添加数据,通知等待的线程取数据
    public static void add(){
        synchronized (list){
            list.add("data");
            System.out.println(Thread.currentThread().getName() + "存储了一个数据");
            list.notifyAll();
        }

    }

    //4.定义线程类调用substract()取数据的方法
    static class ThreadAdd extends Thread{
        @Override
        public void run() {

            add();
        }
    }

    //5.定义线程类调用substract()方法
    static class ThreadSubstract extends Thread{
        @Override
        public void run() {
            subtract();
        }
    }
}
