package cn.second.basic.p10;

import java.util.concurrent.atomic.AtomicLong;

/**
 * 使用原子变量类定义一个计数器
 * 该计数器在整个程序中都能使用，并且所有的地方都使用这一个计数器，这个计数器可以设计为单例
 */
public class Indicator {
    //构造方法私有化
    private Indicator(){}

    //定义一个私有的本类静态对象
    private static final Indicator INSTANCE = new Indicator();

    //3.提供一个公共的静态方法返回该唯一实例
    public static Indicator getInstance(){
        return INSTANCE;
    }

    //记录请求总数
    //使用原子变量类保存请求总数
    private final AtomicLong requestCount = new AtomicLong(0);
    //使用原子变量类保存成功数
    private final AtomicLong successCount = new AtomicLong(0);
    //使用原子变量类保存失败数
    private final AtomicLong failureCount = new AtomicLong(0);

    //有新的请求
    public void newRequestReceive(){
        requestCount.incrementAndGet();
    }

    //处理成功
    public void requestProcessSuccess(){
        successCount.incrementAndGet();
    }

    //处理失败
    public void requestProcessFailure(){
        failureCount.incrementAndGet();
    }

    //查看总数，成功数，失败数
    public long getRequestCount(){
        return  requestCount.get();
    }

    public long getSuccessCount(){
        return successCount.get();
    }

    public long getFailureCount(){
        return failureCount.get();
    }

}
