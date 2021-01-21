package cn.second.basic.p2;

public class MyThread2 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i <=10; i++) {
            System.out.println("sub thread : " + i);
            int time = (int) (Math.random()*1000);
            try {
                Thread.sleep(time);//线程睡眠单位是毫秒   一秒 = 1000毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
