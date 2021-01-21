package cn.second.basic.p12;

/**
 * 测试一生产一消费
 */
public class ProducerStackTest01 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        StackProducerThread p = new StackProducerThread(stack);
        StackConsumerThread c = new StackConsumerThread(stack);

        p.start();
        c.start();
        /**
         * 运行结果是两个结果交替进行，一个线程负责生产，通知另一个线程负责消费
         */
    }
}
