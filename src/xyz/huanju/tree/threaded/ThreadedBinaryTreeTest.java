package xyz.huanju.tree.threaded;

/**
 * @author HuanJu
 * @date 2020/4/3 0:44
 */
public class ThreadedBinaryTreeTest {

    public static void main(String[] args) {
        ThreadedBinaryTree tree=new ThreadedBinaryTree();
        tree.add(5);
        tree.add(3);
        tree.add(7);
        tree.add(2);
        tree.add(4);
        tree.add(6);
        tree.add(8);

        //中序线索化
        tree.threadNodes();


        System.out.println();


    }

}
