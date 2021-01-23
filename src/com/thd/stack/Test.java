package com.thd.stack;

public class Test {
    public static void main(String[] args) {
        MyArrayStack stack = new MyArrayStack();

        System.out.println(stack.isEmpty());
        System.out.println(stack.getSize());

        stack.push("aa");
        stack.push("bb");
        stack.push("cc");
        stack.push("dd");

        System.out.println(stack.isEmpty());
        System.out.println(stack.getSize());

        System.out.println(stack);

        System.out.println(stack.peek());

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
