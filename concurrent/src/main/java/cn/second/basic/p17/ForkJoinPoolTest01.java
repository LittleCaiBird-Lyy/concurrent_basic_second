package cn.second.basic.p17;

import jdk.nashorn.internal.ir.CallNode;

import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * 演示ForkJoinPool线程池的使用
 * 使用该线程池模拟数列求和
 */
public class ForkJoinPoolTest01 {
    //计算数列的和，需要返回结果，可以定义任务继承RecursiveTask<>,这个泛型就是结果的返回类型
    private static class CountTask extends RecursiveTask<Long>{
        //定义数据规模的阈值，允许计算一万个数内的和，超过该阈值数需要分解
        private static final int THRESHOLD = 10000;

        //定义每次把大任务分解为100个小任务
        private static final int TASKNUM = 100;

        //计算数列的起始值
        private long start;
        //计算数列的结束值
        private long end;

        public CountTask(long start, long end) {
            this.start = start;
            this.end = end;
        }

        //重写RecursiveTask的compute()方法，计算数列的结果
        @Override
        protected Long compute() {
            //保存计算的结果
            long sum=0;
            //判断任务是否需要继续分解，如果当前数列end-start超过THRESHOLD，继续分解，小于直接计算
            if (end - start < THRESHOLD){
                //小于阈值直接计算
                for (long i = start; i <=end ; i++) {
                    sum+= i ;
                }
            }else{
                //数列范围超过阈值，需要继续分解
                //约定每次分解为100个小任务，计算每个任务的计算量
                long step = (start + end)/TASKNUM;
                //start = 0,end = 2000000,step = 2000,如果计算[0,200000]范围内数列的和，
                // 把该范围的数列分解为100个小任务，每个任务计算2000个数即可
                //注意，如果任务划分的层次很深，即THRESHOLD阈值太小，每个任务的计算量很小，层次划分很深
                //这样可能出现两种情况：一是系统内的线程数量会越积越多，导致性能下降严重，二是分解次数过多，
                //分解次数过多，方法调用过多导致栈溢出

                //创建一个存储任务的集合
                ArrayList<CountTask> subTaskList = new ArrayList<>();
                //任务的起始位置
                long pos = start;
                for (int i = 0; i < TASKNUM; i++) {
                    //每个任务结束位置
                    long lastOne = pos + step;
                    //调整最后一个任务的结束位置
                    if (lastOne > end){
                        lastOne = end;
                    }
                    //创建一个子任务
                    CountTask task = new CountTask(pos,lastOne);
                    //将任务添加到集合中
                    subTaskList.add(task);
                    //调用for()提交子任务
                    task.fork();
                    //调整下个任务的起始位置
                    pos += step + 1;

                }

                //等待所有的子任务结束后，合并计算等待结果
                for (CountTask task : subTaskList) {
                    //join()会一直等待子任务执行完毕返回执行结果
                    sum+= task.join();
                }
            }
            return sum;
        }
    }
    public static void main(String[] args) {
        //创建ForkJoinPool线程池
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        //创建一个大的任务
        CountTask task = new CountTask(0L,200000L);
        //把大的任务提交给线程池
        ForkJoinTask<Long> result = forkJoinPool.submit(task);
        try{
            //调用get方法返回结果
            Long res = result.get();
            System.out.println("计算数列结果为 ： " + res);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //验证
        long s = 0L;
        for (long i = 0; i <=200000 ; i++) {
            s += i ;
        }
        System.out.println(s);
    }
}
