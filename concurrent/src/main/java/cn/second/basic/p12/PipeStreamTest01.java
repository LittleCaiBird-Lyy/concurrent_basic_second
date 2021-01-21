package cn.second.basic.p12;


import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * 使用PipedInputStream和PipeOutputStream普通字节流在线程之间传递数据
 */
public class PipeStreamTest01 {
    public static void main(String[] args) {
        //定义管道字节流
        PipedInputStream inputStream = new PipedInputStream();
        PipedOutputStream outputStream = new PipedOutputStream();

        //在输入管道流和输出管道流之间简历连接
        try {
            inputStream.connect(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //创建一个线程向管道流中写入数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                writeData(outputStream);
            }
        }).start();

        //定义一个线程从管道流读取数据
        new Thread(new Runnable() {
            @Override
            public void run() {
                readData(inputStream);
            }
        }).start();

    }

    //定义方法，向管道流中写入数据
    public static void writeData(PipedOutputStream out ){
        //分别把0-100之间的数写入到管道中
        try {
            for (int i = 0; i < 100; i++) {
                String data = "" + i ;
                //把自己数组写到输出管道流中
                out.write(data.getBytes());
            }
            //关闭管道流
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //定义方法，从管道流中读取数据
    public static void readData(PipedInputStream in){
        byte[] bytes = new byte[1024];
        try {
            //从管道输入字节流中读取字节保存到字节数组中，返回读到的字节数
            int read = in.read(bytes);
            //如果没有读到任何数据返回-1
            while (read != -1){
                //把bytes数组中从0开始读到的len个字节转换为字符串打印
                System.out.println(new java.lang.String(bytes,0,read));
                //继续从管道中读取数据
                read = in.read(bytes);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
