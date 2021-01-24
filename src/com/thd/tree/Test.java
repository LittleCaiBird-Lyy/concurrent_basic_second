package com.thd.tree;

/**
 * 测试二叉树的遍历序列
 */
public class Test {
    public static void main(String[] args) {
        //创建根节点
        BinaryTreeNode root = new BinaryTreeNode("oo");
        //创建两个节点设置为根节点的左孩子和右孩子
        BinaryTreeNode xx = new BinaryTreeNode("xx");
        BinaryTreeNode yy = new BinaryTreeNode("yy");
        root.setLChild(xx);
        root.setRChild(yy);

        //创建两个节点作为xx节点的左孩子和右孩子
        BinaryTreeNode xl = new BinaryTreeNode("xll");
        BinaryTreeNode xr = new BinaryTreeNode("xrr");
        xx.setLChild(xl);
        xx.setRChild(xr);

        //创建1个节点作为yy节点的右孩子
        BinaryTreeNode yr = new BinaryTreeNode("yrr");
        yy.setRChild(yr);

        //创建二叉树
        BinaryTree tree = new BinaryTree(root);
        //先序遍历-->[oo, xx, xll, xrr, yy, yrr]
        tree.preOrder();
        //中序遍历
        tree.inOrder();
        //后续遍历
        tree.postOrder();
        //层序遍历
        tree.levelOrder();
    }
}
