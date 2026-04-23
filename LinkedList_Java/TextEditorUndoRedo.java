class StateNode {
    String content;
    StateNode prev, next;

    StateNode(String content) {
        this.content = content;
    }
}

class TextEditor {
    StateNode current;
    int historySize;
    int maxHistory;

    TextEditor(int maxHistory) {
        this.maxHistory = maxHistory;
    }

    boolean isEmpty() { return current == null; }

    StateNode getHead() {
        StateNode curr = current;
        while (curr.prev != null) curr = curr.prev;
        return curr;
    }

    void typeText(String newContent) {
        StateNode node = new StateNode(newContent);
        if (isEmpty()) {
            current = node;
            historySize = 1;
        } else {
            current.next = null;
            node.prev = current;
            current.next = node;
            current = node;
            historySize++;

            if (historySize > maxHistory) {
                StateNode oldest = getHead();
                if (oldest.next != null) oldest.next.prev = null;
                historySize--;
            }
        }
        System.out.println("State saved: \"" + newContent + "\"");
    }

    void undo() {
        if (isEmpty()) { System.out.println("Nothing to undo."); return; }
        if (current.prev == null) { System.out.println("Already at oldest state."); return; }
        current = current.prev;
        System.out.println("Undo -> Current state: \"" + current.content + "\"");
    }

    void redo() {
        if (isEmpty()) { System.out.println("Nothing to redo."); return; }
        if (current.next == null) { System.out.println("Already at latest state."); return; }
        current = current.next;
        System.out.println("Redo -> Current state: \"" + current.content + "\"");
    }

    void displayCurrent() {
        if (isEmpty()) System.out.println("Editor is empty.");
        else System.out.println("Current State: \"" + current.content + "\"");
    }

    void displayHistory() {
        if (isEmpty()) { System.out.println("No history."); return; }
        StateNode head = getHead();
        StateNode curr = head;
        System.out.println("\n--- Edit History (max " + maxHistory + " states) ---");
        int idx = 1;
        while (curr != null) {
            String marker = (curr == current) ? " <-- (current)" : "";
            System.out.println(idx + ". \"" + curr.content + "\"" + marker);
            curr = curr.next;
            idx++;
        }
        System.out.println("Total history states: " + (idx - 1) + "\n");
    }
}

public class TextEditorUndoRedo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(5);

        editor.typeText("H");
        editor.typeText("He");
        editor.typeText("Hel");
        editor.typeText("Hell");
        editor.typeText("Hello");
        editor.typeText("Hello ");
        editor.typeText("Hello W");
        editor.typeText("Hello Wo");
        editor.typeText("Hello Wor");
        editor.typeText("Hello Worl");
        editor.typeText("Hello World");

        editor.displayHistory();
        editor.displayCurrent();

        System.out.println("\n--- Undoing 3 times ---");
        editor.undo();
        editor.undo();
        editor.undo();
        editor.displayCurrent();

        System.out.println("\n--- Redoing 1 time ---");
        editor.redo();
        editor.displayCurrent();

        System.out.println("\n--- Typing new text (clears redo history) ---");
        editor.typeText("Hello Python");
        editor.displayHistory();

        editor.redo();
    }
}
