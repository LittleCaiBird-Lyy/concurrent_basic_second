package cn.second.basic.p11;

/**
 * Interrupt()会中断线程的wait()等待
 */
public class waitTest05 {
    public static void main(String[] args) throws InterruptedException {
        SubThread t = new SubThread();
        t.start();

        Thread.sleep(2000);
        t.interrupt();

    }

    private static final Object LOCK = new Object();

    static class SubThread extends Thread{
        @Override
        public void run() {
            synchronized (LOCK){
                try {
                    System.out.println("begin wait ...");
                    LOCK.wait();
                    System.out.println("end wait ...");
                } catch (InterruptedException e) {
                    System.out.println("wait等待被中断====");
                }

            }
        }
    }
}
