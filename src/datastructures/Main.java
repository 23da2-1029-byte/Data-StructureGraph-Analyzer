/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2 (Week 12)
 * Data Structure and Graph Performance Analyzer
 * Main Console Integrated Application
 * Author     : A.M.Fathima Rifatha  |  Reg No: 23DA2-1029  (Team Leader)
 * GitHub     : 23da2-1029-byte
 * Email      : 23da2-1029@sltc.ac.lk
 */
package datastructures;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    // Global instances representing the integrated system state
    private static final ArrayOperations arrayOps = new ArrayOperations();
    private static final StackOperations stackOps = new StackOperations();
    private static final QueueOperations queueOps = new QueueOperations();
    private static final LinkedListOperations linkedListOps = new LinkedListOperations();
    private static final Graph graph = new Graph(false);

    public static void main(String[] args) {
        // Pre-populate sample assignment data for quick testing and demonstration
        initializeSampleData();

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = readInteger("Enter your choice (1-9): ");

            switch (choice) {
                case 1:
                    handleArrayMenu();
                    break;
                case 2:
                    handleStackMenu();
                    break;
                case 3:
                    handleQueueMenu();
                    break;
                case 4:
                    handleLinkedListMenu();
                    break;
                case 5:
                    handleSearchingMenu();
                    break;
                case 6:
                    handleGraphMenu();
                    break;
                case 7:
                    handlePerformanceMenu();
                    break;
                case 8:
                    displayAllResults();
                    break;
                case 9:
                    System.out.println("\nThank you for using the Data Structure & Graph Performance Analyzer. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 9.");
            }
        }
    }

    private static void displayMainMenu() {
        System.out.println("\n=============================================");
        System.out.println("      DATA STRUCTURE & GRAPH ANALYZER        ");
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
        System.out.println("=============================================");
    }

    // -------------------------------------------------------------
    // 1. ARRAY OPERATIONS MENU
    // -------------------------------------------------------------
    private static void handleArrayMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------- ARRAY OPERATIONS -------------");
            System.out.println("1. Insert Element (at End)");
            System.out.println("2. Insert Element at Index");
            System.out.println("3. Delete Element by Value");
            System.out.println("4. Delete Element at Index");
            System.out.println("5. Search Element (Linear Search)");
            System.out.println("6. Display Array");
            System.out.println("7. Clear Array");
            System.out.println("8. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-8): ");
            switch (choice) {
                case 1:
                    int val = readInteger("Enter integer to insert: ");
                    arrayOps.insert(val);
                    break;
                case 2:
                    int idx = readInteger("Enter index position: ");
                    int v = readInteger("Enter integer to insert: ");
                    arrayOps.insertAt(idx, v);
                    break;
                case 3:
                    int delVal = readInteger("Enter value to delete: ");
                    arrayOps.delete(delVal);
                    break;
                case 4:
                    int delIdx = readInteger("Enter index to delete: ");
                    arrayOps.deleteAt(delIdx);
                    break;
                case 5:
                    int searchVal = readInteger("Enter value to search: ");
                    arrayOps.search(searchVal);
                    break;
                case 6:
                    arrayOps.display();
                    break;
                case 7:
                    arrayOps.clear();
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 2. STACK OPERATIONS MENU
    // -------------------------------------------------------------
    private static void handleStackMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------- STACK OPERATIONS -------------");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek (Top)");
            System.out.println("4. Display Stack");
            System.out.println("5. Check if Empty");
            System.out.println("6. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-6): ");
            switch (choice) {
                case 1:
                    int val = readInteger("Enter integer to push: ");
                    stackOps.push(val);
                    break;
                case 2:
                    stackOps.pop();
                    break;
                case 3:
                    stackOps.peek();
                    break;
                case 4:
                    stackOps.display();
                    break;
                case 5:
                    System.out.println("Stack empty status: " + stackOps.isEmpty() + " (Size: " + stackOps.size() + ")");
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 3. QUEUE OPERATIONS MENU
    // -------------------------------------------------------------
    private static void handleQueueMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------- QUEUE OPERATIONS -------------");
            System.out.println("1. Enqueue");
            System.out.println("2. Dequeue");
            System.out.println("3. Peek / Front");
            System.out.println("4. Display Queue");
            System.out.println("5. Check if Empty");
            System.out.println("6. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-6): ");
            switch (choice) {
                case 1:
                    int val = readInteger("Enter integer to enqueue: ");
                    queueOps.enqueue(val);
                    break;
                case 2:
                    queueOps.dequeue();
                    break;
                case 3:
                    queueOps.peek();
                    break;
                case 4:
                    queueOps.display();
                    break;
                case 5:
                    System.out.println("Queue empty status: " + queueOps.isEmpty() + " (Size: " + queueOps.size() + ")");
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 4. LINKED LIST OPERATIONS MENU
    // -------------------------------------------------------------
    private static void handleLinkedListMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---------- LINKED LIST OPERATIONS ----------");
            System.out.println("1. Insert (Tail)");
            System.out.println("2. Insert (Head)");
            System.out.println("3. Delete by Value");
            System.out.println("4. Search Element");
            System.out.println("5. Display Linked List");
            System.out.println("6. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-6): ");
            switch (choice) {
                case 1:
                    int val = readInteger("Enter integer to insert at tail: ");
                    linkedListOps.insert(val);
                    break;
                case 2:
                    int hVal = readInteger("Enter integer to insert at head: ");
                    linkedListOps.insertHead(hVal);
                    break;
                case 3:
                    int delVal = readInteger("Enter integer to delete: ");
                    linkedListOps.delete(delVal);
                    break;
                case 4:
                    int sVal = readInteger("Enter integer to search: ");
                    linkedListOps.search(sVal);
                    break;
                case 5:
                    linkedListOps.display();
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 5. SEARCHING OPERATIONS MENU
    // -------------------------------------------------------------
    private static void handleSearchingMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n------------ SEARCHING OPERATIONS -----------");
            System.out.println("1. Linear Search on Array");
            System.out.println("2. Binary Search on Array (auto-sorts)");
            System.out.println("3. Compare Linear vs Binary Search");
            System.out.println("4. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-4): ");
            int[] currentArr = arrayOps.getArray();
            if (currentArr.length == 0 && choice >= 1 && choice <= 3) {
                System.out.println("Notice: The active array is empty! Please add elements in Array Operations first.");
                continue;
            }

            switch (choice) {
                case 1:
                    int t1 = readInteger("Enter target value to search: ");
                    SearchingOperations.SearchResult r1 = SearchingOperations.linearSearch(currentArr, t1);
                    System.out.println("Result: " + r1);
                    break;
                case 2:
                    int t2 = readInteger("Enter target value to search: ");
                    int[] sorted = java.util.Arrays.copyOf(currentArr, currentArr.length);
                    java.util.Arrays.sort(sorted);
                    System.out.println("Sorted Array: " + java.util.Arrays.toString(sorted));
                    SearchingOperations.SearchResult r2 = SearchingOperations.binarySearch(sorted, t2);
                    System.out.println("Result: " + r2);
                    break;
                case 3:
                    int t3 = readInteger("Enter target value to compare: ");
                    SearchingOperations.compareSearches(currentArr, t3);
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 6. GRAPH OPERATIONS MENU (Matches Page 3 of brief)
    // -------------------------------------------------------------
    private static void handleGraphMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--------------- GRAPH OPERATIONS ------------");
            System.out.println("1. Add Vertex");
            System.out.println("2. Add Edge");
            System.out.println("3. Display Graph");
            System.out.println("4. BFS Traversal");
            System.out.println("5. DFS Traversal");
            System.out.println("6. Search Path / Reachability");
            System.out.println("7. Reset to Sample Assignment Benchmark Graph");
            System.out.println("8. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-8): ");
            switch (choice) {
                case 1:
                    int v = readInteger("Enter vertex value (integer): ");
                    graph.addVertex(v);
                    break;
                case 2:
                    int src = readInteger("Enter source vertex: ");
                    int dest = readInteger("Enter destination vertex: ");
                    graph.addEdge(src, dest);
                    break;
                case 3:
                    graph.displayGraph();
                    break;
                case 4:
                    if (graph.getVertexCount() == 0) {
                        System.out.println("Graph is empty. Add vertices first.");
                        break;
                    }
                    int bfsStart = readInteger("Enter start vertex for BFS: ");
                    Graph.TraversalResult bfsRes = graph.bfs(bfsStart);
                    System.out.println("BFS Traversal Order: " + bfsRes.order);
                    System.out.println("Steps taken: " + bfsRes.steps + " | Execution Time: " + bfsRes.executionTimeNano + " ns");
                    break;
                case 5:
                    if (graph.getVertexCount() == 0) {
                        System.out.println("Graph is empty. Add vertices first.");
                        break;
                    }
                    int dfsStart = readInteger("Enter start vertex for DFS: ");
                    Graph.TraversalResult dfsRes = graph.dfs(dfsStart);
                    System.out.println("DFS Traversal Order: " + dfsRes.order);
                    System.out.println("Steps taken: " + dfsRes.steps + " | Execution Time: " + dfsRes.executionTimeNano + " ns");
                    break;
                case 6:
                    int s = readInteger("Enter start vertex: ");
                    int d = readInteger("Enter target vertex: ");
                    graph.searchPath(s, d);
                    break;
                case 7:
                    graph.loadSampleGraph();
                    break;
                case 8:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 7. PERFORMANCE COMPARISON MENU
    // -------------------------------------------------------------
    private static void handlePerformanceMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n---------- PERFORMANCE COMPARISON ----------");
            System.out.println("1. Run Integrated Comparison Table (Assignment Section 4)");
            System.out.println("2. Run Stress Test Benchmark (50,000 Elements)");
            System.out.println("3. Display Big-O Complexity Summary Tables");
            System.out.println("4. Return to Main Menu");
            System.out.println("--------------------------------------------");

            int choice = readInteger("Enter your choice (1-4): ");
            switch (choice) {
                case 1:
                    int[] arr = arrayOps.getArray();
                    if (arr.length == 0) {
                        System.out.println("Populating array with default values [10, 20, 30, 40, 50, 60, 70] for comparison.");
                        arrayOps.insert(10);
                        arrayOps.insert(20);
                        arrayOps.insert(30);
                        arrayOps.insert(40);
                        arrayOps.insert(50);
                        arrayOps.insert(60);
                        arrayOps.insert(70);
                        arr = arrayOps.getArray();
                    }
                    if (graph.getVertexCount() == 0) {
                        graph.loadSampleGraph();
                    }
                    int target = readInteger("Enter target value to search for in array comparison: ");
                    int startVertex = 1;
                    if (!graph.containsVertex(startVertex)) {
                        startVertex = graph.getVertices().iterator().next();
                    }
                    PerformanceAnalyzer.runIntegratedComparison(arr, target, graph, startVertex);
                    break;
                case 2:
                    PerformanceAnalyzer.runLargeDatasetBenchmark(50000);
                    break;
                case 3:
                    PerformanceAnalyzer.displayAllComplexityTables();
                    break;
                case 4:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid selection. Try again.");
            }
        }
    }

    // -------------------------------------------------------------
    // 8. DISPLAY ALL RESULTS (System-wide snapshot)
    // -------------------------------------------------------------
    private static void displayAllResults() {
        System.out.println("\n=============================================");
        System.out.println("         SYSTEM-WIDE STATE OVERVIEW          ");
        System.out.println("=============================================");
        System.out.println("[1] ARRAY STATE:");
        arrayOps.display();

        System.out.println("\n[2] STACK STATE:");
        stackOps.display();

        System.out.println("\n[3] QUEUE STATE:");
        queueOps.display();

        System.out.println("\n[4] LINKED LIST STATE:");
        linkedListOps.display();

        System.out.println("\n[5] GRAPH STATE:");
        graph.displayGraph();
        System.out.println("=============================================");
    }

    // -------------------------------------------------------------
    // HELPER METHODS: Input validation and Pre-loading
    // -------------------------------------------------------------
    private static int readInteger(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid integer number.");
            }
        }
    }

    private static void initializeSampleData() {
        // Pre-fill Array
        arrayOps.insert(10);
        arrayOps.insert(20);
        arrayOps.insert(30);
        arrayOps.insert(40);
        arrayOps.insert(50);

        // Pre-fill Stack
        stackOps.push(100);
        stackOps.push(200);
        stackOps.push(300);

        // Pre-fill Queue
        queueOps.enqueue(1000);
        queueOps.enqueue(2000);
        queueOps.enqueue(3000);

        // Pre-fill Linked List
        linkedListOps.insert(5);
        linkedListOps.insert(15);
        linkedListOps.insert(25);

        // Pre-fill Graph (assignment benchmark graph)
        graph.loadSampleGraph();

        System.out.println(">> System initialized with standard test dataset for immediate demonstration.");
    }
}
