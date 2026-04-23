class BookNode {
    String bookId, title, author, genre;
    boolean available;
    BookNode prev, next;

    BookNode(String bookId, String title, String author, String genre, boolean available) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.available = available;
    }
}

class LibraryDLL {
    BookNode head, tail;
    int count;

    boolean isEmpty() { return head == null; }

    void addAtBeginning(String bookId, String title, String author, String genre, boolean available) {
        BookNode node = new BookNode(bookId, title, author, genre, available);
        if (isEmpty()) { head = tail = node; }
        else { node.next = head; head.prev = node; head = node; }
        count++;
        System.out.println("Book '" + title + "' added at beginning.");
    }

    void addAtEnd(String bookId, String title, String author, String genre, boolean available) {
        BookNode node = new BookNode(bookId, title, author, genre, available);
        if (isEmpty()) { head = tail = node; }
        else { node.prev = tail; tail.next = node; tail = node; }
        count++;
        System.out.println("Book '" + title + "' added at end.");
    }

    void addAtPosition(int pos, String bookId, String title, String author, String genre, boolean available) {
        if (pos <= 1) { addAtBeginning(bookId, title, author, genre, available); return; }
        BookNode node = new BookNode(bookId, title, author, genre, available);
        BookNode curr = head;
        for (int i = 0; i < pos - 2; i++) {
            if (curr == null) { addAtEnd(bookId, title, author, genre, available); return; }
            curr = curr.next;
        }
        if (curr == null || curr.next == null) { addAtEnd(bookId, title, author, genre, available); return; }
        node.next = curr.next;
        node.prev = curr;
        curr.next.prev = node;
        curr.next = node;
        count++;
        System.out.println("Book '" + title + "' added at position " + pos + ".");
    }

    void removeById(String bookId) {
        BookNode curr = head;
        while (curr != null) {
            if (curr.bookId.equals(bookId)) {
                if (curr.prev != null) curr.prev.next = curr.next; else head = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev; else tail = curr.prev;
                curr.prev = curr.next = null;
                count--;
                System.out.println("Book '" + curr.title + "' removed.");
                return;
            }
            curr = curr.next;
        }
        System.out.println("Book ID '" + bookId + "' not found.");
    }

    void searchByTitle(String title) {
        boolean found = false;
        BookNode curr = head;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) { printBook(curr); found = true; }
            curr = curr.next;
        }
        if (!found) System.out.println("No book titled '" + title + "' found.");
    }

    void searchByAuthor(String author) {
        boolean found = false;
        BookNode curr = head;
        while (curr != null) {
            if (curr.author.equalsIgnoreCase(author)) { printBook(curr); found = true; }
            curr = curr.next;
        }
        if (!found) System.out.println("No books by author '" + author + "' found.");
    }

    void updateAvailability(String bookId, boolean status) {
        BookNode curr = head;
        while (curr != null) {
            if (curr.bookId.equals(bookId)) {
                curr.available = status;
                System.out.println("Availability updated for '" + curr.title + "': " + (status ? "Available" : "Not Available"));
                return;
            }
            curr = curr.next;
        }
        System.out.println("Book ID '" + bookId + "' not found.");
    }

    void displayForward() {
        if (isEmpty()) { System.out.println("Library is empty."); return; }
        System.out.println("\n--- Books (Forward) ---");
        BookNode curr = head;
        int idx = 1;
        while (curr != null) { System.out.print(idx + ". "); printBook(curr); curr = curr.next; idx++; }
        System.out.println();
    }

    void displayReverse() {
        if (isEmpty()) { System.out.println("Library is empty."); return; }
        System.out.println("\n--- Books (Reverse) ---");
        BookNode curr = tail;
        int idx = count;
        while (curr != null) { System.out.print(idx + ". "); printBook(curr); curr = curr.prev; idx--; }
        System.out.println();
    }

    void totalBooks() {
        System.out.println("Total books in library: " + count);
    }

    void printBook(BookNode node) {
        System.out.println("ID: " + node.bookId + " | Title: " + node.title + " | Author: " + node.author + " | Genre: " + node.genre + " | Status: " + (node.available ? "Available" : "Not Available"));
    }
}

public class LibraryManagement {
    public static void main(String[] args) {
        LibraryDLL lib = new LibraryDLL();

        lib.addAtEnd("B001", "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", true);
        lib.addAtEnd("B002", "1984", "George Orwell", "Dystopian", true);
        lib.addAtEnd("B003", "To Kill a Mockingbird", "Harper Lee", "Fiction", true);
        lib.addAtBeginning("B004", "Animal Farm", "George Orwell", "Political Satire", true);
        lib.addAtPosition(3, "B005", "Brave New World", "Aldous Huxley", "Dystopian", true);

        lib.displayForward();
        lib.displayReverse();

        lib.searchByAuthor("George Orwell");
        lib.searchByTitle("1984");

        lib.updateAvailability("B002", false);
        lib.removeById("B005");

        lib.displayForward();
        lib.totalBooks();
    }
}
