import edu.princeton.cs.algs4.Stack;

public class QueueUsingTwoStacks {
    private final Stack<Integer> left;
    private final Stack<Integer> right;

    public QueueUsingTwoStacks() {
        left = new Stack<>();
        right = new Stack<>();
    }

    public void enqueue(int val) {
        right.push(val);
    }

    public int dequeue() {
        if (left.isEmpty())
            shift();

        return left.pop();
    }

    private void shift() {
        while (!right.isEmpty())
            left.push(right.pop());
    }

    public int front() {
        if (left.isEmpty())
            shift();

        return left.peek();
    }
}
