package com.thd.stack;

/**
 * 使用栈完成进制转换
 */
public class TestBaseConversion {
    public static void main(String[] args) {

        System.out.println(convert(100,8));
    }

    /**
     * 把一个十进制整数num转化为decimal指定的进制
     * @param num   接收十进制
     * @param decimal  指定进制
     * @return  返回num这个整数对应的decimal进制的字符串
     */
    public static String convert(int num , int decimal){
        //保存余数
        MyArrayStack stack = new MyArrayStack();
        //求余数
        int remainder = num % decimal;
        while (num != 0){
            //余数压栈
            stack.push(remainder);
            num = num / decimal;
            remainder = num % decimal;
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
