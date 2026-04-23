class Node {
    int rollNo;
    String name;
    int age;
    String grade;
    Node next;

    Node(int rollNo, String name, int age, String grade) {
        this.rollNo = rollNo;
        this.name = name;
        this.age = age;
        this.grade = grade;
        this.next = null;
    }
}

class StudentLinkedList {
    Node head;

    boolean isEmpty() {
        return head == null;
    }

    void addAtBeginning(int rollNo, String name, int age, String grade) {
        Node node = new Node(rollNo, name, age, grade);
        node.next = head;
        head = node;
        System.out.println("Student '" + name + "' added at beginning.");
    }

    void addAtEnd(int rollNo, String name, int age, String grade) {
        Node node = new Node(rollNo, name, age, grade);
        if (isEmpty()) {
            head = node;
        } else {
            Node curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = node;
        }
        System.out.println("Student '" + name + "' added at end.");
    }

    void addAtPosition(int pos, int rollNo, String name, int age, String grade) {
        if (pos <= 1) {
            addAtBeginning(rollNo, name, age, grade);
            return;
        }
        Node node = new Node(rollNo, name, age, grade);
        Node curr = head;
        for (int i = 0; i < pos - 2; i++) {
            if (curr == null) {
                addAtEnd(rollNo, name, age, grade);
                return;
            }
            curr = curr.next;
        }
        if (curr == null) {
            addAtEnd(rollNo, name, age, grade);
            return;
        }
        node.next = curr.next;
        curr.next = node;
        System.out.println("Student '" + name + "' added at position " + pos + ".");
    }

    void deleteByRollNo(int rollNo) {
        if (isEmpty()) {
            System.out.println("List is empty.");
            return;
        }
        if (head.rollNo == rollNo) {
            System.out.println("Student '" + head.name + "' deleted.");
            head = head.next;
            return;
        }
        Node curr = head;
        while (curr.next != null) {
            if (curr.next.rollNo == rollNo) {
                System.out.println("Student '" + curr.next.name + "' deleted.");
                curr.next = curr.next.next;
                return;
            }
            curr = curr.next;
        }
        System.out.println("Roll No " + rollNo + " not found.");
    }

    void searchByRollNo(int rollNo) {
        Node curr = head;
        while (curr != null) {
            if (curr.rollNo == rollNo) {
                System.out.println("Found -> Roll No: " + curr.rollNo + ", Name: " + curr.name + ", Age: " + curr.age + ", Grade: " + curr.grade);
                return;
            }
            curr = curr.next;
        }
        System.out.println("Roll No " + rollNo + " not found.");
    }

    void displayAll() {
        if (isEmpty()) {
            System.out.println("No student records found.");
            return;
        }
        System.out.println("\n--- Student Records ---");
        Node curr = head;
        int idx = 1;
        while (curr != null) {
            System.out.println(idx + ". Roll No: " + curr.rollNo + " | Name: " + curr.name + " | Age: " + curr.age + " | Grade: " + curr.grade);
            curr = curr.next;
            idx++;
        }
        System.out.println("-----------------------\n");
    }

    void updateGrade(int rollNo, String newGrade) {
        Node curr = head;
        while (curr != null) {
            if (curr.rollNo == rollNo) {
                System.out.println("Grade updated for '" + curr.name + "': " + curr.grade + " -> " + newGrade);
                curr.grade = newGrade;
                return;
            }
            curr = curr.next;
        }
        System.out.println("Roll No " + rollNo + " not found.");
    }
}

public class StudentRecord {
    public static void main(String[] args) {
        StudentLinkedList list = new StudentLinkedList();

        list.addAtEnd(101, "Alice", 20, "A");
        list.addAtEnd(102, "Bob", 21, "B");
        list.addAtEnd(103, "Charlie", 19, "C");
        list.addAtBeginning(100, "Zara", 22, "A+");
        list.addAtPosition(3, 105, "Diana", 20, "B+");

        list.displayAll();

        list.searchByRollNo(102);
        list.updateGrade(102, "A");
        list.deleteByRollNo(103);

        list.displayAll();
    }
}
