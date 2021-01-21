package cn.second.basic.p9;

public class Test02 {
    public static void main(String[] args) {
        //创建PrintString
        PrintString printString = new PrintString();

        //开启子线程，让子线程执行printString.printStringMethod();方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                printString.printStringMethod();
            }
        }).start();

        //main线程睡眠1000毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在Main线程中修改打印标志");
        printString.setContinuePrint(false);
       //程序运行，查看在main线程中修改了打印标志后，子线程打印是否可以结束打印
        //程序运行后，可能会出现死循环情况
        //分析原因：main线程修改了printString对象的打印标志后，子线程读不到
        //解决办法：使用volatile关键字修饰pringString对象的打印标志
        //volatile的作用可以强制线程从公共内存中读取变量的值，而不是从工作内存中读取
    }


    //定义类，打印字符串
    static class PrintString{
        private volatile boolean continuePrint = true;
        public PrintString setContinuePrint(boolean continuePrint){
            this.continuePrint = continuePrint;
            return this;
        }

        public void printStringMethod(){
            while (continuePrint){
                //System.out.println(Thread.currentThread().getName() + "开始...");
            }
            try {
                System.out.println("sub thread ...");
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "结束++");
        }
    }
}
