package xyz.huanju.tree.avl;

import xyz.huanju.tree.bin.BinaryTree;

/**
 * @author HuanJu
 */
public class AvlTree {

    /**
     * 根节点
     */
    private Node root;


    /**
     * 左旋转已node为根节点的树
     */
    private void leftRotate(Node node) {

        /*
        1. 创建一个新节点newNode，值为node的值
        2. 把新节点newNode的左子树
        设置成node节点的左子树
        3. 把新节点newNode的右子树
        设置成node节点的右子树的左子树
        node.right.left
         */
        Node newNode = new Node(null, node.value, null);
        newNode.left = node.left;
        newNode.right = node.right.left;
        /*
        4. 把当前节点node的值替换成右子节点的值
        5. 把当前节点node的右子树替换成右子树的右子树
         */
        node.value = node.right.value;
        node.right = node.right.right;
        /*
        6. 把当前节点node的左子树设置为新节点newNode
         */
        node.left = newNode;
    }


    /**
     * 右旋转
     */
    private void rightRotate(Node node) {
        Node newNode = new Node(null, node.value, null);
        newNode.right = node.right;
        newNode.left = node.left.right;
        node.value = node.left.value;
        node.left = node.left.left;
        node.right = newNode;
    }


    public int rightHeight(int value) {
        Node node = find(value);
        return rightHeight(node);
    }

    /**
     * 右子树的高度
     */
    private int rightHeight(Node node) {
        if (node == null || node.right == null) {
            return 0;
        } else {
            return height(node.right);
        }
    }

    public int leftHeight(int value) {
        Node node = find(value);
        return leftHeight(node);
    }

    /**
     * 左子树的高度
     */
    private int leftHeight(Node node) {
        if (node == null || node.left == null) {
            return 0;
        } else {
            return height(node.left);
        }
    }


    /**
     * 获取以node节点为根节点的树的高度
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(node.left == null ? 0 : height(node.left),
                node.right == null ? 0 : height(node.right)) + 1;
    }

    /**
     * 查找节点
     */
    public Node find(int value) {
        if (this.root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        return find(this.root, value);
    }

    private Node find(Node pn, int value) {
        if (pn.value == value) {
            return pn;
        }
        if (value < pn.value && pn.left != null) {
            return find(pn.left, value);
        }

        if (value > pn.value && pn.right != null) {
            return find(pn.right, value);
        }
        return null;
    }

    /**
     * 添加
     */
    public void add(int value) {
        if (this.root == null) {
            this.root = new Node(null, value, null);
            return;
        }
        add(this.root, value);
    }


    private void add(Node node, int value) {
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


        /*
        进行平衡插座
         */
        //如果右子树高度-左子树高度大于1，进行左旋转
        if (rightHeight(node) - leftHeight(node) > 1) {
            if (node.right != null && leftHeight(node.right) > rightHeight(node.right)) {
                rightRotate(node.right);
                leftRotate(node);
            } else {
                leftRotate(node);
            }
        }else if (leftHeight(node) - rightHeight(node) > 1) {
            /*
            如果node节点的左节点的右子树高度大于node节点的左节点的左子树高度
            进行左旋转
            再进行右旋转
             */
            if (node.left != null && rightHeight(node.left) > leftHeight(node.left)) {
                leftRotate(node.left);
                rightRotate(node);
            } else {
                /*
                否则直接进行右旋转
                 */
                rightRotate(node);
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

        Node(Node left, int value, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    '}';
        }
    }

}
