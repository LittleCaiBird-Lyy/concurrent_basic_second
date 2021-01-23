package com.thd.Dual;

public class Test {
    public static void main(String[] args) {
        MyDualLinkedList myDualLinkedList = new MyDualLinkedList();

        System.out.println(myDualLinkedList.getSize());
        System.out.println(myDualLinkedList.isEmpty());

        myDualLinkedList.insert(0,"gg");
        myDualLinkedList.insert(0,"jj");
        myDualLinkedList.insert(1,"dd");
        myDualLinkedList.insert(3,"mm");

        System.out.println(myDualLinkedList);

        System.out.println(myDualLinkedList.indexOf("jj"));
        System.out.println(myDualLinkedList.indexOf("mm"));
        System.out.println(myDualLinkedList.indexOf("DD"));
        System.out.println(myDualLinkedList.indexOf("xx"));

        System.out.println(myDualLinkedList.remove(0));
        System.out.println(myDualLinkedList);

    }
}
