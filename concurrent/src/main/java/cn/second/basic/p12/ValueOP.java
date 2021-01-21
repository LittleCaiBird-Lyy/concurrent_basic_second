package cn.second.basic.p12;

/**
 * 定义一个操作数据的类
 */

public class ValueOP {
    private String value = "";

    //定义方法修改value字段的值
    public void setValue(){
        synchronized (this){
            //如果value值不是空串就等待
            while (!value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //如果空串设置value字段的值
            String value = System.currentTimeMillis() + "-" + System.nanoTime();
            System.out.println("set设置的值是 :  + " + value);
            this.value = value;
            //在多生产多消费情况：只有notify会导致生产者唤醒生产者，消费者唤醒消费者的情况--->假死状态
            //this.notify();
            this.notifyAll();
        }
    }

    //定义方法读取字段值
    public void getValue(){
        synchronized (this){
            //如果VALUE是空就的带-->while 是一直判断的，if只判断一次
            while (value.equalsIgnoreCase("")){
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //不是空串，读取字段值
            System.out.println("get的值是：" + this.value);
            this.value = "";
            this.notify();
        }
    }

    //定义方法读取字段值
}
