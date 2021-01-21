package cn.second.basic.p7;

import java.util.Random;

/**
 * 测试线程的可见性
 */
public class Test02 {
    public static void main(String[] args) {
        MyTast tast = new MyTast();
        new Thread(tast).start();

        try {
            Thread.sleep(1000);
            //主线程一秒钟之后取消子线程
            /**
             * 可能会出现以下情况：
             *          在main线程中调用了task.cancel()方法，把task对象的toCancel变量修改为true
             *          可能存在子线程看不到main线程对toCancel做的修改，在子线程中toCancel变量一直为false
             * 导致子线程看不到Main线程对toCancel变量更新的原因，可能为：
             *          1.JIT即时编译器会对run方法中while循环进行优化为：(为了提高代码运行效率，重复读取toCancel变量)
             *             if(!toCancel){
                 *           while (true){
                 *                 if (doSomething()){
                 *                 }
                 *             }
             *             }
             *          2.可能与计算机的存储系统有关。假设分别有两个cpu内核运行main线程与子线程，
             *            运行子线程的cpu无法立即读取运行main线程cpu中的数据
             */
            tast.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static class MyTast implements Runnable{
        private boolean toCancel = false;
        @Override
        public void run() {
            while (!toCancel){
                if (doSomething()){
                }
            }
            if (toCancel){
                System.out.println("任务被取消");
            }else {
                System.out.println("任务正常结束");
            }
        }
        private boolean doSomething(){
            System.out.println("执行某个任务.....");
            try {
                //模拟执行任务的时间
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return true;
        }

        public void cancel(){
            toCancel = true;
            System.out.println("收到 取消线程的消息");
        }
    }

}
