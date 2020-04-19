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
        //1. 线索化左节点
        threadNodes(node.left);
        //2. 线索化当前节点

        //2.1 将当前节点的左空指针指向前驱节点
        if (node.left==null){
            node.left=pre;
            node.leftType=1;
        }
        //2.2 将pre前驱节点的右空指针指向当前节点
        if (pre!=null&&pre.right==null){
            pre.right=node;
            pre.rightType=1;
        }
        //2.3 将当前节点设置为前驱节点
        pre=node;
        //3. 线索化右节点
        threadNodes(node.right);
    }

    public void add(int value) {
        if (this.root == null) {
            this.root = new Node(null, value, null);
            return;
        }
        Node parent = this.root;
        int vp = parent.value;
        //找到添加位置的父节点
        while (true) {
            if (vp == value) {
                System.out.println("节点已经存在");
                return;
            }
            if (value < vp) {
                Node left = parent.left;
                if (left == null) {
                    break;
                } else {
                    parent = parent.left;
                    vp = parent.value;
                }
            } else {
                Node right = parent.right;
                if (right == null) {
                    break;
                } else {
                    parent = parent.right;
                    vp = parent.value;
                }
            }
        }
        if (value<vp){
            parent.left= new Node(null,value,null);
        }else {
            parent.right=new Node(null,value,null);
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
         * 如果leftType等于0，则表示指向的是左子树
         * 如果等于1则表示指向前驱节点
         */
        int leftType;
        /**
         * 如果rightType等于0，则表示指向的是右子树
         * 如果等于1则表示指向后继节点
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
