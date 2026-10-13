import java.util.*;

/** Undirected graph stored as an adjacency list. Vertices are names (Strings). */
public class Graph {

    /** Holds the visiting order, the number of steps and the time of one traversal. */
    public static class Traversal {
        public final String algorithm;
        public final List<String> order = new ArrayList<>();
        public int steps;
        public long nanos;

        Traversal(String algorithm) { this.algorithm = algorithm; }

        @Override
        public String toString() {
            return algorithm + " order: " + String.join(" -> ", order)
                    + " | steps = " + steps + ", time = " + nanos + " ns";
        }
    }

    private final Map<String, List<String>> adj = new LinkedHashMap<>();
    private int edgeCount = 0;

    public boolean hasVertex(String v) { return adj.containsKey(v); }
    public int vertexCount() { return adj.size(); }
    public int edgeCount() { return edgeCount; }
    public boolean isEmpty() { return adj.isEmpty(); }
    public String firstVertex() { return adj.keySet().iterator().next(); }

    public void addVertex(String v) {
        if (adj.containsKey(v)) {
            throw new IllegalArgumentException("Vertex '" + v + "' already exists.");
        }
        adj.put(v, new ArrayList<>());
    }

    public void addEdge(String a, String b) {
        if (!adj.containsKey(a) || !adj.containsKey(b)) {
            throw new IllegalArgumentException("Both vertices must exist first. Add them with 'Add Vertex'.");
        }
        if (a.equals(b)) {
            throw new IllegalArgumentException("A vertex cannot connect to itself.");
        }
        if (adj.get(a).contains(b)) {
            throw new IllegalArgumentException("Edge " + a + " - " + b + " already exists.");
        }
        adj.get(a).add(b);
        adj.get(b).add(a);
        edgeCount++;
    }

    public void display() {
        if (adj.isEmpty()) {
            System.out.println("Graph is empty.");
            return;
        }
        System.out.println("Adjacency list (" + vertexCount() + " vertices, " + edgeCount + " edges):");
        for (Map.Entry<String, List<String>> e : adj.entrySet()) {
            System.out.println("  " + e.getKey() + " : " + (e.getValue().isEmpty() ? "-" : String.join(", ", e.getValue())));
        }
    }

    /** Breadth-first search using a queue. Steps = vertices dequeued + neighbours checked. */
    public Traversal bfs(String start) {
        checkVertex(start);
        Traversal t = new Traversal("BFS");
        long begin = System.nanoTime();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(start);
        queue.add(start);
        while (!queue.isEmpty()) {
            String v = queue.poll();
            t.order.add(v);
            t.steps++;
            for (String n : adj.get(v)) {
                t.steps++;
                if (visited.add(n)) {
                    queue.add(n);
                }
            }
        }
        t.nanos = System.nanoTime() - begin;
        return t;
    }

    /** Depth-first search using recursion. Steps = vertices visited + neighbours checked. */
    public Traversal dfs(String start) {
        checkVertex(start);
        Traversal t = new Traversal("DFS");
        long begin = System.nanoTime();
        dfsVisit(start, new HashSet<>(), t);
        t.nanos = System.nanoTime() - begin;
        return t;
    }

    private void dfsVisit(String v, Set<String> visited, Traversal t) {
        visited.add(v);
        t.order.add(v);
        t.steps++;
        for (String n : adj.get(v)) {
            t.steps++;
            if (!visited.contains(n)) {
                dfsVisit(n, visited, t);
            }
        }
    }

    /** Graph search: is there a path from a to b? (uses BFS) */
    public boolean pathExists(String a, String b) {
        checkVertex(a);
        checkVertex(b);
        return bfs(a).order.contains(b);
    }

    private void checkVertex(String v) {
        if (!adj.containsKey(v)) {
            throw new IllegalArgumentException("Vertex '" + v + "' does not exist.");
        }
    }

    /** Loads a small example graph so the traversals can be demonstrated quickly. */
    public void loadSample() {
        String[] names = {"A", "B", "C", "D", "E", "F"};
        for (String n : names) {
            if (!hasVertex(n)) addVertex(n);
        }
        String[][] edges = {{"A", "B"}, {"A", "C"}, {"B", "D"}, {"C", "E"}, {"D", "F"}, {"E", "F"}};
        for (String[] e : edges) {
            if (!adj.get(e[0]).contains(e[1])) addEdge(e[0], e[1]);
        }
    }
}
