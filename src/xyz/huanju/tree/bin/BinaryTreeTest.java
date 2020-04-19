package xyz.huanju.tree.bin;

/**
 * @author HuanJu
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree=new BinaryTree();
        binaryTree.add(5);
        binaryTree.add(3);
        binaryTree.add(7);
        binaryTree.add(2);
        binaryTree.add(4);
        binaryTree.add(6);
        binaryTree.add(8);

//        System.out.println("前序遍历");
//        binaryTree.preOrder();
//        System.out.println("中序遍历");
//        binaryTree.midOrder();
//        System.out.println("后序遍历");
//        binaryTree.postOder();

        System.out.println("删除前");
        binaryTree.midOrder();

        binaryTree.del(7);


        System.out.println("删除后");
        binaryTree.midOrder();






    }

}
