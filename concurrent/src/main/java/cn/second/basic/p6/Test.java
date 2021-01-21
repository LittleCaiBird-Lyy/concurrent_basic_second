package cn.second.basic.p6;

/**
 * 设置线程为守护线程
 */
public class Test {
    public static void main(String[] args) {
        SubDaemonThread thread = new SubDaemonThread();
        //设置线程为守护线程-->在线程启动前
        thread.setDaemon(true);
        thread.start();

        //当前线程为main线程，main线程结束守护线程结束
        for (int i = 1; i <=10 ; i++) {
            System.out.println("main == " + i);
        }
    }
}
