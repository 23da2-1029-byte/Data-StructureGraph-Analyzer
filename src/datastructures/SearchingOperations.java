/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Searching Algorithms & Comparison
 * Author     : A.M.Fathima Rifatha  |  Reg No: 23DA2-1029  (Team Leader)
 * GitHub     : 23da2-1029-byte
 * Email      : 23da2-1029@sltc.ac.lk
 */
package datastructures;

import java.util.Arrays;

public class SearchingOperations {

    /**
     * Data holder class for recording search results and performance metrics
     */
    public static class SearchResult {
        public final String algorithm;
        public final int target;
        public final boolean found;
        public final int index;
        public final int steps;
        public final long executionTimeNano;

        public SearchResult(String algorithm, int target, boolean found, int index, int steps, long executionTimeNano) {
            this.algorithm = algorithm;
            this.target = target;
            this.found = found;
            this.index = index;
            this.steps = steps;
            this.executionTimeNano = executionTimeNano;
        }

        @Override
        public String toString() {
            return String.format("%-15s | Target: %-5d | Found: %-5b | Index: %-4d | Steps: %-6d | Time: %-8d ns",
                    algorithm, target, found, index, steps, executionTimeNano);
        }
    }

    /**
     * Linear Search implementation.
     * Iterates through elements sequentially.
     * Works on both unsorted and sorted arrays.
     * Time Complexity: Best O(1), Worst/Average O(n)
     * Space Complexity: O(1)
     */
    public static SearchResult linearSearch(int[] arr, int target) {
        long startTime = System.nanoTime();
        int steps = 0;
        int foundIndex = -1;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            steps++;
            if (arr[i] == target) {
                foundIndex = i;
                found = true;
                break;
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        return new SearchResult("Linear Search", target, found, foundIndex, steps, elapsedTime);
    }

    /**
     * Binary Search implementation (Iterative).
     * Requires the input array to be SORTED in ascending order.
     * Divides search space in half at each step.
     * Time Complexity: Best O(1), Worst/Average O(log n)
     * Space Complexity: O(1)
     */
    public static SearchResult binarySearch(int[] sortedArr, int target) {
        long startTime = System.nanoTime();
        int left = 0;
        int right = sortedArr.length - 1;
        int steps = 0;
        int foundIndex = -1;
        boolean found = false;

        while (left <= right) {
            steps++;
            int mid = left + (right - left) / 2;

            if (sortedArr[mid] == target) {
                foundIndex = mid;
                found = true;
                break;
            } else if (sortedArr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        long elapsedTime = System.nanoTime() - startTime;
        return new SearchResult("Binary Search", target, found, foundIndex, steps, elapsedTime);
    }

    /**
     * Compares Linear Search vs Binary Search on the given array and target.
     * Prints step count, execution time, and algorithmic complexity analysis.
     */
    public static void compareSearches(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            System.out.println("Cannot run comparison on an empty or null array.");
            return;
        }

        System.out.println("\n========================================================");
        System.out.println("              SEARCHING PERFORMANCE COMPARISON          ");
        System.out.println("========================================================");
        System.out.println("Array Size: " + arr.length + " elements | Target value: " + target);

        // Run Linear Search on original array
        SearchResult linearRes = linearSearch(arr, target);

        // For Binary Search, create and sort a copy to ensure sorted prerequisite
        int[] sortedCopy = Arrays.copyOf(arr, arr.length);
        Arrays.sort(sortedCopy);
        SearchResult binaryRes = binarySearch(sortedCopy, target);

        System.out.println("\n--------------------------------------------------------");
        System.out.printf("%-15s | %-8s | %-8s | %-12s\n", "Algorithm", "Found?", "Steps", "Time (ns)");
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-15s | %-8b | %-8d | %-12d\n",
                linearRes.algorithm, linearRes.found, linearRes.steps, linearRes.executionTimeNano);
        System.out.printf("%-15s | %-8b | %-8d | %-12d\n",
                binaryRes.algorithm, binaryRes.found, binaryRes.steps, binaryRes.executionTimeNano);
        System.out.println("--------------------------------------------------------");

        System.out.println("\n[Theoretical & Empirical Analysis]");
        System.out.println("1. Linear Search:");
        System.out.println("   - Time Complexity: O(n) worst/average, O(1) best case.");
        System.out.println("   - Prerequisite: Does NOT require sorted data.");
        System.out.println("   - Evaluated elements sequentially: " + linearRes.steps + " steps taken.");
        System.out.println("2. Binary Search:");
        System.out.println("   - Time Complexity: O(log n) worst/average, O(1) best case.");
        System.out.println("   - Prerequisite: Array MUST be sorted in advance.");
        System.out.println("   - Repeatedly halved search space: " + binaryRes.steps + " steps taken.");

        if (linearRes.steps > binaryRes.steps) {
            System.out.println(">> Result: Binary Search required " + (linearRes.steps - binaryRes.steps)
                    + " fewer comparisons (" + String.format("%.1fx", (double) linearRes.steps / Math.max(1, binaryRes.steps))
                    + " step efficiency gain)!");
        } else {
            System.out.println(">> Result: Both searches finished in few steps due to small data size or target position.");
        }
        System.out.println("========================================================\n");
    }
}
