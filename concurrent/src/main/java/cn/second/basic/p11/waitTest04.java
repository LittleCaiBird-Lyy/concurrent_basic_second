package cn.second.basic.p11;

import java.util.ArrayList;
import java.util.List;

/**
 * notify()不会立即释放锁对象
 */
public class waitTest04 {

    public static void main(String[] args) throws InterruptedException {
        //定义一个List集合存储String数据
        List<String> list = new ArrayList<String>();

        //定义第一个线程，当List集合中元素的数量不等于5时线程等待
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list){
                    if (list.size() != 5){
                        System.out.println("线程1开始等待 ：" + System.currentTimeMillis());
                        try {
                            list.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程1被唤醒" + System.currentTimeMillis());
                    }
                }
            }
        });

        //定义第二个线程，向list集合中添加元素
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (list){
                    for (int i = 0; i < 10; i++) {
                        list.add("data --" + i);
                        //判断元素的数量是否满足唤醒线程1
                        if (list.size() == 5){
                            //不会立即释放锁对象，只有在当前代码块全部执行后才会释放1的锁对象
                            list.notify();
                            System.out.println("线程2已经发出唤醒通知");
                        }
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("线程2添加了第" + i+ "个数据");
                    }
                }
            }
        });
        t1.start();
        Thread.sleep(2000);
        t2.start();

    }
}
