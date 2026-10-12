/** Linear and binary search that also count steps and time. */
public class Searching {

    public static class Result {
        public final String algorithm;
        public final boolean found;
        public final int index;
        public final int steps;
        public final long nanos;

        Result(String algorithm, boolean found, int index, int steps, long nanos) {
            this.algorithm = algorithm;
            this.found = found;
            this.index = index;
            this.steps = steps;
            this.nanos = nanos;
        }

        @Override
        public String toString() {
            String where = found ? "found at index " + index : "not found";
            return algorithm + ": " + where + ", steps = " + steps + ", time = " + nanos + " ns";
        }
    }

    /** Checks every element one by one. Worst case O(n). */
    public static Result linearSearch(int[] a, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int found = -1;
        for (int i = 0; i < a.length; i++) {
            steps++;
            if (a[i] == target) {
                found = i;
                break;
            }
        }
        return new Result("Linear Search", found != -1, found, steps, System.nanoTime() - start);
    }

    /** Halves the search range each time. Needs a sorted array. Worst case O(log n). */
    public static Result binarySearch(int[] a, int target) {
        long start = System.nanoTime();
        int steps = 0;
        int low = 0, high = a.length - 1, found = -1;
        while (low <= high) {
            steps++;
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                found = mid;
                break;
            } else if (a[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return new Result("Binary Search", found != -1, found, steps, System.nanoTime() - start);
    }
}
