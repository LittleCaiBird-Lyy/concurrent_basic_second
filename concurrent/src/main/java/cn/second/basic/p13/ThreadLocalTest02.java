package cn.second.basic.p13;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 在多线程环境中，把字符串转换为日期对象，多个线程使用同一个SimpleDateFormat对象可能会产生线程安全问题
 * 为每一个线程指定自己的SimpleDateFormat对象，使用ThreadLocal
 *
 */
public class ThreadLocalTest02 {
    //定义SimpleDateFormat对象，该对象可以把字符串转化为日期
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

    static ThreadLocal<SimpleDateFormat> threadLocal = new ThreadLocal<SimpleDateFormat>();

    //定义Runnable接口实现类
    static class ParseDate implements Runnable{
        private int i = 0;
        public ParseDate(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            //构建一个表示日期的字符串
            try {
                String text = "2006年11月22日 08:28:" + i%60;
                //把字符串转换为日期
                /*Date date = sdf.parse(text);
                System.out.println(i + "--" + date);*/
                //先判断一下当前线程是否有SimpleDateFormat对象，如果当前线程没有SimpleDateFormat对象就创建一个
                //如果有直接使用
                if (threadLocal.get() == null){
                    threadLocal.set(new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss"));
                }
                Date date = threadLocal.get().parse(text);
                System.out.println(i + "--" + date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        //创建100个线程
        for (int i = 0; i < 100; i++) {
            new Thread(new ParseDate(i)).start();
        }
    }

}
