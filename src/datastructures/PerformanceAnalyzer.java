/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Performance Analyzer & Algorithmic Complexity Benchmark
 * Author     : M.R.F. Sara  |  Reg No: 23DA2-0679
 * GitHub     : SaraRameez
 * Email      : sararameez2020@gmail.com
 */
package datastructures;

import java.util.Arrays;

/**
 * Benchmarks all data structures implemented in this project.
 *
 * <p>Runs timed experiments on:
 * <ul>
 *   <li>Linear Search vs Binary Search (step count + nanosecond timing)</li>
 *   <li>BFS vs DFS graph traversal (step count + nanosecond timing)</li>
 *   <li>Array, Stack, Queue, Linked List operations</li>
 * </ul>
 * </p>
 *
 * <p>Output is formatted as a table matching the assignment Section 4 requirement.
 * Big-O complexity classes are displayed alongside empirical measurements.</p>
 *
 * @author  M.R.F. Sara (23DA2-0679)
 * @version 1.0
 */
public class PerformanceAnalyzer {

    /**
     * Prints the exact Performance Comparison table required by Section 4 of the assignment brief.
     * Benchmarks linear search, binary search, BFS, and DFS side by side.
     *
     * @param searchArray      sorted integer array used for search benchmarks
     * @param target           value to search for
     * @param graph            graph instance used for traversal benchmarks
     * @param graphStartVertex starting vertex for BFS and DFS
     */
    public static void runIntegratedComparison(int[] searchArray, int target, Graph graph, int graphStartVertex) {
        System.out.println("\n==========================================================================");
        System.out.println("                         PERFORMANCE COMPARISON                           ");
        System.out.println("==========================================================================");
        System.out.printf("%-18s %-16s %-10s %-15s %-12s\n", "Operation", "Algorithm", "Steps", "Time (ns)", "Result");
        System.out.println("--------------------------------------------------------------------------");

        // 1. Linear Search
        SearchingOperations.SearchResult linearRes = SearchingOperations.linearSearch(searchArray, target);
        System.out.printf("%-18s %-16s %-10d %-15d %-12s\n",
                "Search", linearRes.algorithm, linearRes.steps, linearRes.executionTimeNano,
                (linearRes.found ? "Found @" + linearRes.index : "Not Found"));

        // 2. Binary Search
        int[] sortedCopy = Arrays.copyOf(searchArray, searchArray.length);
        Arrays.sort(sortedCopy);
        SearchingOperations.SearchResult binaryRes = SearchingOperations.binarySearch(sortedCopy, target);
        System.out.printf("%-18s %-16s %-10d %-15d %-12s\n",
                "Search", binaryRes.algorithm, binaryRes.steps, binaryRes.executionTimeNano,
                (binaryRes.found ? "Found @" + binaryRes.index : "Not Found"));

        // 3. Graph Traversal BFS
        Graph.TraversalResult bfsRes = graph.bfs(graphStartVertex);
        System.out.printf("%-18s %-16s %-10d %-15d %-12s\n",
                "Graph Traversal", bfsRes.algorithm, bfsRes.steps, bfsRes.executionTimeNano,
                "Visited: " + bfsRes.order.size());

        // 4. Graph Traversal DFS
        Graph.TraversalResult dfsRes = graph.dfs(graphStartVertex);
        System.out.printf("%-18s %-16s %-10d %-15d %-12s\n",
                "Graph Traversal", dfsRes.algorithm, dfsRes.steps, dfsRes.executionTimeNano,
                "Visited: " + dfsRes.order.size());

        System.out.println("==========================================================================");

        printComplexityExplanations(linearRes, binaryRes, bfsRes, dfsRes);
    }

    /**
     * Runs an empirical large-dataset benchmark (e.g. 50,000 elements) to visually highlight
     * asymptotic difference between O(n) and O(log n).
     */
    public static void runLargeDatasetBenchmark(int datasetSize) {
        System.out.println("\n--- RUNNING STRESS TEST BENCHMARK (" + datasetSize + " elements) ---");
        int[] dataset = new int[datasetSize];
        for (int i = 0; i < datasetSize; i++) {
            dataset[i] = i * 2; // evenly spaced sorted values
        }

        int target = dataset[datasetSize - 2]; // Worst-case search target near end

        // Linear Search
        SearchingOperations.SearchResult linearRes = SearchingOperations.linearSearch(dataset, target);

        // Binary Search
        SearchingOperations.SearchResult binaryRes = SearchingOperations.binarySearch(dataset, target);

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-15s\n", "Algorithm", "Steps Taken", "Time (ns)", "Theoretical");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12d | %-12d | %-15s\n", "Linear Search", linearRes.steps, linearRes.executionTimeNano, "O(n) -> " + datasetSize);
        System.out.printf("%-15s | %-12d | %-12d | %-15s\n", "Binary Search", binaryRes.steps, binaryRes.executionTimeNano, "O(log n) -> ~" + (int)(Math.log(datasetSize)/Math.log(2)));
        System.out.println("--------------------------------------------------------------------------");

        System.out.println(">> EMPIRICAL PROOF: Binary Search required " + binaryRes.steps + " steps vs Linear Search "
                + linearRes.steps + " steps!");
        System.out.println(">> Speedup Factor in steps: " + String.format("%.1fx", (double) linearRes.steps / binaryRes.steps));
    }

    /**
     * Displays complete Big-O algorithmic complexity reference for all components.
     */
    public static void displayAllComplexityTables() {
        System.out.println("\n==========================================================================");
        System.out.println("                 DATA STRUCTURES & ALGORITHMS COMPLEXITY SUMMARY          ");
        System.out.println("==========================================================================");

        System.out.println("\n1. LINEAR DATA STRUCTURES:");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Structure", "Access", "Search", "Insertion", "Deletion");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Array", "O(1)", "O(n)", "O(n) / O(1)*", "O(n)");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Stack (LIFO)", "O(n)", "O(n)", "O(1) [Push]", "O(1) [Pop]");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Queue (FIFO)", "O(n)", "O(n)", "O(1) [Enq]", "O(1) [Deq]");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Linked List", "O(n)", "O(n)", "O(1) Head", "O(1) Head/O(n)");
        System.out.println("*Array insertion is amortized O(1) at end, O(n) when arbitrary index or resizing.");

        System.out.println("\n2. SEARCHING ALGORITHMS:");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Algorithm", "Best Time", "Average Time", "Worst Time", "Space Complexity");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Linear Search", "O(1)", "O(n)", "O(n)", "O(1)");
        System.out.printf("%-15s | %-12s | %-12s | %-12s | %-12s\n", "Binary Search", "O(1)", "O(log n)", "O(log n)", "O(1)");

        System.out.println("\n3. GRAPH TRAVERSAL ALGORITHMS:");
        System.out.printf("%-15s | %-15s | %-15s | %-20s\n", "Algorithm", "Time Complexity", "Space Complexity", "Data Structure Used");
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("%-15s | %-15s | %-15s | %-20s\n", "BFS (Breadth)", "O(V + E)", "O(V)", "Queue (FIFO)");
        System.out.printf("%-15s | %-15s | %-15s | %-20s\n", "DFS (Depth)", "O(V + E)", "O(V)", "Stack / Recursion (LIFO)");
        System.out.println("==========================================================================\n");
    }

    private static void printComplexityExplanations(SearchingOperations.SearchResult linear,
                                                    SearchingOperations.SearchResult binary,
                                                    Graph.TraversalResult bfs,
                                                    Graph.TraversalResult dfs) {
        System.out.println("\n[Viva / Demonstration Explanation]:");
        System.out.println("1. Linear vs Binary Search:");
        System.out.println("   - Linear Search checks elements sequentially, taking " + linear.steps + " steps.");
        System.out.println("   - Binary Search repeatedly divides the search space in half, taking only " + binary.steps + " steps.");
        System.out.println("   - Why results differ: Binary search eliminates 50% of the remaining elements in each step,");
        System.out.println("     achieving logarithmic O(log n) efficiency compared to linear O(n).");
        System.out.println("2. BFS vs DFS Graph Traversal:");
        System.out.println("   - BFS explores level-by-level using a FIFO Queue. Order: " + bfs.order);
        System.out.println("   - DFS explores deep branches before backtracking using LIFO Stack. Order: " + dfs.order);
        System.out.println("   - Both visit all V vertices and traverse E edges with O(V + E) complexity,");
        System.out.println("     but BFS guarantees shortest path in unweighted graphs, while DFS is ideal for topological sort and cycle detection.");
    }
}
