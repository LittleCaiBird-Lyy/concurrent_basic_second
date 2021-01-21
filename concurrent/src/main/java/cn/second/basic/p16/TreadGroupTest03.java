package cn.second.basic.p16;


/**
 * 肤质线程组中的neir
 */
public class TreadGroupTest03 {

    public static void main(String[] args) {
        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup group1 = new ThreadGroup("group1");
        ThreadGroup group2 = new ThreadGroup(mainGroup,"group2");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("当前线程 ； ===="+Thread.currentThread());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }

        };

        Thread t1 = new Thread(runnable,"t1");
        Thread t2 = new Thread(group1,runnable,"t1");
        Thread t3 = new Thread(group2,runnable,"t1");
        t1.start();
        t2.start();
        t3.start();

        //1.把main数组中的线程复制到数组中
        //定义存储线程的数组，长度为Main数组中活动的线程数
        Thread[] threadList = new Thread[mainGroup.activeCount()];
        /*//main线程组包括子线程组中的所有线程复制到数组中
        mainGroup.enumerate(threadList);
        //遍历数组
        for (Thread thread : threadList) {
            System.out.println(thread);
        }
        */
        //main线程组包括子线程组中无子线程复制到数组中
        mainGroup.enumerate(threadList,false);
        //遍历数组
        for (Thread thread : threadList) {
            System.out.println(thread);
        }

        //2.main中子复制到数组中
        Thread[] gs = new Thread[mainGroup.activeCount()];
        System.out.println("===========");
        mainGroup.enumerate(gs);
        for (Thread g : gs) {
            System.out.println(g);
        }
    }
}
