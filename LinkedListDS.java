/** Singly linked list of integers. */
public class LinkedListDS {
    private static class Node {
        int value;
        Node next;
        Node(int value) { this.value = value; }
    }

    private Node head;
    private Node tail;
    private int size;

    public boolean isEmpty() { return head == null; }
    public int size() { return size; }

    /** O(1) insert at the front. */
    public void insertAtHead(int value) {
        Node node = new Node(value);
        node.next = head;
        head = node;
        if (tail == null) tail = node;
        size++;
    }

    /** O(1) insert at the end (we keep a tail pointer). */
    public void insertAtTail(int value) {
        Node node = new Node(value);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    /** Deletes the first node holding value. Returns false if not found. */
    public boolean delete(int value) {
        Node prev = null;
        Node cur = head;
        while (cur != null && cur.value != value) {
            prev = cur;
            cur = cur.next;
        }
        if (cur == null) return false;
        if (prev == null) head = cur.next; else prev.next = cur.next;
        if (cur == tail) tail = prev;
        size--;
        return true;
    }

    /** Returns the 1-based position of value, or -1 if not found. */
    public int search(int value) {
        int pos = 1;
        for (Node cur = head; cur != null; cur = cur.next, pos++) {
            if (cur.value == value) return pos;
        }
        return -1;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Linked list is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Head -> ");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.value).append(" -> ");
        }
        sb.append("null  (size " + size + ")");
        System.out.println(sb);
    }
}
