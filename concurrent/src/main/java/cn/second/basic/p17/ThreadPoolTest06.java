package cn.second.basic.p17;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 扩展线程池
 */
public class ThreadPoolTest06 {
    //定义一个任务类
    private static class MyTask implements Runnable{
        String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(name + " 任务正在被线程 " + Thread.currentThread().getId() + " 执行");
            try {
                //模拟任务执行时长1秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        //定义扩展线程池，可以定义线程池类继承ThreadPoolExecutor,
        // 在子类中重写beforeExecute()/aferExecute()方法
        //也可以直接使用ThreadPoolExecutor的内部类
        ExecutorService executorService = new ThreadPoolExecutor(5,5,0,TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()){
            //在内部类中重写任务开始方法

            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(t.getId() + " 线程准备任务 ： " + ((MyTask)r).name);
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask)r).name + " 执行完毕");
            }

            //线程池退出
            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        //向线程池中添加任务
        for (int i = 0; i < 5; i++) {
            MyTask task = new MyTask("task- " + i);
            executorService.execute(task);
        }

        //关闭线程池-->仅仅是说线程池不再接收新的任务，线程池中已接收的任务正常执行完毕
        executorService.shutdown();

    }
}
