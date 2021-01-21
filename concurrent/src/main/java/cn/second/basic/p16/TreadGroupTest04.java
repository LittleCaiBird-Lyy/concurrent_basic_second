package cn.second.basic.p16;

/**
 * 线程组的批量中断
 */
public class TreadGroupTest04 {
    public static void main(String[] args) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                    System.out.println("当前线程 ； ===="+Thread.currentThread() + "开始循环");
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread() + "--------------");
                    /*try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        //如果中断睡眠中的线程，会清除线程标志，产生中断异常
                        e.printStackTrace();
                    }*/
                }
                System.out.println(Thread.currentThread() + "jieshu");

            }
        };

        ThreadGroup group = new ThreadGroup("group");
        for (int i = 0; i <= 5 ; i++) {
            new Thread(group,runnable).start();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //中断组中所有线程
        group.interrupt();

    }
}
