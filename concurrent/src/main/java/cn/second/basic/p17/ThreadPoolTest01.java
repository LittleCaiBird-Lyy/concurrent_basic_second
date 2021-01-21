package cn.second.basic.p17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的基本使用
 */
public class ThreadPoolTest01 {
    public static void main(String[] args) {
        //创建有5个线程大小的线程池
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        //想线程池中提交18个任务，这18个任务存在线程池的阻塞队列中，线程池中这五个线程就从阻塞对象中取任务执行
        for (int i = 0; i < 18; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId()
                            + "编号的任务在执行，开始时间：" + System.currentTimeMillis());
                    try {
                        //模拟任务执行时长
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
