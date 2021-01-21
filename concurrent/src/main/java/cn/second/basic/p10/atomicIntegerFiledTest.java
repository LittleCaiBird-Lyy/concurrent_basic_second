package cn.second.basic.p10;

/**
 *
 */
public class atomicIntegerFiledTest {
    public static void main(String[] args) {
        User user = new User(1234,10);
        //开启10个线程
        for (int i = 0; i < 10; i++) {
            new SubThread(user).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(user);
    }
}
