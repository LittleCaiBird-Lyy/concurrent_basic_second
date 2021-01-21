package cn.second.basic.p12;

/**
 * 定义线程模拟消费者
 */
public class ConsumerThread extends Thread{
    //消费者使用数据，就是使用ValueOP类的value字段值
    private ValueOP obj;

    public ConsumerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.getValue();
        }
    }
}
