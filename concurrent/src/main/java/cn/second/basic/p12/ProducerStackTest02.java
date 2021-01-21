package cn.second.basic.p12;

/**
 * 测试多生产--多消费
 */
public class ProducerStackTest02 {
    public static void main(String[] args) {
        MyStack stack = new MyStack();

        StackProducerThread p = new StackProducerThread(stack);
        StackProducerThread p2 = new StackProducerThread(stack);
        StackProducerThread p3 = new StackProducerThread(stack);
        StackConsumerThread c = new StackConsumerThread(stack);
        StackConsumerThread c1 = new StackConsumerThread(stack);
        StackConsumerThread c2 = new StackConsumerThread(stack);
        c.setName("消费者1号");
        c1.setName("消费者2号");
        c2.setName("消费者3号");

        p.setName("生产者一号");
        p2.setName("生产者二号");
        p3.setName("生产者三号");

        p.start();
        c.start();
        c1.start();
        c2.start();
        /**
         * 运行结果是两个结果交替进行，一个线程负责生产，通知另一个线程负责消费
         */
    }
}
