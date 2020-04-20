package xyz.huanju.queue;

/**
 * 数组模拟环形队列
 */
public class CircleArrayQueue {
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
        front = 0;
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
        /*
        插入元素
        并将队尾标志后移取模
         */
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
        /*
        front是指向队列的第一元素
        1. 现将front对应的值
        保存到一个临时变量
        2. 将front后移，并取模
        3. 将临时变量返回
         */

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
