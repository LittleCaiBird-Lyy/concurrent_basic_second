package com.thd.stack;

public class Test02 {
    public static void main(String[] args) {
        MyLinkStack linkStack = new MyLinkStack();

        System.out.println(linkStack.isEmpty());
        System.out.println(linkStack.getSize());

        linkStack.push("ppp");
        linkStack.push("aaa");
        linkStack.push("ppp");
        linkStack.push("aaa");

        System.out.println(linkStack);
    }
}
