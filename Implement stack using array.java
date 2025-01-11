class MyStack {
    private int[] arr;
    private int top;

    public MyStack() {
        arr = new int[1000];
        top = -1;
    }

    public void push(int a) {
        top++;
        arr[top] = a;
    }

    public int pop() {
        if (top == -1) return -1;
        int x = arr[top];
        top--;
        return x;
    }
}
