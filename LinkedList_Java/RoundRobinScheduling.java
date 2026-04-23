import java.util.*;

class ProcessNode {
    String processId;
    int burstTime, remainingTime, priority;
    ProcessNode next;

    ProcessNode(String processId, int burstTime, int priority) {
        this.processId = processId;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}

class RoundRobinScheduler {
    ProcessNode head;
    int timeQuantum, size;

    RoundRobinScheduler(int timeQuantum) {
        this.timeQuantum = timeQuantum;
    }

    boolean isEmpty() { return head == null; }

    ProcessNode getTail() {
        if (isEmpty()) return null;
        ProcessNode curr = head;
        while (curr.next != head) curr = curr.next;
        return curr;
    }

    void addProcess(String processId, int burstTime, int priority) {
        ProcessNode node = new ProcessNode(processId, burstTime, priority);
        if (isEmpty()) { head = node; node.next = head; }
        else { ProcessNode tail = getTail(); tail.next = node; node.next = head; }
        size++;
        System.out.println("Process " + processId + " added (Burst: " + burstTime + ", Priority: " + priority + ")");
    }

    void removeProcess(String processId) {
        if (isEmpty()) return;
        if (head.processId.equals(processId)) {
            if (head.next == head) { head = null; size--; return; }
            ProcessNode tail = getTail();
            head = head.next;
            tail.next = head;
            size--;
            return;
        }
        ProcessNode curr = head;
        while (curr.next != head) {
            if (curr.next.processId.equals(processId)) {
                curr.next = curr.next.next;
                size--;
                return;
            }
            curr = curr.next;
        }
    }

    void simulate() {
        if (isEmpty()) { System.out.println("No processes to schedule."); return; }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Round Robin Scheduling | Time Quantum = " + timeQuantum);
        System.out.println("=".repeat(60));

        List<Map<String, Object>> processes = new ArrayList<>();
        ProcessNode curr = head;
        do {
            Map<String, Object> p = new LinkedHashMap<>();
            p.put("pid", curr.processId);
            p.put("burst", curr.burstTime);
            p.put("remaining", curr.burstTime);
            p.put("finish", 0);
            processes.add(p);
            curr = curr.next;
        } while (curr != head);

        int currentTime = 0, completed = 0, n = processes.size();
        List<Integer> queue = new ArrayList<>();
        for (int i = 0; i < n; i++) queue.add(i);

        while (completed < n) {
            List<Integer> nextQueue = new ArrayList<>();
            for (int i : queue) {
                Map<String, Object> p = processes.get(i);
                int remaining = (int) p.get("remaining");
                if (remaining > 0) {
                    int execTime = Math.min(timeQuantum, remaining);
                    System.out.println("Time " + currentTime + "-" + (currentTime + execTime) + ": Process " + p.get("pid") + " (Remaining: " + remaining + " -> " + (remaining - execTime) + ")");
                    p.put("remaining", remaining - execTime);
                    currentTime += execTime;
                    if ((int) p.get("remaining") == 0) {
                        p.put("finish", currentTime);
                        completed++;
                        System.out.println("  Process " + p.get("pid") + " COMPLETED at time " + currentTime);
                    } else {
                        nextQueue.add(i);
                    }
                }
            }
            queue = nextQueue;
            if (!queue.isEmpty()) {
                System.out.println("\n  Remaining: " + queue.stream().map(i -> (String) processes.get(i).get("pid")).toList() + "\n");
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("Process Statistics:");
        System.out.println("=".repeat(60));
        System.out.printf("%-8s %-10s %-10s %-14s %s%n", "PID", "Burst", "Finish", "Turnaround", "Waiting");
        System.out.println("-".repeat(50));

        double totalWt = 0, totalTat = 0;
        for (Map<String, Object> p : processes) {
            int burst = (int) p.get("burst");
            int finish = (int) p.get("finish");
            int tat = finish;
            int wt = tat - burst;
            totalWt += wt;
            totalTat += tat;
            System.out.printf("%-8s %-10d %-10d %-14d %d%n", p.get("pid"), burst, finish, tat, wt);
        }

        System.out.printf("%nAverage Turnaround Time: %.2f%n", totalTat / n);
        System.out.printf("Average Waiting Time   : %.2f%n", totalWt / n);
    }

    void displayQueue() {
        if (isEmpty()) { System.out.println("  [Queue is empty]"); return; }
        StringBuilder sb = new StringBuilder("  Queue: ");
        ProcessNode curr = head;
        do {
            sb.append(curr.processId).append("(rem:").append(curr.remainingTime).append(") -> ");
            curr = curr.next;
        } while (curr != head);
        sb.append("(head)");
        System.out.println(sb);
    }
}

public class RoundRobinScheduling {
    public static void main(String[] args) {
        RoundRobinScheduler scheduler = new RoundRobinScheduler(3);

        scheduler.addProcess("P1", 10, 2);
        scheduler.addProcess("P2", 5, 1);
        scheduler.addProcess("P3", 8, 3);
        scheduler.addProcess("P4", 3, 2);

        scheduler.simulate();
    }
}
