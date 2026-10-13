/** Builds the Performance Comparison tables (steps and time). */
public class PerformanceAnalyzer {

    private static int[] sortedData(int n) {
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = i * 2;
        return a;
    }

    public static void run(Graph graph, ResultsLog log) {
        System.out.println("=============================================");
        System.out.println("PERFORMANCE COMPARISON");
        System.out.println("=============================================");
        System.out.printf("%-16s %-16s %-10s %10s %12s%n", "Operation", "Algorithm", "Data size", "Steps", "Time (ns)");
        System.out.println("---------------------------------------------------------------------");

        // 1. Searching: target is the last element, so linear search has to scan everything.
        int[] sizes = {1000, 10000, 100000};
        for (int n : sizes) {
            int[] data = sortedData(n);
            int target = data[n - 1];
            Searching.Result lin = Searching.linearSearch(data, target);
            Searching.Result bin = Searching.binarySearch(data, target);
            printRow("Search", "Linear Search", String.valueOf(n), lin.steps, lin.nanos);
            printRow("Search", "Binary Search", String.valueOf(n), bin.steps, bin.nanos);
            log.add("Performance (search, n=" + n + "): Linear steps=" + lin.steps + ", Binary steps=" + bin.steps);
        }

        // 2. Insert at the front: array has to shift every element, linked list only changes the head.
        int n = 100000;
        int[] arr = new int[n + 1];
        long t1 = System.nanoTime();
        for (int i = n; i > 0; i--) arr[i] = arr[i - 1];
        arr[0] = 1;
        long arrayNanos = System.nanoTime() - t1;

        LinkedListDS list = new LinkedListDS();
        for (int i = 0; i < n; i++) list.insertAtTail(i);
        long t2 = System.nanoTime();
        list.insertAtHead(1);
        long listNanos = System.nanoTime() - t2;

        printRow("Insert at front", "Array", String.valueOf(n), n, arrayNanos);
        printRow("Insert at front", "Linked List", String.valueOf(n), 1, listNanos);
        log.add("Performance (insert at front, n=" + n + "): Array steps=" + n + ", Linked List steps=1");

        // 3. Graph traversal on the current graph.
        if (graph.isEmpty()) {
            System.out.println("Graph traversal: graph is empty. Add vertices/edges or load the sample graph first.");
        } else {
            String start = graph.firstVertex();
            Graph.Traversal bfs = graph.bfs(start);
            Graph.Traversal dfs = graph.dfs(start);
            String size = graph.vertexCount() + "V/" + graph.edgeCount() + "E";
            printRow("Graph Traversal", "BFS", size, bfs.steps, bfs.nanos);
            printRow("Graph Traversal", "DFS", size, dfs.steps, dfs.nanos);
            log.add("Performance (graph from " + start + "): BFS steps=" + bfs.steps + ", DFS steps=" + dfs.steps);
        }
        System.out.println("=============================================");
        System.out.println("Notes:");
        System.out.println("- Linear search is O(n); binary search is O(log n), so the gap grows with the data size.");
        System.out.println("- Array insert at the front is O(n) (shifting); linked list insert at the head is O(1).");
        System.out.println("- BFS and DFS are both O(V + E); they differ in visiting order, not in big-O.");
        System.out.println("- Time in ns can vary between runs (JVM warm-up), but the step counts stay the same.");
    }

    private static void printRow(String op, String algo, String size, int steps, long nanos) {
        System.out.printf("%-16s %-16s %-10s %10d %12d%n", op, algo, size, steps, nanos);
    }
}
