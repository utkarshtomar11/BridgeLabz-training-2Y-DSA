import java.util.ArrayDeque;
import java.util.Deque;

public class QueueUsingStacks {

    static Deque<Integer> s1 = new ArrayDeque<>();
    static Deque<Integer> s2 = new ArrayDeque<>();

    public static void enqueue(int val) {
        s1.push(val);
    }

    public static int dequeue() {
        if (s1.isEmpty() && s2.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.pop();
    }

    public static int peek() {
        if (s1.isEmpty() && s2.isEmpty()) {
            System.out.println("Queue is empty");
            return -1;
        }
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
        return s2.peek();
    }

    public static void main(String[] args) {
        enqueue(10);
        enqueue(20);
        enqueue(30);

        System.out.println("Dequeue: " + dequeue());
        System.out.println("Peek: " + peek());
        System.out.println("Dequeue: " + dequeue());

        enqueue(40);
        System.out.println("Dequeue: " + dequeue());
        System.out.println("Dequeue: " + dequeue());
    }
}
