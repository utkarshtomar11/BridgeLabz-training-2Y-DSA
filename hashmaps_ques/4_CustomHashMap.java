public class CustomHashMap {

    private static final int CAPACITY = 16;
    private Node[] buckets;

    static class Node {
        int key;
        int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public CustomHashMap() {
        buckets = new Node[CAPACITY];
    }

    private int getIndex(int key) {
        return Math.abs(key) % CAPACITY;
    }

    public void put(int key, int value) {
        int index = getIndex(key);
        Node head = buckets[index];

        while (head != null) {
            if (head.key == key) {
                head.value = value;
                return;
            }
            head = head.next;
        }

        Node newNode = new Node(key, value);
        newNode.next = buckets[index];
        buckets[index] = newNode;
    }

    public int get(int key) {
        int index = getIndex(key);
        Node head = buckets[index];

        while (head != null) {
            if (head.key == key) return head.value;
            head = head.next;
        }

        return -1;
    }

    public void remove(int key) {
        int index = getIndex(key);
        Node head = buckets[index];
        Node prev = null;

        while (head != null) {
            if (head.key == key) {
                if (prev == null) {
                    buckets[index] = head.next;
                } else {
                    prev.next = head.next;
                }
                return;
            }
            prev = head;
            head = head.next;
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();

        map.put(1, 100);
        map.put(2, 200);
        map.put(3, 300);

        System.out.println("get(1): " + map.get(1));
        System.out.println("get(2): " + map.get(2));
        System.out.println("get(3): " + map.get(3));

        map.put(2, 999);
        System.out.println("get(2) after update: " + map.get(2));

        map.remove(1);
        System.out.println("get(1) after remove: " + map.get(1));

        System.out.println("get(99) not found: " + map.get(99));
    }
}
