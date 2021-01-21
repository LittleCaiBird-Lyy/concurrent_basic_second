package cn.second.basic.p17;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 监控线程池
 */
public class ThreadPoolTest05 {
    public static void main(String[] args) {
        //先定义任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getId()
                        + "-开始执行-" + System.currentTimeMillis());
                try {
                    //模拟任务执行时长10秒
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        //定义线程池
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(2,5 ,0 ,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());

        //向线程池提交30个任务
        for (int i = 0; i < 30; i++) {
            poolExecutor.submit(runnable);
            System.out.println("当前线程值核心线程数 ： " + poolExecutor.getCorePoolSize() + ",最大线程数 ： "  +
                    poolExecutor.getMaximumPoolSize() + " ，当前线程池大小 " + poolExecutor.getPoolSize() + " , 活动线程 ： " + poolExecutor.getActiveCount() +
                    " , 收到任务数 ：" + poolExecutor.getTaskCount() + ", 完成任务数 ： " + poolExecutor.getCompletedTaskCount() + " , 等待任务数： " +
                    poolExecutor.getQueue().size());
            try {
                //每隔500毫秒提交一次任务
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("==================");
        while (poolExecutor.getActiveCount() > 0){
            System.out.println("当前线程值核心线程数 ： " + poolExecutor.getCorePoolSize() + ",最大线程数 ： "  +
                    poolExecutor.getMaximumPoolSize() + " ，当前线程池大小 " + poolExecutor.getPoolSize() + " , 活动线程 ： " + poolExecutor.getActiveCount() +
                    " , 收到任务数 ：" + poolExecutor.getTaskCount() + ", 完成任务数 ： " + poolExecutor.getCompletedTaskCount() + " , 等待任务数： " +
                    poolExecutor.getQueue().size());
            try {
                //每隔500毫秒提交一次任务
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
