package cn.second.basic.p11;

/**
 * wait(long)
 */
public class waitTest07 {
    public static void main(String[] args) {
        final Object obj = new Object();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized(obj){
                    try {
                        System.out.println("thread begin wait");
                        obj.wait(5000);
                        System.out.println(" end wait ...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
}
