package cn.second.basic.p5;

public class SubThread6 extends Thread{
    @Override
    public void run() {
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <=1000000 ; i++) {
            sum+=i;
            Thread.yield();//线程让步，放弃cpu执行权
        }
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - begin));
    }
}
