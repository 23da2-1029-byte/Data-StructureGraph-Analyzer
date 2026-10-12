# CIT300 Data Structures and Algorithms
## Graded Practical Assignment 2 (Week 12)
### Data Structure and Graph Performance Analyzer

---

## 1. Project Title & Overview
**Project Title**: Data Structure & Graph Performance Analyzer  
**Course**: CIT300 — Data Structures and Algorithms  
**Application Type**: Standalone Interactive Console-Based System  
**Programming Language**: Java (JDK 8 / OpenJDK 1.8+ compatible)  
**Submission Artifacts**: Complete Java Source Code, Git/GitHub Repository, Merged Demonstration Video (<15 min)

### Executive Summary
The **Data Structure and Graph Performance Analyzer** is an integrated software system engineered to demonstrate, analyze, and benchmark fundamental linear and non-linear data structures, searching algorithms, and graph traversals. Built strictly in accordance with the CIT300 Assignment 2 specification, the application features custom object-oriented implementations of:
- **Linear Data Structures**: Dynamic Resizable Array, LIFO Stack (with underflow safety), Circular FIFO Queue (with circular wraparound and underflow safety), and Singly Linked List (with custom Node reference linking).
- **Searching Algorithms**: Linear Search ($O(n)$) and Binary Search ($O(\log n)$) with step counter instrumentation and high-resolution nanosecond timers.
- **Graph Structure & Traversals**: Adjacency List graph representation with Breadth-First Search (BFS via Queue) and Depth-First Search (DFS via Stack/Recursion), plus path reachability detection.
- **Performance & Complexity Analyzer**: Automated generation of the assignment-mandated performance comparison table, empirical 50,000-element stress testing (demonstrating logarithmic asymptotic speedup), and full Big-O reference tables.
- **Interactive Console UI**: A robust 9-option top-level menu with 6 dedicated submenus, fortified with `try-catch` input validation to prevent crashes from invalid user inputs.

---

## 2. Team Member Details & Individual Contributions

> **Note**: As stipulated in Section 5 & 7 of the assignment brief, individual member details, roles, and concrete technical contributions are documented below.

### Lead Developer / Group Member 1
- **Full Name**: A.M.Fathima Rifatha
- **Student ID**: 23DA2-1029
- **Email**: 23da2-1029@sltc.ac.lk
- **GitHub**: https://github.com/23da2-1029-byte
- **Assigned Responsibility**: System Architecture, Main Menu Integration & Array / Searching Implementation
- **Individual Contribution**:
  - Engineered the central application framework in `Main.java` with loop-driven submenus and crash-proof console input validation (`readInteger`).
  - Developed `ArrayOperations.java` with dynamic capacity doubling, arbitrary index insertion, shift-based deletion, and linear search step tracking.
  - Developed `SearchingOperations.java` implementing sequential Linear Search and divide-and-conquer Binary Search with nanosecond benchmarking.
  - Configured project build automation (`run.bat`), Git collaboration scripts (`setup_git.ps1`), and project verification suites.

### Group Member 2
- **Full Name**: M.S.F. Noaf
- **Student ID**: 23DA2-0975
- **Email**: 23da2-0975@sltc.ac.lk
- **GitHub**: https://github.com/NOAF2002
- **Assigned Responsibility**: Stack, Circular Queue & Singly Linked List Implementation
- **Individual Contribution**:
  - Implemented `StackOperations.java` using LIFO principles with dynamic array expansion and underflow safety.
  - Implemented `QueueOperations.java` using a circular array with `front`, `rear`, and `size` pointers and modular arithmetic (`(rear + 1) % capacity`) to prevent memory drift.
  - Handled empty-data-structure underflow conditions on `pop`, `dequeue`, and `peek` without throwing unhandled exceptions.
  - Built custom `LinkedListOperations.java` and internal `Node` class without relying on `java.util.LinkedList`.
  - Implemented head insertion ($O(1)$), tail insertion ($O(n)$), node deletion by value, and sequential pointer searching.
  - Designed the ASCII visualizers for Stack (`[TOP] -> | ... | -> [BASE]`), Queue (`[FRONT] -> ... -> [REAR]`), and Linked List (`[Head] -> ... -> null`).

### Group Member 3
- **Full Name**: M.R.F. Sara
- **Student ID**: 23DA2-0679
- **Email**: sararameez2020@gmail.com
- **GitHub**: https://github.com/SaraRameez
- **Assigned Responsibility**: Graph Data Structure, Traversals (BFS / DFS) & Performance Analyzer
- **Individual Contribution**:
  - Implemented `Graph.java` using an Adjacency List representation via `Map<Integer, List<Integer>>` (using `TreeMap` for deterministic vertex ordering).
  - Implemented vertex additions and undirected/directed edge creation.
  - Implemented Breadth-First Search (BFS) traversal using a FIFO queue to explore the graph level-by-level.
  - Implemented Depth-First Search (DFS) traversal using a LIFO stack to explore deep branches before backtracking.
  - Implemented path reachability analysis between any two graph vertices and pre-loaded the benchmark test graph.
  - Implemented `PerformanceAnalyzer.java`, including the Section 4 comparison table, Big-O reference matrix, and the 50,000-element empirical stress test benchmark.

---

## 3. Technologies Used
| Component | Technology / Tool | Purpose |
| :--- | :--- | :--- |
| **Language** | Java (JDK 8 / OpenJDK 1.8.0+) | Core programming language for all classes |
| **Paradigm** | Object-Oriented Programming (OOP) | Encapsulation, modular separation of data structures |
| **Version Control** | Git & GitHub | Branching, meaningful commits, PR simulation, collaboration evidence |
| **Build Automation** | Windows Batch Script (`run.bat`) | One-click compilation and execution |
| **Testing** | Console I/O Test Automation | Boundary, stress, and underflow/overflow verification |
| **Documentation** | Markdown (GitHub Flavored) | Formal academic documentation and viva video script |

---

## 4. System Architecture & Directory Layout

```
DataStructureGraphAnalyzer/
├── src/
│   └── datastructures/
│       ├── ArrayOperations.java       # Resizable array with CRUD, shift deletion & step tracking
│       ├── StackOperations.java       # LIFO Stack with underflow safety & vertical ASCII display
│       ├── QueueOperations.java       # Circular FIFO Queue with modular arithmetic
│       ├── LinkedListOperations.java  # Custom Node-based Singly Linked List (pure OOP)
│       ├── SearchingOperations.java   # Linear vs Binary search with nanosecond timing
│       ├── Graph.java                 # Adjacency List, BFS (Queue), DFS (Stack), reachability
│       ├── PerformanceAnalyzer.java   # Assignment Section 4 table, Big-O tables, stress test
│       └── Main.java                  # Interactive 9-option menu with crash-proof input validation
├── bin/                               # Compiled bytecode (.class files)
├── run.bat                            # Windows one-click automated build & run script
├── setup_git.ps1                      # Automated Git branch & commit collaboration script
├── GIT_COLLABORATION_GUIDE.md         # Complete Git collaboration and submission guide
├── VIDEO_SCRIPT_AND_VIVA_GUIDE.md     # Timed script (<15 min) for the merged demonstration video
├── .gitignore                         # Excludes bin/ and IDE workspace files
└── README.md                          # Comprehensive project documentation
```

---

## 5. Main System Features & Component Specifications

### A. Dynamic Array Operations (`ArrayOperations.java`)
- **Dynamic Resizing**: Starts with default capacity 10; automatically doubles capacity when saturated using `Arrays.copyOf()`.
- **Operations**:
  - `insert(int value)`: Appends value to the end ($O(1)$ amortized).
  - `insertAt(int index, int value)`: Shifts elements to the right and inserts at specified index ($O(n)$).
  - `delete(int value)`: Locates first occurrence and shifts left to close the gap ($O(n)$).
  - `deleteAt(int index)`: Validates bounds $[0, size - 1]$ and shifts elements left ($O(n)$).
  - `search(int value)`: Sequentially searches array, logging step comparisons.
  - `display()`: Outputs formatted array state with size and capacity metadata.

### B. Stack Operations (`StackOperations.java`)
- **Principle**: Last-In, First-Out (LIFO).
- **Safety**: Safe Stack Underflow protection. When `pop()` or `peek()` is invoked on an empty stack, the system outputs a clear error message and returns `null` rather than throwing an exception or returning an ambiguous `-1`.
- **Visualization**: Renders an ASCII vertical representation showing `[TOP]` and `[BASE]`.

### C. Queue Operations (`QueueOperations.java`)
- **Principle**: First-In, First-Out (FIFO).
- **Implementation**: Circular array using `front`, `rear`, and `size` variables with modular arithmetic `(rear + 1) % capacity`. Prevents memory drifting.
- **Safety**: Safe Queue Underflow protection on `dequeue()` and `peek()`.
- **Visualization**: Renders a horizontal flow showing `[FRONT] -> elements -> [REAR]`.

### D. Singly Linked List (`LinkedListOperations.java`)
- **Implementation**: Pure custom node reference chaining using a static inner class `Node (int data, Node next)` without third-party collections.
- **Operations**:
  - `insert(int value)`: Traverses to the end and attaches a new node ($O(n)$).
  - `insertHead(int value)`: Instantly updates `head` pointer ($O(1)$).
  - `delete(int value)`: Handles head deletion, intermediate deletion, tail deletion, and missing value notifications.
  - `search(int value)`: Traverses nodes, reporting 0-based index and node traversal steps.
  - `display()`: Formatted arrow output `[Head] -> [x] -> [y] -> null`.

### E. Searching Algorithms (`SearchingOperations.java`)
- **Linear Search**: Sequentially checks every element from index 0 to $n-1$. Does not require sorted data. Worst case: $O(n)$ comparisons.
- **Binary Search**: Iterative divide-and-conquer implementation. Operates on a sorted copy of the array. Repeatedly halves the search space: $O(\log n)$ comparisons.
- **Side-by-Side Comparison**: Measures execution time using `System.nanoTime()` and counts the exact number of comparisons made by both algorithms.

### F. Graph Data Structure & Traversals (`Graph.java`)
- **Representation**: Adjacency List utilizing `Map<Integer, List<Integer>>` with `TreeMap` to ensure deterministic, sorted neighbor lists.
- **Operations**:
  - `addVertex(int vertex)`: Adds a new vertex key.
  - `addEdge(int src, int dest)`: Adds edges (undirected by default; automatically adds reciprocal edges).
  - `displayGraph()`: Outputs the adjacency list representation clearly.
  - `bfs(int startVertex)`: Explores vertex neighbors level-by-level using a FIFO Queue. Time: $O(V + E)$, Space: $O(V)$.
  - `dfs(int startVertex)`: Explores branch depth first using an explicit LIFO Stack. Time: $O(V + E)$, Space: $O(V)$.
  - `searchPath(int start, int target)`: Evaluates graph connectivity and reachability between two vertices.
  - `loadSampleGraph()`: Loads the assignment benchmark graph (Vertices 1–5, Edges 1-2, 1-3, 2-4, 3-5, 4-5).

### G. Performance & Complexity Analyzer (`PerformanceAnalyzer.java`)
- **Integrated Comparison Table**: Directly matches Section 4 of the assignment brief:
  ```
  ==========================================================================
                           PERFORMANCE COMPARISON                           
  ==========================================================================
  Operation          Algorithm        Steps      Time (ns)       Result      
  --------------------------------------------------------------------------
  Search             Linear Search    4          ...             Found @3    
  Search             Binary Search    2          ...             Found @3    
  Graph Traversal    BFS              15         ...             Visited: 5  
  Graph Traversal    DFS              15         ...             Visited: 5  
  ==========================================================================
  ```
- **Large Dataset Stress Test**: Executes a 50,000-element search benchmark comparing worst-case Linear Search (~49,999 steps) against Binary Search (~15 steps), proving a **~3333x empirical speedup**.
- **Theoretical Big-O Reference**: Prints complete complexity tables for all structures and algorithms.

---

## 6. Algorithmic Complexity Reference

### Linear Data Structures
| Data Structure | Access | Search | Insertion | Deletion | Space Complexity |
| :--- | :---: | :---: | :---: | :---: | :---: |
| **Array** | $O(1)$ | $O(n)$ | $O(1)$ amortized / $O(n)$ at index | $O(n)$ | $O(n)$ |
| **Stack (LIFO)** | $O(n)$ | $O(n)$ | $O(1)$ (`push`) | $O(1)$ (`pop`) | $O(n)$ |
| **Queue (FIFO)** | $O(n)$ | $O(n)$ | $O(1)$ (`enqueue`) | $O(1)$ (`dequeue`) | $O(n)$ |
| **Linked List** | $O(n)$ | $O(n)$ | $O(1)$ (Head) / $O(n)$ (Tail) | $O(1)$ (Head) / $O(n)$ | $O(n)$ |

### Searching Algorithms
| Algorithm | Best Case | Average Case | Worst Case | Space Complexity | Data Requirement |
| :--- | :---: | :---: | :---: | :---: | :--- |
| **Linear Search** | $O(1)$ | $O(n)$ | $O(n)$ | $O(1)$ | Works on unsorted data |
| **Binary Search** | $O(1)$ | $O(\log n)$ | $O(\log n)$ | $O(1)$ | **Requires sorted data** |

### Graph Traversal Algorithms
| Algorithm | Time Complexity | Space Complexity | Underlying Structure | Primary Practical Use |
| :--- | :---: | :---: | :---: | :--- |
| **BFS (Breadth-First)** | $O(V + E)$ | $O(V)$ | Queue (FIFO) | Shortest path in unweighted graphs, level-order search |
| **DFS (Depth-First)** | $O(V + E)$ | $O(V)$ | Stack (LIFO) / Recursion | Topological sorting, cycle detection, maze solving |

---

## 7. How to Compile and Run

### Method 1: One-Click Execution (Windows)
Double-click `run.bat` or execute in PowerShell / Command Prompt:
```cmd
.\run.bat
```

### Method 2: Manual Compilation & Execution
1. Open terminal in the project root directory:
   ```powershell
   cd D:\Desktop\ICCC\DataStructureGraphAnalyzer
   ```
2. Compile all source files into the `bin` directory:
   ```powershell
   javac -d bin src\datastructures\*.java
   ```
3. Run the application:
   ```powershell
   java -cp bin datastructures.Main
   ```

---

## 8. Verification & Test Evidence

The application has been verified against all rubric criteria:
- **Stack Test**: Verified `push(100, 200, 300)`, `peek() -> 300`, sequential `pop()`, and underflow guard (`Error: Stack Underflow!`).
- **Queue Test**: Verified `enqueue(1000, 2000, 3000)`, `peek() -> 1000`, sequential `dequeue()`, and underflow guard (`Error: Queue Underflow!`).
- **Linked List Test**: Verified head insertion (`insertHead(1)`), tail insertion, search for present/missing elements, and deletion by value (`15`).
- **Search Comparison**: Verified Linear vs Binary search with targets present and missing.
- **Graph Traversals**: Verified benchmark graph with BFS generating `[1, 2, 3, 4, 5]` and DFS generating `[1, 2, 4, 5, 3]`.
- **Stress Test**: Verified empirical 50,000-element benchmark demonstrating logarithmic efficiency.

---
