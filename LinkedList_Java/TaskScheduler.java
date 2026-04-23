class TaskNode {
    String taskId, name, priority, dueDate;
    TaskNode next;

    TaskNode(String taskId, String name, String priority, String dueDate) {
        this.taskId = taskId;
        this.name = name;
        this.priority = priority;
        this.dueDate = dueDate;
    }
}

class CircularTaskScheduler {
    TaskNode head;
    TaskNode current;
    int size;

    boolean isEmpty() {
        return head == null;
    }

    TaskNode getTail() {
        if (isEmpty()) return null;
        TaskNode curr = head;
        while (curr.next != head) curr = curr.next;
        return curr;
    }

    void addAtBeginning(String taskId, String name, String priority, String dueDate) {
        TaskNode node = new TaskNode(taskId, name, priority, dueDate);
        if (isEmpty()) {
            head = node;
            node.next = head;
        } else {
            TaskNode tail = getTail();
            node.next = head;
            head = node;
            tail.next = head;
        }
        size++;
        System.out.println("Task '" + name + "' added at beginning.");
    }

    void addAtEnd(String taskId, String name, String priority, String dueDate) {
        TaskNode node = new TaskNode(taskId, name, priority, dueDate);
        if (isEmpty()) {
            head = node;
            node.next = head;
            current = head;
        } else {
            TaskNode tail = getTail();
            tail.next = node;
            node.next = head;
        }
        size++;
        System.out.println("Task '" + name + "' added at end.");
    }

    void addAtPosition(int pos, String taskId, String name, String priority, String dueDate) {
        if (pos <= 1) {
            addAtBeginning(taskId, name, priority, dueDate);
            return;
        }
        TaskNode node = new TaskNode(taskId, name, priority, dueDate);
        TaskNode curr = head;
        for (int i = 0; i < pos - 2; i++) {
            curr = curr.next;
            if (curr == head) break;
        }
        node.next = curr.next;
        curr.next = node;
        size++;
        System.out.println("Task '" + name + "' added at position " + pos + ".");
    }

    void removeById(String taskId) {
        if (isEmpty()) { System.out.println("Scheduler is empty."); return; }
        if (head.taskId.equals(taskId)) {
            if (head.next == head) {
                System.out.println("Task '" + head.name + "' removed.");
                head = null; current = null; size--;
                return;
            }
            TaskNode tail = getTail();
            System.out.println("Task '" + head.name + "' removed.");
            head = head.next;
            tail.next = head;
            size--;
            return;
        }
        TaskNode curr = head;
        while (curr.next != head) {
            if (curr.next.taskId.equals(taskId)) {
                System.out.println("Task '" + curr.next.name + "' removed.");
                curr.next = curr.next.next;
                size--;
                return;
            }
            curr = curr.next;
        }
        System.out.println("Task ID '" + taskId + "' not found.");
    }

    void viewCurrentAndMove() {
        if (isEmpty()) { System.out.println("No tasks scheduled."); return; }
        if (current == null) current = head;
        System.out.println("Current Task -> ID: " + current.taskId + " | Name: " + current.name + " | Priority: " + current.priority + " | Due: " + current.dueDate);
        current = current.next;
        System.out.println("Moved to next task: '" + current.name + "'");
    }

    void displayAll() {
        if (isEmpty()) { System.out.println("No tasks."); return; }
        System.out.println("\n--- Task Scheduler (Circular) ---");
        TaskNode curr = head;
        int idx = 1;
        do {
            System.out.println(idx + ". ID: " + curr.taskId + " | Name: " + curr.name + " | Priority: " + curr.priority + " | Due: " + curr.dueDate);
            curr = curr.next;
            idx++;
        } while (curr != head);
        System.out.println("[Circular: last task points back to '" + head.name + "']\n");
    }

    void searchByPriority(String priority) {
        if (isEmpty()) { System.out.println("Scheduler is empty."); return; }
        boolean found = false;
        TaskNode curr = head;
        do {
            if (curr.priority.equalsIgnoreCase(priority)) {
                System.out.println("Found -> ID: " + curr.taskId + " | Name: " + curr.name + " | Priority: " + curr.priority + " | Due: " + curr.dueDate);
                found = true;
            }
            curr = curr.next;
        } while (curr != head);
        if (!found) System.out.println("No task with priority '" + priority + "' found.");
    }
}

public class TaskScheduler {
    public static void main(String[] args) {
        CircularTaskScheduler scheduler = new CircularTaskScheduler();

        scheduler.addAtEnd("T001", "Design UI", "High", "2024-12-01");
        scheduler.addAtEnd("T002", "Write Tests", "Medium", "2024-12-05");
        scheduler.addAtEnd("T003", "Deploy Server", "High", "2024-12-10");
        scheduler.addAtBeginning("T004", "Fix Bug", "Critical", "2024-11-30");
        scheduler.addAtPosition(3, "T005", "Code Review", "Low", "2024-12-08");

        scheduler.displayAll();

        scheduler.viewCurrentAndMove();
        scheduler.viewCurrentAndMove();

        scheduler.searchByPriority("High");
        scheduler.removeById("T002");

        scheduler.displayAll();
    }
}
