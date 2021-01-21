package cn.second.basic.p10;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 *AtomicStampedReference原子类可以解决CAS中的ABA问题
 * AtomicStampedReference原子类中有一个整数标记值stamp，
 * 每次执行CAS操作时，需要对比它的版本，即比较stamp的值
 */
public class atomicReferenceTest03 {
    private static AtomicReference<String> atomicReference
            = new AtomicReference<>("abc");
    //定义AtomicStampedReference引用操作："abc"字符串，指定初始化版本号为0
    private static AtomicStampedReference stampedReference =
            new AtomicStampedReference("abc",0);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                stampedReference.compareAndSet("abc","def",stampedReference.getStamp(),stampedReference.getStamp()+1);
                System.out.println(Thread.currentThread().getName() + "--" + stampedReference.getReference());
                stampedReference.compareAndSet("def","abc",stampedReference.getStamp(),stampedReference.getStamp()+1);

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                //获得版本号
                //int stamp = stampedReference.getStamp(); -->false 睡之前和之后的版本号不一致
                try {
                    TimeUnit.SECONDS.sleep(1);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int stamp = stampedReference.getStamp();//true
                System.out.println(stampedReference.compareAndSet("abc","ggg",stamp,stamp+1));
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(stampedReference.getReference());
    }
}
