package cn.second.basic.p2;

public class Test {
    public static void main(String[] args) {
        MyThread2 thread2 = new MyThread2();
        //开启子线程
        thread2.start();
        //当前是maIn线程
        for (int i = 0; i <=10; i++) {
            System.out.println("main thread --> " + i);
            int time = (int) (Math.random()*1000);
            try {
                Thread.sleep(time);//线程睡眠单位是毫秒   一秒 = 1000毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
