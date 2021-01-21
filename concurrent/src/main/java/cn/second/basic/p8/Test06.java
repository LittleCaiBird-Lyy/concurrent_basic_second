package cn.second.basic.p8;

/**
 * 同步方法与同步代码块如何选择
 *      同步方法锁的粒度粗，执行效率低
 *      同步代码块，锁的粒度细，执行效率高
 *
 */
public class Test06 {
    public static void main(String[] args) {
        Test06 obj = new Test06();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                obj.doLongTimeTask();
            }
        }).start();
    }

    //同步代码块，锁的粒度细，执行效率高
    public void doLongTimeTask(){
        System.out.println("Task Begin");
        try {
            //模拟任务需要准备三秒钟
            Thread.sleep(3000);
            synchronized(this){
                System.out.println("开始同步");
                for (int i = 1; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i);
                }
            }
            System.out.println("Task end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //同步方法锁的粒度粗，执行效率低
    public synchronized void doLongTimeTask2(){
        System.out.println("Task Begin");
        try {
            //模拟任务需要准备三秒钟
            Thread.sleep(3000);
                System.out.println("开始同步");
                for (int i = 1; i <= 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "-->" + i);
                }
            System.out.println("Task end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
