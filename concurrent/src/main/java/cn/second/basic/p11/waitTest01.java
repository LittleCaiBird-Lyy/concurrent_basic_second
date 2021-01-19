package cn.second.basic.p11;

/**
 * wait()方法notify()方法放到同步代码块当中，否则java.lang.IllegalMonitorStateException异常
 *      任何对象都可以调用wait(),notify(),这两个方法是Object类继承来的
 */
public class waitTest01 {
    public static void main(String[] args) {
        try {
            String test = "lyycto";
            //java.lang.IllegalMonitorStateException
            //没有把wait方法块的调用放到同步代码块中
            test.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
