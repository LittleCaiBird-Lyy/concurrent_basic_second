package cn.second.basic.p12;

/**
 * 生产者线程
 */
public class StackProducerThread extends Thread{
    private MyStack stack;

    public StackProducerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.push();
        }
    }
}
