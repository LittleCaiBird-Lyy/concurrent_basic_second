package com.enjoyedu.second;

public class GraphCover extends Graph {
    //遍历标志，防止死环遍历
    private int[] visit = new int[size];

    /**
     * 深度优先遍历
     * 一条路走到黑,不撞南墙不回头
     * 对每一个可能的分支路径深入到不能再深入为止
     */
    public void DeepFirst(int start){
        //该节点已被处理
        visit[start]  = 1;
        //输出该节点
        System.out.println("该节点为 ： " + this.nodes[start]);
        //遍历输出
        for (int i = 0; i < this.size; i++) {
            if (this.edges[start][i] == 1 && visit[i] == 0){
                //邻接点
                DeepFirst(i);
            }
        }

    }

    /**
     * 广度优先遍历
     * 广度优先搜索遍历图的过程中以v 为起始点，由近至远，
     * 依次访问和v 有路径相通且路径长度为1,2,…的顶点
     * 第一批节点的邻接点，?
     */
    private int[] queue= new int[size];
    public void BreadthFirst(int front,int tail){
        int last = tail;
        for (int i = front; i <= tail; i++) {
            //开始遍历的节点
            int node = queue[i];
            System.out.println("节点的值 ： " + this.nodes[node]);
            for (int j = 0; j <this.size; j++) {
                if (this.edges[node][j] == 1 && visit[j] == 0){
                    visit[j]=1;
                    queue[++last] = j;
                }
            }
        }
        //遍历下一批几点
        if (last > tail){
            BreadthFirst(tail+1,last);
        }
    }

    public void BreadthFirst(int start){
        queue[start] = 0;
        //标记已扫过的
        visit[start] =1;
        BreadthFirst(0,0);
    }

    public static void main(String[] args) {
        GraphCover graph = new GraphCover();

        graph.BreadthFirst(0);
    }
}
