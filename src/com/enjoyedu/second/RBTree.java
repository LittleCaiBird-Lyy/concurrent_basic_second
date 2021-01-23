package com.enjoyedu.second;

public class RBTree<T extends Comparable<T>> {
    private RBTNode<T> mroot;

    //红色是false
    private static final boolean RED = false;
    private static final boolean BLACK = true;

    public class RBTNode<T extends Comparable<T>>{
        //颜色
        boolean color;
        //关键字->值
        T key;
        //左节点
        RBTNode<T> leftNode;
        //右节点
        RBTNode<T> rightNode;
        //父节点
        RBTNode<T> parentNode;

        public RBTNode(boolean color,T key , RBTNode<T> leftNode , RBTNode<T> rightNode ,RBTNode<T> parentNode){
            this.color = color;
            this.key = key;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
            this.parentNode = parentNode;
        }

        public T getKey(){
            return key;
        }

        @Override
        public String toString(){
            return "" + key + (this.color == RED? "R" : "B");
        }

    }

    public RBTree(){
        mroot = null;
    }

    private RBTNode<T> parentOf(RBTNode<T> node){
        return node != null ? node.parentNode : null;
    }

    private boolean colorOf(RBTNode<T> node){
        return node != null ? node.color : BLACK;
    }

    private boolean isRed(RBTNode<T> node){
        return ((node != null) && (node.color == RED))?true:false;
    }

    private boolean isBlack(RBTNode<T> node){
        return !isRed(node);
    }

    private void setBlack(RBTNode<T> node){
        if (node != null){
            node.color = BLACK;
        }
    }

    private void setRed(RBTNode<T> node){
        if (node != null){
            node.color = RED;
        }
    }

    private void setParent(RBTNode<T> node ,RBTNode<T> parent){
        if (node != null){
            node.parentNode = parent;
        }
    }

    private void setColor(RBTNode<T> node ,boolean color){
        if (node != null){
            node.color = color;
        }
    }

    private RBTNode<T> search(RBTNode<T> x , T key){
        if (x == null){
            return null;
        }
        int i = key.compareTo(x.key);
        if (i<0){
            return search(x.leftNode,key);
        }else if(i > 0){
            return search(x.rightNode,key);
        }
        return x;
    }

    public RBTNode<T> search(T key){
        return search(mroot,key);
    }

    //非递归实现查找
    private RBTNode<T> iterativeSearch(RBTNode<T> node , T key){
        while (node != null){
            int i = key.compareTo(node.key);
            if (i>0){
                node = node.rightNode;
            }else if(i < 0){
                node = node.leftNode;
            }else {
                return node;
            }

        }
        return node;
    }

    public RBTNode<T> iterativeSearch(T key){
        return iterativeSearch(mroot,key);
    }

    //查找最小节点：返回tree为根结点的红黑树的最小结点。
    private RBTNode<T> minimum(RBTNode<T> node){
        if (node == null){
            return null;
        }
        while (node.leftNode != null){
            node = node.leftNode;
        }
        return node;
    }

    public T minimum(){
        RBTNode<T> minimum = minimum(mroot);
        if (minimum != null){
            return minimum.key;
        }
        return null;
    }
    //查找最大节点：返回tree为根结点的红黑树的最大结点。
    private RBTNode<T> maximun(RBTNode<T> node){
        if (node == null){
            return  null;
        }
        while (node.rightNode != null){
            node = node.rightNode;
        }
        return node;
    }

    public T maximum(){
        RBTNode<T> maximun = maximun(mroot);
        if (maximun != null){
            return maximun.key;
        }
        return null;
    }

    //找结点(x)的后继结点。即，查找"红黑树中数据值大于该结点"的"最小结点"。
    public RBTNode<T> successor(RBTNode<T> node){
      if (node.rightNode != null){
          return minimum(node.rightNode);
      }
      //没有左节点说明这个值是它的父节点，但是它有好多父节点，得找出它最小的父节点
      RBTNode<T> parentNode = node.parentNode;
      while ((parentNode != null) && (node == parentNode.rightNode)){
          node = parentNode;
          parentNode = parentNode.parentNode;
      }
      return parentNode;
    }

    //找结点(x)的前驱结点。即，查找"红黑树中数据值小于该结点"的"最大结点"。
    public RBTNode<T> predecessor(RBTNode<T> node){
        if (node.leftNode != null){
            return maximun(node.leftNode);
        }
        RBTNode<T> parentNode = node.parentNode;
        while ((parentNode != null) && (node == parentNode.leftNode)){
            node = parentNode;
            parentNode = parentNode.parentNode;
        }
        return parentNode;
    }

    /*
     * 对红黑树的节点(x)进行左旋转
     *
     * 左旋示意图(对节点x进行左旋)：
     *      13                               17
     *     /  \                             /  \
     *   nul  17                          13    27
     *       / \      --(左旋)-.          / \    / \
     *     nul 27                      nul nul nul nul
     *         / \
     *      nul  nul
     *
     *
     */
    //对红黑树的节点(x)进行左旋转
    private void leftRotate(RBTNode<T> x){
        RBTNode<T> y = x.rightNode;
        x.rightNode = y.leftNode;
        if (y.leftNode != null){
            y.leftNode.parentNode = x;
        }
        y.parentNode = x.parentNode;
        if (x.parentNode == null){
            this.mroot = y;
        }else {
            if (x.parentNode.leftNode == x){
                x.parentNode.leftNode = y;
            }else {
                x.parentNode.rightNode = y;
            }
        }
        y.leftNode = x;
        x.parentNode = y;
    }

    /*
     * 对红黑树的节点(8)进行右旋转
     *
     * 右旋示意图(对节点8进行右旋)：
     *            13                                 8
     *           /  \                             /     \
     *          8   nul                          1      13
     *         /  \      --(右旋)-              / \      / \
     *        1   nul                        nul nul nul  nul
     *       / \
     *     nul nul
     *
     */
    //对红黑树的节点(8)进行右旋转
    public void rightRotate(RBTNode<T> y){
        RBTNode<T> x = y.leftNode;
        x.rightNode = y.leftNode;
        if (x.rightNode != null){
            x.rightNode.parentNode = y;
        }
        x.parentNode = y.parentNode;
        if (y.parentNode == null){
            this.mroot = x;
        }else {
            if (y.parentNode.rightNode == y){
                y.parentNode.rightNode = y;
            }else {
                y.parentNode.leftNode = y;
            }
        }
        x = y.rightNode;
        y.parentNode = x;
    }

    /*
     * 红黑树插入修正函数
     *
     * 在向红黑树中插入节点之后(失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 插入的结点        // 对应《算法导论》中的z
     */
    //红黑树插入修正函数
    public void insertFixUp(RBTNode<T> node){
        RBTNode<T> parent ,gparent;
        //若父节点存在切父节点的颜色是红的
        while (((parent = parentOf(node)) != null) &&isRed(parent)){
            //祖父节点
            gparent = parentOf(parent);
            //若父节点是祖父节点的左节点
            if (parent == gparent.leftNode){
                //叔父节点
                RBTNode<T> uncle = gparent.rightNode;
                //如果叔父节点是红色
                if (uncle != null && isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                //如果叔父是黑的，且插入的当前节点为右节点(先左后右)
                if (parent.rightNode == node){
                    leftRotate(parent);
                    RBTNode<T> temp;
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                //叔父黑，插入节点为左边
                setBlack(parent);
                setRed(gparent);
                rightRotate(gparent);
            }else {//若“父节点”是“祖父节点的右孩子”
                RBTNode<T> uncle = gparent.leftNode;
                if (uncle != null & isRed(uncle)){
                    setBlack(uncle);
                    setBlack(parent);
                    setRed(gparent);
                    node = gparent;
                    continue;
                }
                // 情况6：叔叔是黑色，且当前节点是左孩子(两次旋转，先右后左)
                if (parent.leftNode == node){
                    RBTNode<T> temp;
                    rightRotate(parent);
                    temp = parent;
                    parent = node;
                    node = temp;
                }
                // 情况4：叔叔是黑色，且当前节点是右孩子。（一次左旋转）
                setBlack(parent);
                setRed(gparent);
                leftRotate(gparent);

            }

        }
        setBlack(this.mroot);
    }

    /*
     * 将结点插入到红黑树中
     *
     * 参数说明：
     *     node 插入的结点
     */
    //将结点插入到红黑树中
    private void insert(RBTNode<T> node){
        int cmp;
        RBTNode<T> y = null;
        RBTNode<T> x = this.mroot;
        // 1. 将红黑树当作一颗二叉查找树，将节点添加到二叉查找树中。
        while (x != null){
            cmp = node.key.compareTo(x.key);
            if (cmp < 0){
                x = x.leftNode;
            }if (cmp > 0){
                x = x.rightNode;
            }
        }
        node.parentNode = y;
        if (y != null){
             cmp = node.key.compareTo(y.key);
             if (cmp<0){
                 y.leftNode = x;
             }else {
                 y.rightNode = x;
             }
        }else {
            this.mroot = node;
        }
        //// 2. 设置节点的颜色为红色
        setRed(node);
        // 3. 将它重新修正为一颗二叉查找树
        insertFixUp(node);
    }

    /*
     * 新建结点(key)，并将其插入到红黑树中
     *
     * 参数说明：
     *     key 插入结点的键值
     */
    //新建
    public void insert(T key){
        RBTNode<T> node = new RBTNode<T>(BLACK,key,null,null,null);
        // 如果新建结点失败，则返回。
        if (node != null){
            insert(node);
        }
    }

    /*
     * 红黑树删除修正函数
     *
     * 在从红黑树中删除插入节点之后(红黑树失去平衡)，再调用该函数；
     * 目的是将它重新塑造成一颗红黑树。
     *
     * 参数说明：
     *     node 待修正的节点
     */
    //删除
    public void removeFixUp(RBTNode<T>  node,RBTNode<T> parent){
        RBTNode<T> other;
        //node为黑色的且不是根节点
        while ((node == null && isBlack(node)) || node != this.mroot){
            if (parent.leftNode == node){
                other = parent.rightNode;
                if (isRed(other)){
                    //如果叔父节点为红色
                    setBlack(other);
                    setRed(parent);
                    leftRotate(parent);
                    other = parent.rightNode;
                }       //叔父节点下的两个节点都为黑色
                if ((other.leftNode == null || isBlack(other.leftNode)
                        && (other.rightNode == null || isBlack(other.rightNode)))){
                    setBlack(other);
                    node = parent;
                    parent = parentOf(node);
                }else {
                    if (other.rightNode == null || isBlack(other.rightNode)){
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是红色，右孩子为黑色。
                        setBlack(other.leftNode);
                        setRed(other);
                        rightRotate(other);
                        other = parent.rightNode;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的右孩子是红色的，左孩子任意颜色。
                    setColor(other,colorOf(parent));
                    setBlack(other.rightNode);
                    setBlack(parent);
                    leftRotate(parent);
                    node = this.mroot;
                    break;
                }

            }else {
                other = parent.leftNode;
                // Case 1: x的兄弟w是红色的
                if (isRed(other)){
                    setBlack(other);;
                    setRed(parent);
                    rightRotate(parent);
                    other = parent.leftNode;
                }
                if (other.rightNode == null || isBlack(other.rightNode) &&
                        (other.leftNode == null || isBlack(other.leftNode))){
                    // Case 2: x的兄弟w是黑色，且w的俩个孩子也都是黑色的
                    setRed(other);
                    node = parent;
                    parent = parentOf(node);
                }else {
                    if (other.leftNode == null || isBlack(other.leftNode)){
                        // Case 3: x的兄弟w是黑色的，并且w的左孩子是黑色，右孩子为红色。
                        setBlack(other.rightNode);
                        setRed(other);
                        leftRotate(other);
                        other = parent.leftNode;
                    }
                    // Case 4: x的兄弟w是黑色的；并且w的左孩子是红色的，右孩子任意颜色。
                    setColor(other,colorOf(parent));
                    setBlack(parent);
                    setBlack(other.leftNode);
                    rightRotate(parent);
                    node = this.mroot;
                    break;
                }
            }
        }
        if (node != null){
            setBlack(node);
        }
    }

    /*
     * 删除结点(node)，并返回被删除的结点
     *
     * 参数说明：
     *     node 删除的结点
     */
    //删除结点(node)，并返回被删除的结点
    private void remove(RBTNode<T> node) {
        RBTNode<T> child, parent;
        boolean color;

        // 被删除节点的"左右孩子都不为空"的情况。
        if ( (node.leftNode!=null) && (node.rightNode!=null) ) {
            // 被删节点的后继节点。(称为"取代节点")
            // 用它来取代"被删节点"的位置，然后再将"被删节点"去掉。
            RBTNode<T> replace = node;

            // 获取后继节点
            replace = replace.rightNode;
            while (replace.leftNode != null)
                replace = replace.leftNode;

            // "node节点"不是根节点(只有根节点不存在父节点)
            if (parentOf(node)!=null) {
                if (parentOf(node).leftNode == node)
                    parentOf(node).leftNode = replace;
                else
                    parentOf(node).rightNode = replace;
            } else {
                // "node节点"是根节点，更新根节点。
                this.mroot = replace;
            }

            // child是"取代节点"的右孩子，也是需要"调整的节点"。
            // "取代节点"肯定不存在左孩子！因为它是一个后继节点。
            child = replace.rightNode;
            parent = parentOf(replace);
            // 保存"取代节点"的颜色
            color = colorOf(replace);

            // "被删除节点"是"它的后继节点的父节点"
            if (parent == node) {
                parent = replace;
            } else {
                // child不为空
                if (child!=null)
                    setParent(child, parent);
                parent.leftNode = child;

                replace.rightNode = node.rightNode;
                setParent(node.rightNode, replace);
            }

            replace.parentNode = node.parentNode;
            replace.color = node.color;
            replace.leftNode = node.leftNode;
            node.leftNode.parentNode = replace;

            if (color == BLACK)
                removeFixUp(child, parent);

            node = null;
            return ;
        }

        if (node.leftNode !=null) {
            child = node.leftNode;
        } else {
            child = node.rightNode;
        }

        parent = node.parentNode;
        // 保存"取代节点"的颜色
        color = node.color;

        if (child!=null)
            child.parentNode = parent;

        // "node节点"不是根节点
        if (parent!=null) {
            if (parent.leftNode == node)
                parent.leftNode = child;
            else
                parent.rightNode = child;
        } else {
            this.mroot = child;
        }

        if (color == BLACK)
            removeFixUp(child, parent);
        node = null;
    }
    /*
     * 删除结点(z)，并返回被删除的结点
     *
     * 参数说明：
     *     tree 红黑树的根结点
     *     z 删除的结点
     */
    //删除结点(z)，并返回被删除的结点
    public void  remove(T key){
        RBTNode<T> node;
        if ((node = search(mroot.key)) != null){
            remove(node);
        }
    }

    /*
     * 销毁红黑树
     */


}
