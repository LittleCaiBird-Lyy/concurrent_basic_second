package cn.second.basic.p16;

/**
 * 设置线程的UncaughtExceptionHandler回调接口,在实际开发中设计异常处理常用，特别在异步执行方法
 * 如果线程产生了异常，则JVM会调用dispatchUncaughtException()方法，在该方法中调用了
 * getUncaughtExceptionHandler().uncaughtException(this,e);
 * 如果当前线程设置了设置线程的UncaughtExceptionHandler回调接口，直接调用自己的uncaughtException(this,e);
 * 没有设置，则调用当前线程所在线程组的回调接口
 * 如果线程组也没有设置回调接口，则直接把异常的栈信息定向到System.err中
 *
 */
public class ThreadExceptionTest01 {
    public static void main(String[] args) {
        //1.设置线程全局的回调接口
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //t参数接收发生异常的数据，e就是该线程中的异常
                System.out.println(t.getName() + "线程产生了异常 ： " + e.getMessage());
            }
        });

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始运行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    //线程中的受捡异常必须被主力
                    e.printStackTrace();
                }
                System.out.println(12 / 0);
            }
        });
        t1.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String txt = null;
                System.out.println(txt.length());
            }
        }).start();

    }
}
