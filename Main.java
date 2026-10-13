/** Data Structure & Graph Analyzer - main menu and integration of all components. */
public class Main {
    private static final ArrayOps array = new ArrayOps(50);
    private static final StackDS stack = new StackDS(10);
    private static final QueueDS queue = new QueueDS(10);
    private static final LinkedListDS list = new LinkedListDS();
    private static final Graph graph = new Graph();
    private static final ResultsLog log = new ResultsLog();
    private static int[] searchData = null; // dataset used by the Searching menu

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("=============================================");
            System.out.println("DATA STRUCTURE & GRAPH ANALYZER");
            System.out.println("=============================================");
            System.out.println("1. Array Operations");
            System.out.println("2. Stack Operations");
            System.out.println("3. Queue Operations");
            System.out.println("4. Linked List Operations");
            System.out.println("5. Searching Operations");
            System.out.println("6. Graph Operations");
            System.out.println("7. Performance Comparison");
            System.out.println("8. Display All Results");
            System.out.println("9. Exit");
            switch (InputHelper.readInt("Enter your choice: ", 1, 9)) {
                case 1 -> arrayMenu();
                case 2 -> stackMenu();
                case 3 -> queueMenu();
                case 4 -> linkedListMenu();
                case 5 -> searchMenu();
                case 6 -> graphMenu();
                case 7 -> PerformanceAnalyzer.run(graph, log);
                case 8 -> log.displayAll();
                case 9 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
            }
        }
    }

    // ---------------- Array ----------------
    private static void arrayMenu() {
        while (true) {
            System.out.println("\n--------------- ARRAY OPERATIONS ---------------");
            System.out.println("1. Insert\n2. Delete\n3. Search\n4. Display\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1 -> {
                        int value = InputHelper.readInt("Value to insert: ");
                        int pos = InputHelper.readInt("Position (0 to " + array.size() + "): ");
                        array.insert(value, pos);
                        System.out.println("Inserted " + value + " at position " + pos + ".");
                        log.add("Array: inserted " + value + " at position " + pos);
                    }
                    case 2 -> {
                        if (array.isEmpty()) { System.out.println("Array is empty. Nothing to delete."); break; }
                        int value = InputHelper.readInt("Value to delete: ");
                        if (array.delete(value)) {
                            System.out.println("Deleted " + value + ".");
                            log.add("Array: deleted " + value);
                        } else {
                            System.out.println(value + " was not found.");
                        }
                    }
                    case 3 -> {
                        if (array.isEmpty()) { System.out.println("Array is empty."); break; }
                        int value = InputHelper.readInt("Value to search: ");
                        int idx = array.search(value);
                        System.out.println(idx == -1 ? value + " not found." : value + " found at index " + idx + ".");
                        log.add("Array: search " + value + " -> " + (idx == -1 ? "not found" : "index " + idx));
                    }
                    case 4 -> array.display();
                    case 5 -> { return; }
                }
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Stack ----------------
    private static void stackMenu() {
        while (true) {
            System.out.println("\n--------------- STACK OPERATIONS ---------------");
            System.out.println("1. Push\n2. Pop\n3. Peek\n4. Display\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1 -> {
                        int value = InputHelper.readInt("Value to push: ");
                        stack.push(value);
                        System.out.println("Pushed " + value + ".");
                        log.add("Stack: push " + value);
                    }
                    case 2 -> {
                        int v = stack.pop();
                        System.out.println("Popped " + v + ".");
                        log.add("Stack: pop -> " + v);
                    }
                    case 3 -> System.out.println("Top element: " + stack.peek());
                    case 4 -> stack.display();
                    case 5 -> { return; }
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Queue ----------------
    private static void queueMenu() {
        while (true) {
            System.out.println("\n--------------- QUEUE OPERATIONS ---------------");
            System.out.println("1. Enqueue\n2. Dequeue\n3. Peek / Front\n4. Display\n5. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 5);
            try {
                switch (c) {
                    case 1 -> {
                        int value = InputHelper.readInt("Value to enqueue: ");
                        queue.enqueue(value);
                        System.out.println("Enqueued " + value + ".");
                        log.add("Queue: enqueue " + value);
                    }
                    case 2 -> {
                        int v = queue.dequeue();
                        System.out.println("Dequeued " + v + ".");
                        log.add("Queue: dequeue -> " + v);
                    }
                    case 3 -> System.out.println("Front element: " + queue.peek());
                    case 4 -> queue.display();
                    case 5 -> { return; }
                }
            } catch (IllegalStateException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ---------------- Linked List ----------------
    private static void linkedListMenu() {
        while (true) {
            System.out.println("\n----------- LINKED LIST OPERATIONS -----------");
            System.out.println("1. Insert at Head\n2. Insert at Tail\n3. Delete\n4. Search\n5. Display\n6. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 6);
            switch (c) {
                case 1 -> {
                    int value = InputHelper.readInt("Value to insert: ");
                    list.insertAtHead(value);
                    System.out.println("Inserted " + value + " at the head.");
                    log.add("Linked List: insert at head " + value);
                }
                case 2 -> {
                    int value = InputHelper.readInt("Value to insert: ");
                    list.insertAtTail(value);
                    System.out.println("Inserted " + value + " at the tail.");
                    log.add("Linked List: insert at tail " + value);
                }
                case 3 -> {
                    if (list.isEmpty()) { System.out.println("Linked list is empty. Nothing to delete."); break; }
                    int value = InputHelper.readInt("Value to delete: ");
                    if (list.delete(value)) {
                        System.out.println("Deleted " + value + ".");
                        log.add("Linked List: deleted " + value);
                    } else {
                        System.out.println(value + " was not found.");
                    }
                }
                case 4 -> {
                    if (list.isEmpty()) { System.out.println("Linked list is empty."); break; }
                    int value = InputHelper.readInt("Value to search: ");
                    int pos = list.search(value);
                    System.out.println(pos == -1 ? value + " not found." : value + " found at position " + pos + ".");
                    log.add("Linked List: search " + value + " -> " + (pos == -1 ? "not found" : "position " + pos));
                }
                case 5 -> list.display();
                case 6 -> { return; }
            }
        }
    }

    // ---------------- Searching ----------------
    private static void searchMenu() {
        while (true) {
            System.out.println("\n------------- SEARCHING OPERATIONS -------------");
            System.out.println("1. Generate sorted dataset (even numbers 0, 2, 4, ...)");
            System.out.println("2. Use sorted copy of the Array module");
            System.out.println("3. Linear Search");
            System.out.println("4. Binary Search");
            System.out.println("5. Compare Linear vs Binary");
            System.out.println("6. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 6);
            if (c == 6) return;
            if (c == 1) {
                int n = InputHelper.readInt("Dataset size (1 to 1000000): ", 1, 1000000);
                searchData = new int[n];
                for (int i = 0; i < n; i++) searchData[i] = i * 2;
                System.out.println("Dataset created with " + n + " sorted numbers (0 to " + (n - 1) * 2 + ").");
                continue;
            }
            if (c == 2) {
                if (array.isEmpty()) { System.out.println("The Array module is empty. Insert some values first."); continue; }
                searchData = array.toSortedArray();
                System.out.println("Loaded " + searchData.length + " values (sorted) from the Array module.");
                continue;
            }
            if (searchData == null) {
                System.out.println("No dataset yet. Use option 1 or 2 first.");
                continue;
            }
            int target = InputHelper.readInt("Number to search for: ");
            if (c == 3 || c == 5) {
                Searching.Result r = Searching.linearSearch(searchData, target);
                System.out.println(r);
                log.add(r + " [target " + target + ", n=" + searchData.length + "]");
            }
            if (c == 4 || c == 5) {
                Searching.Result r = Searching.binarySearch(searchData, target);
                System.out.println(r);
                log.add(r + " [target " + target + ", n=" + searchData.length + "]");
            }
            if (c == 5) {
                System.out.println("Binary search needs far fewer steps on large sorted data: O(log n) vs O(n).");
            }
        }
    }

    // ---------------- Graph ----------------
    private static void graphMenu() {
        while (true) {
            System.out.println("\n--------------- GRAPH OPERATIONS ---------------");
            System.out.println("1. Add Vertex\n2. Add Edge\n3. Display Graph\n4. BFS Traversal\n5. DFS Traversal");
            System.out.println("6. Check Path Between Two Vertices\n7. Load Sample Graph\n8. Return to Main Menu");
            int c = InputHelper.readInt("Enter your choice: ", 1, 8);
            try {
                switch (c) {
                    case 1 -> {
                        String v = InputHelper.readString("Vertex name: ");
                        graph.addVertex(v);
                        System.out.println("Vertex " + v + " added.");
                    }
                    case 2 -> {
                        String a = InputHelper.readString("First vertex: ");
                        String b = InputHelper.readString("Second vertex: ");
                        graph.addEdge(a, b);
                        System.out.println("Edge " + a + " - " + b + " added.");
                    }
                    case 3 -> graph.display();
                    case 4 -> {
                        if (graph.isEmpty()) { System.out.println("Graph is empty."); break; }
                        Graph.Traversal t = graph.bfs(InputHelper.readString("Start vertex: "));
                        System.out.println(t);
                        log.add(t.toString());
                    }
                    case 5 -> {
                        if (graph.isEmpty()) { System.out.println("Graph is empty."); break; }
                        Graph.Traversal t = graph.dfs(InputHelper.readString("Start vertex: "));
                        System.out.println(t);
                        log.add(t.toString());
                    }
                    case 6 -> {
                        String a = InputHelper.readString("From vertex: ");
                        String b = InputHelper.readString("To vertex: ");
                        boolean ok = graph.pathExists(a, b);
                        System.out.println(ok ? "A path exists from " + a + " to " + b + "." : "No path from " + a + " to " + b + ".");
                        log.add("Graph path " + a + " -> " + b + ": " + (ok ? "exists" : "none"));
                    }
                    case 7 -> {
                        graph.loadSample();
                        System.out.println("Sample graph loaded (A-F).");
                    }
                    case 8 -> { return; }
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
