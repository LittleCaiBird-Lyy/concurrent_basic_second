package cn.second.basic.p9;

public class Test01 {
    public static void main(String[] args) {
        //创建PrintString
        PrintString printString = new PrintString();
        //调用方法打印字符串
        printString.printStringMethod();

        //main线程睡眠1000毫秒
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("在Main线程中修改打印标志");
        printString.setContinuePrint(false);
        //修改完打印标志后运行程序，查看运行结果
        //程序不会停止，因为printString.printStringMethod();
        // 方法调用后，该方法一直处于死循环状态，程序根本执行不到这条语句
        //解决方法：使用多线程技术

    }


    //定义类，打印字符串
    static class PrintString{
        private boolean continuePrint = true;
        public PrintString setContinuePrint(boolean continuePrint){
            this.continuePrint = continuePrint;
            return this;
        }

        public void printStringMethod(){
            while (continuePrint){
                System.out.println(Thread.currentThread().getName());
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
