package com.enjoyedu.second;



/**
 * 二叉平衡树，AVL树
 */
public class AVLTree {
    //节点
    public static class Node{
        int data;
        int height;
        Node leftNode;
        Node rightNode;

        public Node(int data){
            this.data = data;
        }
    }

    //获取节点的高度
    public static int getHeight(Node p){
        return p == null?  -1 : p.height;
    }

    public static void main(String[] args) {
        Node root = null;
        root = insert(root,30);
        root = insert(root,20);
        root = insert(root,40);
        root = insert(root,10);
        root = insert(root,25);
        //插入节点在失衡结点的左子树的左边
        root = insert(root,5);
        //打印树，按照先打印左子树，再打印右子树的方式
        printTree(root);

    }

    public static void printTree(Node root) {
        System.out.println(root.data);
        if (root.leftNode != null){
            System.out.print("left:");
            printTree(root.leftNode);
        }
        if (root.rightNode != null){
            System.out.print("right:");
            printTree(root.rightNode);
        }
    }

    //AVL树的插入方法
    public static Node insert(Node root , int data){
        if (root == null){
            //新建，root.data是已有的
            root = new Node(data);
            return root;
        }
        //先插入在调整而不是先调整再插入
        if (data <= root.data){//插入到其左子树上
            root.leftNode = insert(root.leftNode,data);
            //调整平衡
            if (getHeight(root.leftNode) - getHeight(root.rightNode) >1){
                if (data <= root.leftNode.data){
                    System.out.println("LL旋转");
                    root=LLRotate(root);
                }else {
                    System.out.println("LR旋转");
                    root=LRRotate(root);
                }
            }
        }else {//插入到右子树上
            root.rightNode = insert(root.rightNode,data);
            //调整平衡
            if (getHeight(root.rightNode) - getHeight(root.leftNode) > 1){
                if (data <= root.rightNode.data){
                    System.out.println("RL旋转");
                    root = RLRotate(root);
                }else {
                    System.out.println("RR旋转");
                    root = RRRotate(root);
                }
            }

        }
        //只要插入了左右子节点和主节点的高度都要调整
        root.height = Math.max(getHeight(root.leftNode),getHeight(root.rightNode))+1;
        return  root;

    }

    //LR旋转
    public static Node LRRotate(Node p){
        Node node = RRRotate(p.leftNode);
        return LLRotate(p);
    }

    //RL旋转
    public static Node RLRotate(Node p){
       //先将失衡点p的右子树进行LL平衡旋转
        Node node = LLRotate(p.rightNode);
        // 再将失衡点p进行RR平衡旋转并返回新节点代替原失衡点p
        return RRRotate(p);
    }

    /*
     * RR旋转
     * 右旋示意图(对结点30进行左旋)
     *      20                          30
     *     /  \                        /  \
     *    10  30                     20   40
     *       /  \      --RR旋转-     /  \   \
     *      25  40                 10  25  50
     *           \
     *           50
     *
     */
    // RR旋转
    public static  Node RRRotate(Node p){//p为原来的根节点
        //新的根节点
        Node rsubtree = p.rightNode;
        //将新节点的左子树25成为失衡点20的右子树
        p.rightNode = rsubtree.leftNode;
        //将失衡点20作为新结点的左子树
        rsubtree.leftNode=p;
        //重新设置失衡点20和新节点30的高度
        p.height = Math.max(getHeight(p.leftNode),getHeight(p.rightNode))+1;
        rsubtree.height = Math.max(getHeight(rsubtree.leftNode),getHeight(rsubtree.rightNode))+1;
        return rsubtree;
    }

    /*
     * LL旋转
     * 左旋示意图(对结点20进行左旋)
     *      30                       20
     *     /  \                     /  \
     *    20  40                  10   30
     *   /  \      --LL旋转-       /   /  \
     *  10   25                  5   25   40
     *  /
     * 5
     *
     */
    //LL旋转
    public static Node LLRotate(Node p){
        Node lsubtree = p.leftNode;
        p.leftNode = lsubtree.rightNode;
        lsubtree.rightNode = p;
        //重新设置高度
        p.height = Math.max(getHeight(p.leftNode),getHeight(p.rightNode))+1;
        lsubtree.height = Math.max(getHeight(lsubtree.leftNode),p.height)+1;
        return  lsubtree;
    }


    


}
