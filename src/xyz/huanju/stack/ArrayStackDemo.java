package xyz.huanju.stack;

/**
 * @author HuanJu
 * @date 2020/2/20 23:13
 */
public class ArrayStackDemo {

    public static void main(String[] args) {
        //构造一个栈
        ArrayStack arrayStack=new ArrayStack(5);
        //
        System.out.println("入栈");
        for (int i = 0; i < 5; i++) {
            arrayStack.push(i*10);
            arrayStack.show();
            System.out.println("---------");
        }

        System.out.println("出栈");
        for (int i = 0; i < 5; i++) {
            arrayStack.pop();
            arrayStack.show();
            System.out.println("---------");
        }




    }

}

/**
 * 基于数组的栈
 */
class ArrayStack {
    /**
     * 栈的大小
     */
    private int maxSize;
    /**
     * 数组
     */
    private int[] stack;
    /**
     * 栈顶
     */
    private int top = -1;

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 栈满
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 栈空
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈 pop
     */
    public void push(int value) {
        if (isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     */
    public int pop() {
        if (isEmpty()) {
            throw new RuntimeException("栈空，没有数据...");
        }
        int temp = stack[top];
        top--;
        return temp;
    }

    /**
     * 遍历栈
     */
    public void show(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.printf("stack[%d]=%d\n",i,stack[i]);
        }

    }


}
