import java.util.ArrayDeque;
import java.util.Deque;

public class SortStackRecursion {

    public static void sortedInsert(Deque<Integer> stack, int val) {
        if (stack.isEmpty() || stack.peek() <= val) {
            stack.push(val);
            return;
        }
        int top = stack.pop();
        sortedInsert(stack, val);
        stack.push(top);
    }

    public static void sortStack(Deque<Integer> stack) {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        sortStack(stack);
        sortedInsert(stack, top);
    }

    public static void main(String[] args) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(5);

        System.out.println("Before sort: " + stack);
        sortStack(stack);
        System.out.println("After sort:  " + stack);
    }
}
