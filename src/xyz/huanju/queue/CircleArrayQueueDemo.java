package xyz.huanju.queue;

import java.util.Scanner;

/**
 * @author HuanJu
 */
public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //创建一个队列 可用长度为3
        CircleArrayQueue queue = new CircleArrayQueue(4);
        //接收用户输入
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            //接收一个字符
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.show();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.add(value);
                    break;
                //取出数据
                case 'g':
                    try {
                        int res = queue.get();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                //查看队列头的数据
                case 'h':
                    try {
                        int res = queue.peek();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                //退出
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出~~");
    }
}


/**
 * 数组模拟环形队列
 */
class CircleArrayQueue {
    /**
     * 队列的最大容量
     */
    private int maxSize;
    /**
     * 队列头: 指向队列头元素
     */
    private int front;
    /**
     * 队列尾: 指向队列尾元素的后一个位置
     */
    private int rear;
    /**
     * 数据容器
     */
    private int[] arr;

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        //指向队列头元素
        front = 0;
        //指向队列尾元素的后一个位置
        rear = 0;
    }

    /**
     * 判断队列是否已满
     */
    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    /**
     * 判断是否为空
     */
    public boolean isEmpty() {
        return front == rear;
    }

    /**
     * 添加数据
     */
    public void add(int element) {
        if (isFull()) {
            System.out.println("队列已满...");
            return;
        }
        //直接将元素插入
        arr[rear] = element;
        rear = (rear + 1) % maxSize;
    }

    /**
     * 取数据
     */
    public int get() {
        if (isEmpty()) {
            throw new RuntimeException("队列空,不能取数据");
        }
        //front是指向队列的第一个元素
        //1. 现将front对应的值保存到一个临时变量
        //2. 将front后移，考虑取模
        //3. 将临时变量返回
        int temp = arr[front];
        front = (front + 1) % maxSize;
        return temp;
    }

    /**
     * 遍历队列
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("队列空，没有数据");
        }

        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }
    }

    /**
     * 队列有效数据的个数
     */
    public int size() {
        return (rear + maxSize - front) % maxSize;
    }

    /**
     * 队头元素
     */
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("队列空,不能取数据");
        }
        return arr[front];
    }

}
