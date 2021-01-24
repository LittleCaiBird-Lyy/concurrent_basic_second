package com.thd.tree;

/**
 * 三叉列表的节点
 */
public class BinaryTreeNode {
    //数据域
    private Object data;
    //父节点
    private BinaryTreeNode parent;
    //左孩子
    private BinaryTreeNode lChild;
    //右孩子
    private BinaryTreeNode rChild;
    //以当前节点为根节点的二叉树的高度
    private int height;
    //以当前节点为节点的二叉树所有节点的数量
    private int size;

    public BinaryTreeNode(Object e ){
        data = e ;
        parent = null;
        lChild = null;
        rChild = null;
        height = 1;
        size = 1;
    }
    public BinaryTreeNode(){
        this(null);
    }

    //判断当前节点的情况
    //判断节点是否有父节点
    public boolean hasParent(){
        return parent !=null ;
    }

    //判断是否有左孩子
    public boolean hasLChild(){
        return lChild != null;
    }

    //判断是否有右孩子
    public boolean hasRChild(){
        return rChild != null;
    }

    //判断是否为叶子节点
    public boolean isLeaf(){
        return lChild == null && rChild == null;
    }

    //判断是否为父节点的左孩子
    public boolean isLChild(){
        return parent != null && parent.lChild == this;
    }

    //判断是否为父节点的you孩子
    public boolean isRChild(){
        return parent != null && parent.rChild == this;
    }

    //与高度相关的操作
    //返回高度
    public int getHeight(){
        return height;
    }

    //跟新当前节点的高度，祖先节点的高度
    public void updataHeight(){
        //保存新的高度
        int newHeight = 0;
        //当前节点的高度为左子树的高度，或者右子树的高度较大的那个加一
        if (hasLChild()){
            newHeight = Math.max(newHeight,getlChild().getHeight() + 1);
        }
        if (hasRChild()){
            newHeight = Math.max(newHeight,getrChild().getHeight() + 1);
        }
        //如果当前节点的高度有变化，递归更新祖先节点的高度
        if (newHeight == height){
            //如果刚刚计算的高度与原来的一样，不需要更新祖先的高度
            return;
        }
        //把心的高度作为当前节点的高度
        height = newHeight;
        //更新祖先节点的高度
        if (hasParent()){
            getParent().updataHeight();
        }

    }

    //返回左子树
    public BinaryTreeNode getlChild(){
        return lChild;
    }

    //与左孩子相关的操作
    //设置当前节点的左孩子，把原来的左孩子返回
    public BinaryTreeNode setLChild(BinaryTreeNode newLChild){
        //保存原来的左孩子
        BinaryTreeNode oldLChild = this.lChild;
        //先断开当前节点的左孩子
        if (hasLChild()){
            lChild.disInheritence();
        }
        //设置新的左孩子为参数节点
        if (newLChild != null){
            //先把参数节点断开与原来父节点的关系
            newLChild.disInheritence();
            //把参数节点设置左孩子
            this.lChild = newLChild;
            //设置参数节点的父节点
            newLChild.parent = this;
            this.updataHeight();
            this.updataSize();
        }
        return oldLChild;
    }


    //返回右子树
    public BinaryTreeNode getrChild(){
        return rChild;
    }

    //与右孩子相关的操作
    //设置右孩子
    public BinaryTreeNode setRChild(BinaryTreeNode newRChild){
        //保存原来的右孩子
        BinaryTreeNode old = this.rChild;
        //断开右孩子结点
        if (hasRChild()){
            rChild.disInheritence();
        }
        //设置右孩子节点
        if (newRChild != null){
            //参数节点先断开原来父节点的关系
            newRChild.disInheritence();
            //设置当前节点的右孩子
            this.rChild = newRChild;
            newRChild.parent = this;
            this.updataHeight();
            this.updataSize();
        }
        return old;
    }

    //返回fu子树
    public BinaryTreeNode getParent(){
        return parent;
    }

    //断开与父节点的关系
    public void disInheritence(){
        //如果没有父节点
        if (!hasParent()){
            return;
        }

        //修改父节点的左孩子和右孩子为Null
        if (isLChild()){
            //当前节点是父节点的左孩子
            parent.lChild = null;
        }else if (isRChild()){
            parent.rChild = null;
        }
        //更新父节点的高度
        parent.updataHeight();
        //更新节点数
        parent.updataSize();
        //当前节点parent设置为Null
        parent = null;
    }

    //与节点个数相关的操作
    //返回以当前节点为根的二叉树的节点数
    public int getSize(){
        return size;
    }

    //更新当前节点以及祖先的节点数
    public void updataSize(){
        //当前节点本身
        size = 1;
        if (hasLChild()){
            //累加左子树的节点数
            size += getlChild().getSize();
        }
        if (hasRChild()){
            //累加you子树的节点数
            size += getrChild().getSize();
        }
        //递归更新祖先节点数
        if (hasParent()){
            getParent().updataSize();
        }

    }

    public Object getData(){
        return data;
    }
}
