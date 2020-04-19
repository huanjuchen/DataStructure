package xyz.huanju.tree.bin;

/**
 * @author HuanJu
 */
public class BinaryTree {

    private Node root;


    /**
     * 添加
     */
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
        if (value < vp) {
            parent.left = new Node(null, value, null);
        } else {
            parent.right = new Node(null, value, null);
        }
    }


    /**
     * 删除节点
     * 情况一：删除的节点是叶子节点
     * 情况二：删除的节点有两个子节点
     * 情况三：删除的节点有一个子节点
     *
     */
    public void del(int value) {
        if (this.root == null) {
            return;
        }
        /*
        1. 查找需要删除的节点targetNode
        没有找到则退出
         */
        Node targetNode = find(value);
        if (targetNode == null) {
            return;
        }
        /*
        2. 查找targetNode的父节点
         */
        Node parentNode = findParent(value);
        /*
        删除的节点是叶子节点
        3.判断删除的节点targetNode是否是叶子节点
        如果是就直接删除
        3.1 判断targetNode是左子节点还是右子节点
        3.2 将父节点的左子节点/右子节点置空
         */
        if (targetNode.left == null && targetNode.right == null) {
            if (parentNode != null && parentNode.left.value == targetNode.value) {
                //左子节点
                parentNode.left = null;
            } else if (parentNode != null && parentNode.right.value == targetNode.value) {
                parentNode.right = null;

            }
        } else if (targetNode.left != null && targetNode.right != null) {
            /*
            4. 删除的节点targetNode有两个子节点
             */
            int minVal=delRightTreeMin(targetNode.right);
            targetNode.value=minVal;


        } else {
            /*
             5. 删除的节点targetNode只有一个子节点
             5.1 targetNode有左子节点
             5.1.1 要删除的是根节点
             5.1.2 删除的节点targetNode 是parentNode的左子节点
             5.1.3 删除的节点targetNode 是parentNode的右子节点
             */
            if (targetNode.left != null) {
                if (parentNode == null) {
                    this.root = targetNode.left;
                } else if (parentNode.left.value == targetNode.value) {
                    parentNode.left = targetNode.left;
                } else {
                    parentNode.right = targetNode.left;
                }
            } else {
                /*
                5.2 targetNode有右子节点
                 */
                if (parentNode == null) {
                    this.root=targetNode.right;
                } else if (parentNode.left.value == value) {
                    parentNode.left = targetNode.right;
                } else {
                    parentNode.right = targetNode.right;
                }

            }
        }
    }

    /**
     * 查找以node为根节点的二叉排序树的最小节点的值
     * 并删除node
     */
    public int delRightTreeMin(Node node){
        Node target=node;
        while (target.left!=null){
            target=target.left;
        }

        del(target.value);
        return target.value;

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
     * 查找父节点
     */
    public Node findParent(int value) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        //没有父节点直接返回
        if (root.value == value) {
            return null;
        }
        return findParent(this.root, value);
    }

    private Node findParent(Node pn, int value) {
        //当前节点就是父节点
        if ((pn.left != null && pn.left.value == value) ||
                (pn.right != null && pn.right.value == value)) {
            return pn;
        }
        if (pn.left != null && value < pn.value) {
            return findParent(pn.left, value);
        }
        if (pn.right != null && value > pn.value) {
            return findParent(pn.right, value);
        }
        return null;
    }


    /**
     * 前序遍历
     */
    public void preOrder() {
        if (this.root != null) {
            preOrder(this.root);
            System.out.println();
        } else {
            System.out.println("二叉树为空");
        }
    }

    private void preOrder(Node node) {
        System.out.print(node.value + "\t");
        if (node.left != null) {
            preOrder(node.left);
        }
        if (node.right != null) {
            preOrder(node.right);
        }
    }

    /**
     * 中序遍历
     */
    public void midOrder() {
        if (this.root != null) {
            midOrder(this.root);
            System.out.println();
        } else {
            System.out.println("二叉树为空");
        }
    }

    private void midOrder(Node node) {
        if (node.left != null) {
            midOrder(node.left);
        }
        System.out.print(node.value + "\t");
        if (node.right != null) {
            midOrder(node.right);
        }
    }

    public void postOder() {
        if (this.root != null) {
            postOrder(this.root);
            System.out.println();
        } else {
            System.out.println("二叉树为空");
        }
    }

    private void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }
        if (node.right != null) {
            postOrder(node.right);
        }
        System.out.print(node.value + "\t");

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
