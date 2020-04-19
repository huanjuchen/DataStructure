package xyz.huanju.tree.avl;

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
        2. 把新节点newNode的左子树设置成node节点的左子树
        3. 把新节点newNode的右子树设置成node节点的右子树的左子树node.right.left
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


    private void add(Node parent, int value) {
        if (value == parent.value) {
            System.out.println("节点已经存在");
            return;
        } else if (value < parent.value) {
            if (parent.left == null) {
                parent.left = new Node(null, value, null);
            } else {
                add(parent.left, value);
            }
        } else {
            if (parent.right == null) {
                parent.right = new Node(null, value, null);
            } else {
                add(parent.right, value);
            }
        }
        //如果右子树高度-左子树高度大于1，进行左旋转
        if (rightHeight(parent) - leftHeight(parent) > 1) {
            if (parent.right != null && leftHeight(parent.right) > rightHeight(parent.right)) {
                rightRotate(parent.right);
                leftRotate(parent);
            } else {
                leftRotate(parent);
            }
        }else if (leftHeight(parent) - rightHeight(parent) > 1) {
            /*
            如果parent节点的左节点的右子树高度大于parent节点的左节点的左子树高度
            进行左旋转
            再进行右旋转
             */
            if (parent.left != null && rightHeight(parent.left) > leftHeight(parent.left)) {
                leftRotate(parent.left);
                rightRotate(parent);
            } else {
                /*
                否则直接进行右旋转
                 */
                rightRotate(parent);
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
