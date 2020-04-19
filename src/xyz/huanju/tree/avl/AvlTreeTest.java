package xyz.huanju.tree.avl;

/**
 * @author HuanJu
 */
public class AvlTreeTest {
    public static void main(String[] args) {
//        int[] arr = {4,3,6,5,7,8};
//        int[] arr = {3, 4, 5, 6, 7, 8};

//        int[] arr = {10, 12, 8,9,7,6};
        int[] arr = {10, 11, 7, 6, 8, 9};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(arr[i]);
        }



        System.out.println("左子树高度: " + avlTree.leftHeight(4));
        System.out.println("右子树高度：" + avlTree.rightHeight(4));

    }

}
