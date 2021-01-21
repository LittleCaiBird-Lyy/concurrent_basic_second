package cn.second.basic.p12;

/**
 * 定义线程类模拟生产者
 *
 */
public class ProducerThread extends Thread{
    //生产者生产数据就是调用Value类的setValue方法给value字段赋值
    private ValueOP obj;

    public ProducerThread(ValueOP obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        while (true){
            obj.setValue();
        }
    }
}
