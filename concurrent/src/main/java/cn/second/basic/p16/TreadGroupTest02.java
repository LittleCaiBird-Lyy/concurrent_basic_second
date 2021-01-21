package cn.second.basic.p16;

/**
 * 线程组基本操作
 */
public class TreadGroupTest02 {

    public static void main(String[] args) {

        ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

        ThreadGroup group = new ThreadGroup("group");

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
        Thread t2 = new Thread(group,runnable,"t1");
        t1.start();
        t2.start();

        // 4  t1 t1 main 垃圾回收器
        System.out.println("main group 中活动线程数" + mainGroup.activeCount());
        System.out.println(" group 中活动线程数" + group.activeCount());
        System.out.println(" main 中zi线程数" + mainGroup.activeGroupCount());
        System.out.println(" group 中zi线程数" + group.activeGroupCount());
        System.out.println(" main 中fu线程数" + mainGroup.getParent());
        System.out.println(" group 中fu线程数" + group.getParent());
        System.out.println(mainGroup.parentOf(mainGroup));
        System.out.println(mainGroup.parentOf(group));
        mainGroup.list();

    }
}
