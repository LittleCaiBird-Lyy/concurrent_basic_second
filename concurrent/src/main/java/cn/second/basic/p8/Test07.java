package cn.second.basic.p8;

import java.sql.SQLOutput;

/**
 * 脏读
 *      出现读取属性值出现了意外，读取的是中间值，而不是修改之后的值
 *      出现脏读的原因是对共享数据的修改，与共享数据不同步
 *      读写都要同步
 */
public class Test07 {
    public static void main(String[] args) throws InterruptedException {
        //开启子线程设置用户名和密码
        PublicValue publicValue = new PublicValue();
        SubThread t1 = new SubThread(publicValue);
        t1.start();

        //为了确定设置成功
        Thread.sleep(1000);
        //在main线程中读取用户名密码
        publicValue.getValue();
    }

    //定义线程，设置用户名和密码
    static class SubThread extends Thread{
        private PublicValue publicValue;
        public SubThread(PublicValue publicValue){
            this.publicValue = publicValue;
        }

        @Override
        public void run() {
            publicValue.setValue("bbb","123");
        }
    }

    static class PublicValue{
        private String name = "aaa";
        private String pwd = "666";

        public synchronized void getValue(){
            System.out.println(Thread.currentThread().getName() + " ,  getter -- name :" + name
            + "  , -- pwd: " + pwd);
        }

        public synchronized void setValue(String name ,String pwd){
            this.name = name;
            try {
                //模拟操作需要一段时间
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.pwd = pwd;
            System.out.println(Thread.currentThread().getName() + " , setter -- name : " + name
            + " , --pwd : " + pwd);
        }

    }
}
