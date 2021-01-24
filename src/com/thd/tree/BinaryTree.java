package com.thd.tree;

import java.util.LinkedList;
import java.util.function.IntFunction;

/**
 * 使用三叉链表创建二叉树
 */
public class BinaryTree {
    //二叉树的根节点
    private BinaryTreeNode root;
    //构造方法
    public BinaryTree(BinaryTreeNode root) {
        super();
        this.root = root;
    }

    //返回元素的个数
    public int getSize(){
        if (root == null){
            //空树的情况
            return 0;
        }
        return root.getSize();
    }

    //判断二叉树是否为空
    public boolean isEmpty(){
        return getSize() == 0;
    }

    //返回根节点
    public BinaryTreeNode getRoot(){
        if (root != null){
            return root;
        }
        return null;
    }

    //返回树的高度
    public int getHeight(){
        if (root == null){
            return  0;
        }
        return root.getHeight();
    }

    //二叉树先序遍历
    //先序遍历二叉树，把遍历的节点存储到List列表当中
    private void preOrderRecusion(BinaryTreeNode root ,LinkedList<Object> list){
        if (root == null){
            //空树
            return;
        }
        //先访问根节点，把根节点存储到列表中
        list.add(root.getData());
        //递归，先序遍历左子树
        preOrderRecusion(root.getlChild(),list);
        //递归，先序遍历右子树
        preOrderRecusion(root.getrChild(),list);

    }

    //打印当前二叉树的先序遍历序列
    public void preOrder(){
        LinkedList<Object> list = new LinkedList<>();
        preOrderRecusion(root,list);
        System.out.println(list);
    }

    //中序遍历
    private void inOrderRecursion(BinaryTreeNode root , LinkedList<Object> list){
        if (root == null){
            //空树
            return;
        }
        //递归遍历左子树
        inOrderRecursion(root.getlChild(),list);
        //访问根
        list.add(root.getData());
        //递归遍历右子树
        inOrderRecursion(root.getrChild(),list);
    }

    public void inOrder(){
        LinkedList<Object> list = new LinkedList<>();
        inOrderRecursion(root,list);
        System.out.println(list);
    }

    //后序遍历
    private void postOrderRecursion(BinaryTreeNode root , LinkedList<Object> list){
        if (root == null){
            //空树
            return;
        }
        //递归遍历左子树
        postOrderRecursion(root.getlChild(),list);
        //递归遍历右子树
        postOrderRecursion(root.getrChild(),list);
        //访问根
        list.add(root.getData());
    }

    public void postOrder(){
        LinkedList<Object> list = new LinkedList<>();
        postOrderRecursion(root,list);
        System.out.println(list);
    }

    //二叉树层序遍历
    private void leverOrderTranverse(BinaryTreeNode root, LinkedList<Object> list){
        if (root == null){
            return;
        }
        //定义一个队列
        LinkedList<BinaryTreeNode> queue = new LinkedList<>();
        //根节点入队
        queue.offer(root);
        while (!queue.isEmpty()){
            //队列头部的结点取出来
            BinaryTreeNode node = queue.poll();
            //把节点的数据添加到List列表中
            list.add(node.getData());
            //分别把node的左节点和右节点入队
            if (node.hasLChild()){
                queue.offer(node.getlChild());
            }
            if (node.hasRChild()){
                queue.offer(node.getrChild());
            }
        }
    }
    public void levelOrder(){
        LinkedList<Object> linkedList = new LinkedList<>();
        leverOrderTranverse(root,linkedList);
        System.out.println(linkedList);
    }
}
