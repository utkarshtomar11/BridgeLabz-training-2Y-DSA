class MovieNode {
    String title, director;
    int year;
    double rating;
    MovieNode prev, next;

    MovieNode(String title, String director, int year, double rating) {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }
}

class MovieDLL {
    MovieNode head, tail;

    boolean isEmpty() {
        return head == null;
    }

    void addAtBeginning(String title, String director, int year, double rating) {
        MovieNode node = new MovieNode(title, director, year, rating);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        System.out.println("Movie '" + title + "' added at beginning.");
    }

    void addAtEnd(String title, String director, int year, double rating) {
        MovieNode node = new MovieNode(title, director, year, rating);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
        }
        System.out.println("Movie '" + title + "' added at end.");
    }

    void addAtPosition(int pos, String title, String director, int year, double rating) {
        if (pos <= 1) {
            addAtBeginning(title, director, year, rating);
            return;
        }
        MovieNode node = new MovieNode(title, director, year, rating);
        MovieNode curr = head;
        for (int i = 0; i < pos - 2; i++) {
            if (curr == null) {
                addAtEnd(title, director, year, rating);
                return;
            }
            curr = curr.next;
        }
        if (curr == null || curr.next == null) {
            addAtEnd(title, director, year, rating);
            return;
        }
        node.next = curr.next;
        node.prev = curr;
        curr.next.prev = node;
        curr.next = node;
        System.out.println("Movie '" + title + "' added at position " + pos + ".");
    }

    void removeByTitle(String title) {
        MovieNode curr = head;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) {
                if (curr.prev != null) curr.prev.next = curr.next;
                else head = curr.next;
                if (curr.next != null) curr.next.prev = curr.prev;
                else tail = curr.prev;
                curr.prev = curr.next = null;
                System.out.println("Movie '" + title + "' removed.");
                return;
            }
            curr = curr.next;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    void searchByDirector(String director) {
        boolean found = false;
        MovieNode curr = head;
        while (curr != null) {
            if (curr.director.equalsIgnoreCase(director)) {
                printMovie(curr);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) System.out.println("No movies by director '" + director + "' found.");
    }

    void searchByRating(double minRating) {
        boolean found = false;
        MovieNode curr = head;
        while (curr != null) {
            if (curr.rating >= minRating) {
                printMovie(curr);
                found = true;
            }
            curr = curr.next;
        }
        if (!found) System.out.println("No movies with rating >= " + minRating + " found.");
    }

    void displayForward() {
        if (isEmpty()) { System.out.println("No movies."); return; }
        System.out.println("\n--- Movies (Forward) ---");
        MovieNode curr = head;
        while (curr != null) { printMovie(curr); curr = curr.next; }
        System.out.println("------------------------\n");
    }

    void displayReverse() {
        if (isEmpty()) { System.out.println("No movies."); return; }
        System.out.println("\n--- Movies (Reverse) ---");
        MovieNode curr = tail;
        while (curr != null) { printMovie(curr); curr = curr.prev; }
        System.out.println("------------------------\n");
    }

    void updateRating(String title, double newRating) {
        MovieNode curr = head;
        while (curr != null) {
            if (curr.title.equalsIgnoreCase(title)) {
                System.out.println("Rating updated for '" + curr.title + "': " + curr.rating + " -> " + newRating);
                curr.rating = newRating;
                return;
            }
            curr = curr.next;
        }
        System.out.println("Movie '" + title + "' not found.");
    }

    void printMovie(MovieNode node) {
        System.out.println("Title: " + node.title + " | Director: " + node.director + " | Year: " + node.year + " | Rating: " + node.rating);
    }
}

public class MovieManagement {
    public static void main(String[] args) {
        MovieDLL dll = new MovieDLL();

        dll.addAtEnd("Inception", "Christopher Nolan", 2010, 8.8);
        dll.addAtEnd("The Dark Knight", "Christopher Nolan", 2008, 9.0);
        dll.addAtEnd("Interstellar", "Christopher Nolan", 2014, 8.6);
        dll.addAtBeginning("Avengers", "Russo Brothers", 2019, 8.4);
        dll.addAtPosition(3, "Dunkirk", "Christopher Nolan", 2017, 7.9);

        dll.displayForward();
        dll.displayReverse();

        dll.searchByDirector("Christopher Nolan");
        dll.searchByRating(8.7);
        dll.updateRating("Inception", 9.1);
        dll.removeByTitle("Dunkirk");

        dll.displayForward();
    }
}
