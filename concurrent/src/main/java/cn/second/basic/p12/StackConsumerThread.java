package cn.second.basic.p12;

/**
 * 消费者线程
 */
public class StackConsumerThread extends Thread{
    private MyStack stack;

    public StackConsumerThread(MyStack stack) {
        this.stack = stack;
    }

    @Override
    public void run() {
        while (true){
            stack.pop();
        }
    }
}
