package cn.second.basic.p12;

/**
 *测试一生产一消费的情况
 */
public class producerdataTest01 {
    public static void main(String[] args) {
        ValueOP valueOP = new ValueOP();

        ProducerThread p = new ProducerThread(valueOP);
        ConsumerThread c = new ConsumerThread(valueOP);

        p.start();
        c.start();
    }

}
