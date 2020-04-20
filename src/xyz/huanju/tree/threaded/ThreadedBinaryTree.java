package xyz.huanju.tree.threaded;

/**
 * （中序遍历）线索化二叉树
 *
 * @author HuanJu
 */
public class ThreadedBinaryTree {

    /**
     * 二叉树根节点
     */
    private Node root;

    /**
     * 当前节点的前驱节点
     */
    private Node pre;


    public void threadNodes(){
        threadNodes(this.root);
    }


    /**
     * 中序线索化节点
     *
     * @param node 需要线索化的节点
     */
    private void threadNodes(Node node) {
        //node为空，无法线索
        if (node==null){
            return;
        }
        /*
        1.线索化左节点
         */
        threadNodes(node.left);

        /*
        2. 线索化当前节点
        2.1 将当前节点的左空指针
        指向前驱节点

         */

        if (node.left==null){
            node.left=pre;
            node.leftType=1;
        }


        /*
        2.2 线索化pre前驱节点
        的后继节点
        即将前一个节点的后继节点
        设置为当前节点
         */
        if (pre!=null&&pre.right==null){
            pre.right=node;
            pre.rightType=1;
        }

        /*
        2.3 将当前节点设为
        前驱节点
        用于线索化右节点
         */
        pre=node;

        /*
        3.线索化右节点
         */
        threadNodes(node.right);
    }

    public void add(int value) {
        /*
        根节点为空
        直接添加到根节点
         */
        if (this.root == null) {
            this.root = new Node(null, value, null);
        }else {
            add(this.root,value);
        }
    }


    private void add(Node node,int value){
        if (value==node.value){
            System.out.println("节点已经存在");
        }else if (value<node.value){
            /*
            1.插入的值比节点的值小
            1.1
            左节点为空直接插入
            1.2
            左节点不为空
            递归插入
             */
            if (node.left==null){
                node.left=new Node(null,value,null);
            }else {
                add(node.left,value);
            }
        }else {
            /*
            2.插入的值比节点的值大
            2.1
            右节点为空直接插入
            2.2
            右节点不为空
            递归插入
             */
            if (node.right==null){
                node.right=new Node(null,value,null);
            }else {
                add(node.right,value);
            }
        }
    }





    /**
     * 节点
     */
    private static class Node {
        int value;
        Node left;
        Node right;

        /**
         * 如果leftType等于0，
         * 则表示指向的是左子树
         * 等于1则表示指向前驱节点
         */
        int leftType;
        /**
         * 如果rightType等于0，
         * 则表示指向的是右子树
         * 等于1则表示指向后继节点
         */
        int rightType;

        Node(Node left, int value, Node right) {
            this.left = left;
            this.value = value;
            this.right = right;
            this.leftType=0;
            this.rightType=0;
        }
    }
}
