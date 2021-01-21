package cn.second.basic.p9;

/**
 * volatile不具备原子性
 */
public class Test03 {
    public static void main(String[] args) {
        //在main中创建10个子线程
        for (int i = 0; i < 10; i++) {
            new MyThread().start();
        }

    }

    static class MyThread extends Thread{
        //volatile关键字仅仅是表示所有线程从主内存读取count变量的值
        volatile public static int count;

        public  synchronized static void addCount(){
            for (int i = 0; i < 1000; i++) {
                //count++;不是原子操作
                count++;
            }
            System.out.println(Thread.currentThread().getName() + "count=" + count);
        }

        @Override
        public void run() {
            addCount();
        }
    }
}
