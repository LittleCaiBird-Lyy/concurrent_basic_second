package cn.second.basic.p6;


public class SubDaemonThread extends Thread{
    @Override
    public void run() {
        super.run();
        while (true){
            System.out.println(" sub Thread .....");
        }
    }
}
