package xyz.huanju.tree.arr;

/**
 * @author HuanJu
 */
public class ArrBinaryTreeTest {

    public static void main(String[] args) {
        int[] arr={5,3,7,2,4,6,8};
        //构造构造一颗顺序存储二叉树
        ArrBinaryTree tree=new ArrBinaryTree(arr);
        System.out.println("前序遍历");
        tree.preOrder();
    }
}
