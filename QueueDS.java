/** Circular array-based queue (FIFO). */
public class QueueDS {
    private final int[] items;
    private int front = 0;
    private int size = 0;

    public QueueDS(int capacity) {
        items = new int[capacity];
    }

    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == items.length; }

    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Queue Overflow: the queue is full.");
        }
        items[(front + size) % items.length] = value;
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue Underflow: cannot dequeue from an empty queue.");
        }
        int value = items[front];
        front = (front + 1) % items.length;
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty: nothing at the front.");
        }
        return items[front];
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Queue (front -> rear): ");
        for (int i = 0; i < size; i++) {
            sb.append(items[(front + i) % items.length]);
            if (i < size - 1) sb.append(" <- ");
        }
        System.out.println(sb);
    }
}
