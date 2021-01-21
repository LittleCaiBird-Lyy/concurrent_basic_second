package cn.second.basic.p17;

import java.util.Random;
import java.util.concurrent.*;

/**
 *自定义拒绝策略
 */
public class ThreadPoolTest03 {

    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                int num = new Random().nextInt(5);
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

        //创建线程池,自定义拒绝策略
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5,
                5, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10), Executors.defaultThreadFactory(), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                //r 就是请求的任务，executor当前线程池
                System.out.println(r + " is discarding ...");
            }
        });

        //想线程池提交若干任务
        for (int i = 0; i <Integer.MAX_VALUE ; i++) {
            threadPoolExecutor.submit(runnable);
        }
    }


}
