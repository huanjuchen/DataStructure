package xyz.huanju.tree.arr;

/**
 * 顺序存储二叉树
 *
 * @author HuanJu
 */
public class ArrBinaryTree {

    /**
     *存储数据
     */
    private int[] arr;

    public ArrBinaryTree(int[] arr){
        this.arr=arr;
    }

    /**
     * 前序遍历
     */
    public void preOrder(){
        if (arr==null||arr.length==0){
            System.out.println("数组为空，不能作为二叉树前序遍历");
        }
        preOrder(0);
        System.out.println();
    }

    /**
     *
     * @param index 数据的下标
     */
    private void preOrder(int index){
        //输出当前元素
        System.out.print(arr[index]+"\t");
        //遍历左子树
        int leftIndex=index*2+1;
        if (leftIndex<arr.length){
            preOrder(leftIndex);
        }
        //遍历右子树
        int rightIndex=index*2+2;
        if (rightIndex<arr.length){
            preOrder(rightIndex);
        }
    }


}
