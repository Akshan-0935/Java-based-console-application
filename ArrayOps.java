import java.util.Arrays;

/** Fixed-size integer array with insert, delete, search and display. */
public class ArrayOps {
    private final int[] data;
    private int size;

    public ArrayOps(int capacity) {
        data = new int[capacity];
    }

    public int size() { return size; }
    public int capacity() { return data.length; }
    public boolean isEmpty() { return size == 0; }
    public boolean isFull() { return size == data.length; }

    /** Inserts value at the given position and shifts the rest to the right. */
    public void insert(int value, int position) {
        if (isFull()) {
            throw new IllegalStateException("Array is full (capacity " + data.length + ").");
        }
        if (position < 0 || position > size) {
            throw new IllegalArgumentException("Position must be between 0 and " + size + ".");
        }
        for (int i = size; i > position; i--) {
            data[i] = data[i - 1];
        }
        data[position] = value;
        size++;
    }

    /** Deletes the first occurrence of value and shifts the rest to the left. */
    public boolean delete(int value) {
        int index = search(value);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        return true;
    }

    /** Linear search. Returns the index or -1. */
    public int search(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /** Returns a sorted copy (used by the searching component for binary search). */
    public int[] toSortedArray() {
        int[] copy = Arrays.copyOf(data, size);
        Arrays.sort(copy);
        return copy;
    }

    public void display() {
        if (isEmpty()) {
            System.out.println("Array is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("Array: [");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]  (size " + size + "/" + data.length + ")");
        System.out.println(sb);
    }
}
