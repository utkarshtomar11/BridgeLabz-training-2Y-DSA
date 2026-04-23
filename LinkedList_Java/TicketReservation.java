import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class TicketNode {
    String ticketId, customerName, movieName, seatNumber, bookingTime;
    TicketNode next;

    TicketNode(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        this.ticketId = ticketId;
        this.customerName = customerName;
        this.movieName = movieName;
        this.seatNumber = seatNumber;
        this.bookingTime = bookingTime != null ? bookingTime : LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}

class TicketReservationSystem {
    TicketNode head;
    int size;

    boolean isEmpty() { return head == null; }

    TicketNode getTail() {
        if (isEmpty()) return null;
        TicketNode curr = head;
        while (curr.next != head) curr = curr.next;
        return curr;
    }

    void addTicket(String ticketId, String customerName, String movieName, String seatNumber, String bookingTime) {
        TicketNode node = new TicketNode(ticketId, customerName, movieName, seatNumber, bookingTime);
        if (isEmpty()) { head = node; node.next = head; }
        else { TicketNode tail = getTail(); tail.next = node; node.next = head; }
        size++;
        System.out.println("Ticket booked! ID: " + ticketId + " | Customer: " + customerName + " | Movie: " + movieName + " | Seat: " + seatNumber);
    }

    void removeTicket(String ticketId) {
        if (isEmpty()) { System.out.println("No tickets to cancel."); return; }
        if (head.ticketId.equals(ticketId)) {
            if (head.next == head) {
                System.out.println("Ticket " + ticketId + " cancelled for '" + head.customerName + "'.");
                head = null; size--; return;
            }
            TicketNode tail = getTail();
            System.out.println("Ticket " + ticketId + " cancelled for '" + head.customerName + "'.");
            head = head.next;
            tail.next = head;
            size--;
            return;
        }
        TicketNode curr = head;
        while (curr.next != head) {
            if (curr.next.ticketId.equals(ticketId)) {
                System.out.println("Ticket " + ticketId + " cancelled for '" + curr.next.customerName + "'.");
                curr.next = curr.next.next;
                size--;
                return;
            }
            curr = curr.next;
        }
        System.out.println("Ticket ID '" + ticketId + "' not found.");
    }

    void displayTickets() {
        if (isEmpty()) { System.out.println("No tickets booked."); return; }
        System.out.println("\n" + "=".repeat(75));
        System.out.printf("%-10s %-18s %-20s %-8s %s%n", "TID", "Customer", "Movie", "Seat", "Time");
        System.out.println("-".repeat(75));
        TicketNode curr = head;
        do {
            System.out.printf("%-10s %-18s %-20s %-8s %s%n", curr.ticketId, curr.customerName, curr.movieName, curr.seatNumber, curr.bookingTime);
            curr = curr.next;
        } while (curr != head);
        System.out.println("=".repeat(75));
        System.out.println("[Circular: last ticket points back to '" + head.ticketId + "']\n");
    }

    void searchByCustomer(String customerName) {
        if (isEmpty()) { System.out.println("No tickets available."); return; }
        boolean found = false;
        TicketNode curr = head;
        do {
            if (curr.customerName.equalsIgnoreCase(customerName)) { printTicket(curr); found = true; }
            curr = curr.next;
        } while (curr != head);
        if (!found) System.out.println("No tickets found for customer '" + customerName + "'.");
    }

    void searchByMovie(String movieName) {
        if (isEmpty()) { System.out.println("No tickets available."); return; }
        boolean found = false;
        TicketNode curr = head;
        do {
            if (curr.movieName.equalsIgnoreCase(movieName)) { printTicket(curr); found = true; }
            curr = curr.next;
        } while (curr != head);
        if (!found) System.out.println("No tickets found for movie '" + movieName + "'.");
    }

    void totalTickets() {
        System.out.println("Total booked tickets: " + size);
    }

    void printTicket(TicketNode node) {
        System.out.println("  Ticket ID: " + node.ticketId + " | Customer: " + node.customerName + " | Movie: " + node.movieName + " | Seat: " + node.seatNumber + " | Time: " + node.bookingTime);
    }
}

public class TicketReservation {
    public static void main(String[] args) {
        TicketReservationSystem system = new TicketReservationSystem();

        system.addTicket("T001", "Ravi Kumar", "Inception", "A12", "2024-12-01 10:00");
        system.addTicket("T002", "Priya Sharma", "Interstellar", "B05", "2024-12-01 10:15");
        system.addTicket("T003", "Arjun Singh", "Inception", "A13", "2024-12-01 10:30");
        system.addTicket("T004", "Neha Gupta", "The Dark Knight", "C07", "2024-12-01 11:00");
        system.addTicket("T005", "Ravi Kumar", "Interstellar", "D02", "2024-12-01 11:15");

        system.displayTickets();

        System.out.println("--- Search by Customer 'Ravi Kumar' ---");
        system.searchByCustomer("Ravi Kumar");

        System.out.println("\n--- Search by Movie 'Inception' ---");
        system.searchByMovie("Inception");

        system.totalTickets();

        System.out.println("\n--- Cancelling Ticket T003 ---");
        system.removeTicket("T003");
        system.displayTickets();

        system.totalTickets();
    }
}
