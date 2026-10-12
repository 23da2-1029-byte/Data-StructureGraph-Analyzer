/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Graph Data Structure, BFS & DFS Traversals
 * Author     : M.R.F. Sara  |  Reg No: 23DA2-0679
 * GitHub     : SaraRameez
 * Email      : sararameez2020@gmail.com
 */
package datastructures;

import java.util.*;

/**
 * Represents a weighted/unweighted graph using an Adjacency List.
 *
 * <p>Supports both directed and undirected graphs. Vertices are stored
 * in a {@link TreeMap} to guarantee deterministic, sorted traversal order
 * required by the assignment output specification.</p>
 *
 * <p>Supported operations:
 * <ul>
 *   <li>Add vertex / Add edge (directed or undirected)</li>
 *   <li>Breadth-First Search (BFS) — O(V + E)</li>
 *   <li>Depth-First Search (DFS) — O(V + E)</li>
 *   <li>Display adjacency list</li>
 * </ul>
 * </p>
 *
 * @author  M.R.F. Sara (23DA2-0679)
 * @version 1.0
 */
public class Graph {
    // Adjacency List representation: maps each vertex to its list of adjacent vertices
    private final Map<Integer, List<Integer>> adjList;
    private final boolean isDirected;

    /**
     * Immutable data holder for the result of a BFS or DFS traversal.
     *
     * <p>Stores the algorithm name, start vertex, visitation order,
     * total step count, and wall-clock execution time in nanoseconds.</p>
     */
    public static class TraversalResult {
        public final String algorithm;
        public final int startVertex;
        public final List<Integer> order;
        public final int steps;
        public final long executionTimeNano;

        public TraversalResult(String algorithm, int startVertex, List<Integer> order, int steps, long executionTimeNano) {
            this.algorithm = algorithm;
            this.startVertex = startVertex;
            this.order = order;
            this.steps = steps;
            this.executionTimeNano = executionTimeNano;
        }

        @Override
        public String toString() {
            return String.format("%-5s from %d: Order %s | Steps: %d | Time: %d ns",
                    algorithm, startVertex, order.toString(), steps, executionTimeNano);
        }
    }

    public Graph() {
        this(false); // Default is undirected graph
    }

    public Graph(boolean isDirected) {
        this.adjList = new TreeMap<>(); // TreeMap keeps vertices naturally ordered for clean output
        this.isDirected = isDirected;
    }

    /**
     * Adds a vertex to the graph.
     * Time Complexity: O(1) average.
     */
    public boolean addVertex(int vertex) {
        if (adjList.containsKey(vertex)) {
            System.out.println("Notice: Vertex " + vertex + " already exists in graph.");
            return false;
        }
        adjList.put(vertex, new ArrayList<>());
        System.out.println("Success: Added vertex " + vertex);
        return true;
    }

    /**
     * Adds an edge between source and destination vertices.
     * Automatically creates vertices if they do not exist.
     * Time Complexity: O(1) amortized.
     */
    public boolean addEdge(int source, int destination) {
        if (!adjList.containsKey(source)) {
            addVertex(source);
        }
        if (!adjList.containsKey(destination)) {
            addVertex(destination);
        }

        List<Integer> srcNeighbors = adjList.get(source);
        if (srcNeighbors.contains(destination)) {
            System.out.println("Notice: Edge (" + source + " -> " + destination + ") already exists.");
            return false;
        }
        srcNeighbors.add(destination);

        if (!isDirected) {
            List<Integer> destNeighbors = adjList.get(destination);
            if (!destNeighbors.contains(source)) {
                destNeighbors.add(source);
            }
            System.out.println("Success: Added undirected edge between " + source + " and " + destination);
        } else {
            System.out.println("Success: Added directed edge (" + source + " -> " + destination + ")");
        }
        return true;
    }

    /**
     * Displays the graph's Adjacency List representation.
     */
    public void displayGraph() {
        if (adjList.isEmpty()) {
            System.out.println("Graph is currently empty (0 vertices, 0 edges).");
            return;
        }

        System.out.println("Graph Representation (Adjacency List):");
        System.out.println("Type: " + (isDirected ? "Directed" : "Undirected")
                + " | Vertices: " + getVertexCount() + " | Edges: " + getEdgeCount());
        for (Map.Entry<Integer, List<Integer>> entry : adjList.entrySet()) {
            System.out.print("  Vertex [" + entry.getKey() + "] -> ");
            List<Integer> neighbors = entry.getValue();
            if (neighbors.isEmpty()) {
                System.out.println("[] (No connected edges)");
            } else {
                System.out.println(neighbors);
            }
        }
    }

    /**
     * Breadth-First Search (BFS) Traversal using a Queue.
     * Explores vertex level-by-level.
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for visited set and queue.
     */
    public TraversalResult bfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("Error: Start vertex " + startVertex + " does not exist in graph.");
            return new TraversalResult("BFS", startVertex, Collections.emptyList(), 0, 0);
        }

        long startTime = System.nanoTime();
        List<Integer> traversalOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        java.util.Queue<Integer> queue = new java.util.LinkedList<>();
        int steps = 0;

        visited.add(startVertex);
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            traversalOrder.add(current);
            steps++;

            List<Integer> neighbors = adjList.getOrDefault(current, Collections.emptyList());
            for (int neighbor : neighbors) {
                steps++; // neighbor inspection step
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        long elapsedTime = System.nanoTime() - startTime;
        return new TraversalResult("BFS", startVertex, traversalOrder, steps, elapsedTime);
    }

    /**
     * Depth-First Search (DFS) Traversal using an explicit Stack / Iterative approach.
     * Explores as deep as possible along each branch before backtracking.
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) for visited set and stack.
     */
    public TraversalResult dfs(int startVertex) {
        if (!adjList.containsKey(startVertex)) {
            System.out.println("Error: Start vertex " + startVertex + " does not exist in graph.");
            return new TraversalResult("DFS", startVertex, Collections.emptyList(), 0, 0);
        }

        long startTime = System.nanoTime();
        List<Integer> traversalOrder = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        int steps = 0;

        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                traversalOrder.add(current);
                steps++;

                // Reverse neighbor list so smaller vertex numbers get processed first (similar to standard recursion)
                List<Integer> neighbors = new ArrayList<>(adjList.getOrDefault(current, Collections.emptyList()));
                Collections.reverse(neighbors);

                for (int neighbor : neighbors) {
                    steps++; // neighbor inspection step
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }

        long elapsedTime = System.nanoTime() - startTime;
        return new TraversalResult("DFS", startVertex, traversalOrder, steps, elapsedTime);
    }

    /**
     * Searches for a vertex using BFS traversal to verify reachability.
     */
    public boolean searchPath(int startVertex, int targetVertex) {
        if (!adjList.containsKey(startVertex) || !adjList.containsKey(targetVertex)) {
            System.out.println("Error: One or both vertices not found in graph.");
            return false;
        }
        TraversalResult res = bfs(startVertex);
        boolean reachable = res.order.contains(targetVertex);
        if (reachable) {
            System.out.println("Path exists: Vertex " + targetVertex + " is reachable from " + startVertex + ".");
        } else {
            System.out.println("No path: Vertex " + targetVertex + " is NOT reachable from " + startVertex + ".");
        }
        return reachable;
    }

    public int getVertexCount() {
        return adjList.size();
    }

    public int getEdgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjList.values()) {
            count += neighbors.size();
        }
        return isDirected ? count : count / 2;
    }

    public boolean containsVertex(int vertex) {
        return adjList.containsKey(vertex);
    }

    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    public void clear() {
        adjList.clear();
        System.out.println("Graph cleared.");
    }

    /**
     * Pre-populates the graph with the recommended assignment test graph:
     *      1
     *     / \
     *    2   3
     *    |   |
     *    4---5
     */
    public void loadSampleGraph() {
        clear();
        addVertex(1);
        addVertex(2);
        addVertex(3);
        addVertex(4);
        addVertex(5);

        addEdge(1, 2);
        addEdge(1, 3);
        addEdge(2, 4);
        addEdge(3, 5);
        addEdge(4, 5);
        System.out.println("Sample assignment benchmark graph loaded successfully!");
    }
}
