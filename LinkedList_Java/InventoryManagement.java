class ItemNode {
    String itemId, name;
    int quantity;
    double price;
    ItemNode next;

    ItemNode(String itemId, String name, int quantity, double price) {
        this.itemId = itemId;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}

class InventorySystem {
    ItemNode head;

    boolean isEmpty() { return head == null; }

    void addAtBeginning(String itemId, String name, int quantity, double price) {
        ItemNode node = new ItemNode(itemId, name, quantity, price);
        node.next = head;
        head = node;
        System.out.println("Item '" + name + "' added at beginning.");
    }

    void addAtEnd(String itemId, String name, int quantity, double price) {
        ItemNode node = new ItemNode(itemId, name, quantity, price);
        if (isEmpty()) { head = node; }
        else {
            ItemNode curr = head;
            while (curr.next != null) curr = curr.next;
            curr.next = node;
        }
        System.out.println("Item '" + name + "' added at end.");
    }

    void addAtPosition(int pos, String itemId, String name, int quantity, double price) {
        if (pos <= 1) { addAtBeginning(itemId, name, quantity, price); return; }
        ItemNode node = new ItemNode(itemId, name, quantity, price);
        ItemNode curr = head;
        for (int i = 0; i < pos - 2; i++) {
            if (curr == null) { addAtEnd(itemId, name, quantity, price); return; }
            curr = curr.next;
        }
        if (curr == null) { addAtEnd(itemId, name, quantity, price); return; }
        node.next = curr.next;
        curr.next = node;
        System.out.println("Item '" + name + "' added at position " + pos + ".");
    }

    void removeById(String itemId) {
        if (isEmpty()) { System.out.println("Inventory is empty."); return; }
        if (head.itemId.equals(itemId)) {
            System.out.println("Item '" + head.name + "' removed.");
            head = head.next; return;
        }
        ItemNode curr = head;
        while (curr.next != null) {
            if (curr.next.itemId.equals(itemId)) {
                System.out.println("Item '" + curr.next.name + "' removed.");
                curr.next = curr.next.next; return;
            }
            curr = curr.next;
        }
        System.out.println("Item ID '" + itemId + "' not found.");
    }

    void updateQuantity(String itemId, int newQty) {
        ItemNode curr = head;
        while (curr != null) {
            if (curr.itemId.equals(itemId)) {
                System.out.println("Quantity updated for '" + curr.name + "': " + curr.quantity + " -> " + newQty);
                curr.quantity = newQty; return;
            }
            curr = curr.next;
        }
        System.out.println("Item ID '" + itemId + "' not found.");
    }

    void search(String key) {
        boolean found = false;
        ItemNode curr = head;
        while (curr != null) {
            if (curr.itemId.equals(key) || curr.name.equalsIgnoreCase(key)) {
                System.out.println("Found -> ID: " + curr.itemId + " | Name: " + curr.name + " | Qty: " + curr.quantity + " | Price: " + curr.price);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) System.out.println("No item found for '" + key + "'.");
    }

    void totalInventoryValue() {
        double total = 0;
        ItemNode curr = head;
        while (curr != null) { total += curr.quantity * curr.price; curr = curr.next; }
        System.out.printf("\nTotal Inventory Value: %.2f%n", total);
    }

    ItemNode mergeSort(ItemNode h, String key) {
        if (h == null || h.next == null) return h;
        ItemNode mid = getMid(h);
        ItemNode right = mid.next;
        mid.next = null;
        ItemNode left = mergeSort(h, key);
        ItemNode rightSorted = mergeSort(right, key);
        return merge(left, rightSorted, key);
    }

    ItemNode getMid(ItemNode h) {
        ItemNode slow = h, fast = h;
        while (fast.next != null && fast.next.next != null) { slow = slow.next; fast = fast.next.next; }
        return slow;
    }

    ItemNode merge(ItemNode l, ItemNode r, String key) {
        ItemNode dummy = new ItemNode("", "", 0, 0);
        ItemNode curr = dummy;
        while (l != null && r != null) {
            boolean takeLeft = key.equals("price") ? l.price <= r.price : l.name.compareToIgnoreCase(r.name) <= 0;
            if (takeLeft) { curr.next = l; l = l.next; }
            else { curr.next = r; r = r.next; }
            curr = curr.next;
        }
        curr.next = (l != null) ? l : r;
        return dummy.next;
    }

    ItemNode reverseList(ItemNode h) {
        ItemNode prev = null, curr = h;
        while (curr != null) { ItemNode nxt = curr.next; curr.next = prev; prev = curr; curr = nxt; }
        return prev;
    }

    void sortBy(String key, boolean ascending) {
        head = mergeSort(head, key);
        if (!ascending) head = reverseList(head);
        System.out.println("Inventory sorted by " + key + " (" + (ascending ? "ascending" : "descending") + ").");
    }

    void displayAll() {
        if (isEmpty()) { System.out.println("Inventory is empty."); return; }
        System.out.println("\n--- Inventory ---");
        ItemNode curr = head;
        int idx = 1;
        while (curr != null) {
            System.out.println(idx + ". ID: " + curr.itemId + " | Name: " + curr.name + " | Qty: " + curr.quantity + " | Price: " + curr.price);
            curr = curr.next; idx++;
        }
        System.out.println("-----------------\n");
    }
}

public class InventoryManagement {
    public static void main(String[] args) {
        InventorySystem inv = new InventorySystem();

        inv.addAtEnd("I001", "Laptop", 10, 55000);
        inv.addAtEnd("I002", "Mouse", 50, 500);
        inv.addAtEnd("I003", "Keyboard", 30, 1200);
        inv.addAtBeginning("I004", "Monitor", 15, 12000);
        inv.addAtPosition(3, "I005", "Headphones", 20, 3000);

        inv.displayAll();

        inv.search("I002");
        inv.search("Laptop");
        inv.updateQuantity("I001", 8);
        inv.removeById("I005");
        inv.totalInventoryValue();

        inv.sortBy("price", true);
        inv.displayAll();

        inv.sortBy("name", false);
        inv.displayAll();
    }
}
