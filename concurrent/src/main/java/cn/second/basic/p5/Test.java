package cn.second.basic.p5;

public class Test {
    public static void main(String[] args) {
        //开启子线程计算累加和
        SubThread6 t6 = new SubThread6();
        t6.start();;

        //Main线程计算累加和
        long begin = System.currentTimeMillis();
        long sum = 0;
        for (int i = 1; i <=1000000 ; i++) {
            sum+=i;
        }
        long end = System.currentTimeMillis();
        System.out.println("Main用时====>" + (end - begin));
    }
}
