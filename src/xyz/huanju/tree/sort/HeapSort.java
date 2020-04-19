package xyz.huanju.tree.sort;

/**
 * 堆排序
 * 升序排列 （大顶堆）
 *
 * @author HuanJu
 * @date 2020/4/3 21:41
 */
public class HeapSort {


    public static void heapSort(int[] arr) {
        System.out.println("堆排序");
        /*
        1. 将无序的数组构建成一个堆
        升序用大顶堆，降序用小顶堆
         */
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            modifyHead(arr,i,arr.length);
        }


        /*
        2. 将堆顶元素与末尾元素交换，将最大元素沉到末端
        3. 重新调整结构，使其满足堆定义
        然后继续交换堆顶元素与当前末尾元素
        反复执行调整+交换步骤，直到整个序列有序
         */
        int temp=0;
        for (int j=arr.length-1;j>0;j--){
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            //调整
            modifyHead(arr,0,j);
        }
    }


    /**
     * 调整为一个大顶堆
     *
     * @param arr    数组
     * @param i      非叶子节点在数组中的索引
     * @param length 对多个个元素进行调整
     */
    public static void modifyHead(int[] arr, int i, int length) {
        /*
        保存当前值
         */
        int temp = arr[i];
        //k指向i节点的左节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            /*
            如果左节点小于右节点
            则将k指向右节点
             */
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                k++;
            }
            /*
            如果子节点大于父节点
            则将较大值赋给当前节点
             */
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        //循环结束后，将以i为父节点的数的最大值放在顶部
        arr[i] = temp;
    }
}
