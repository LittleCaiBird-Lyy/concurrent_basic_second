package cn.second.basic.p17;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 自定义线程工厂
 */
public class ThreadPoolTest04 {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(10);
                System.out.println(Thread.currentThread().getId()
                        + "--" + System.currentTimeMillis()+"开始睡眠" + num + "秒");
                try {
                    //模拟任务执行时长
                    TimeUnit.SECONDS.sleep(num);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //创建线程池，使用自定义线程工厂,采用默认的拒绝策略
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                //根据参数r创建任务，需要创建一个线程
                Thread t = new Thread(r);
                //设置为守护线程，当主线程运行结束，线程池中的线程会自动释放
                t.setDaemon(true);
                System.out.println("创建了线程：" + t);
                return t;
            }
        });
        //提交五个任务，当给当前线程池提交的任务超过5个时，线程池默认抛出异常
        for (int i = 0; i < 5; i++) {
            executorService.submit(r);
        }

        //主线程睡眠10秒，主线程睡眠时，主线程结束，线程池中的线程会自动退出
        /*try {
            //模拟任务执行时长
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }
}
