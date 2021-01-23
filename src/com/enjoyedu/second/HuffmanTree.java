package com.enjoyedu.second;


import java.util.ArrayList;
import java.util.List;

public class HuffmanTree {

    public static class Node<E>{
        E data;
        //权重
        int weight;
        Node leftNode;
        Node rightNode;

        public Node(E data, int weight ){
            super();
            this.data = data;
            this.weight = weight;

        }

        @Override
        public String toString(){
            return "Node[" + weight + ", data=" + data + "]";
        }
    }
    public static void main(String[] args) {
        List<Node> nodes = new ArrayList<Node>();
        //把子节点加入至list中
        nodes.add(new Node("a",10));
        nodes.add(new Node("b",15));
        nodes.add(new Node("c",12));
        nodes.add(new Node("d",3));
        nodes.add(new Node("e",4));
        nodes.add(new Node("f",13));
        nodes.add(new Node("g",1));
        Node root = HuffmanTree.createTree(nodes);
        printTree(root);
    }



    //哈夫曼树方法的实现
    private static Node createTree(List<Node> nodes){
        //如果Node节点内有两个以上的节点
        while (nodes.size()>1){
            //排序方式是增序的
            sort(nodes);

            //权重最小的
            Node left = nodes.get(0);
            //权重第二小的
            Node right = nodes.get(1);
            //生成一个新的节点（父节点），父节点的权重为左右两个节点的和
            Node parent = new Node(null,left.weight+right.weight);
            //树的连接，让子节点与父节点进行连接
            parent.leftNode = left;
            parent.rightNode = right;
            //拿完了节点要删除，并加入父节点？？
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(parent);
        }
        //返回根节点
        return nodes.get(0);

    }
    //冒泡排序的实现
    public static void sort(List<Node> nodeList){
        if (nodeList.size() <= 1){
            return;
        }
        for (int i = 0; i < nodeList.size(); i++) {
            for (int j = 0; j < nodeList.size()-i-1; j++) {
                if (nodeList.get(j).weight>nodeList.get(j+1).weight){
                    Node temp = nodeList.get(j+1);
                    nodeList.set(j+1,nodeList.get(j));
                    nodeList.set(j,temp);
                }
            }
        }
        return;
    }

    //打印哈夫曼树
    public static void printTree(Node root) {
        System.out.println(root.toString());
        if(root.leftNode !=null){
            System.out.print("left:");
            printTree(root.leftNode);
        }
        if(root.rightNode !=null){
            System.out.print("right:");
            printTree(root.rightNode);
        }
    }



}
        //进行哈夫曼树的构造



