/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Array Operations
 * Author     : A.M.Fathima Rifatha  |  Reg No: 23DA2-1029  (Team Leader)
 * GitHub     : 23da2-1029-byte
 * Email      : 23da2-1029@sltc.ac.lk
 */
package datastructures;

import java.util.Arrays;

public class ArrayOperations {
    private int[] arr;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public ArrayOperations() {
        this.arr = new int[DEFAULT_CAPACITY];
        this.size = 0;
    }

    public ArrayOperations(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.arr = new int[capacity];
        this.size = 0;
    }

    /**
     * Inserts an element at the end of the array.
     * Time Complexity: O(1) amortized, O(n) when resizing.
     */
    public boolean insert(int value) {
        if (size >= arr.length) {
            resize();
        }
        arr[size++] = value;
        System.out.println("Success: Inserted " + value + " at index " + (size - 1));
        return true;
    }

    /**
     * Inserts an element at a specific index.
     * Time Complexity: O(n) due to shifting elements.
     */
    public boolean insertAt(int index, int value) {
        if (index < 0 || index > size) {
            System.out.println("Error: Index " + index + " out of bounds. Valid range: [0, " + size + "]");
            return false;
        }
        if (size >= arr.length) {
            resize();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        size++;
        System.out.println("Success: Inserted " + value + " at index " + index);
        return true;
    }

    /**
     * Deletes the first occurrence of a value from the array.
     * Time Complexity: O(n) search + shift.
     */
    public boolean delete(int value) {
        int index = searchIndex(value);
        if (index == -1) {
            System.out.println("Error: Value " + value + " not found in array.");
            return false;
        }
        return deleteAt(index);
    }

    /**
     * Deletes an element at a specific index.
     * Time Complexity: O(n) due to shifting elements.
     */
    public boolean deleteAt(int index) {
        if (size == 0) {
            System.out.println("Error: Array is empty. Nothing to delete.");
            return false;
        }
        if (index < 0 || index >= size) {
            System.out.println("Error: Invalid index " + index + ". Valid range: [0, " + (size - 1) + "]");
            return false;
        }
        int removedValue = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        size--;
        System.out.println("Success: Deleted " + removedValue + " from index " + index);
        return true;
    }

    /**
     * Searches for a value using Linear Search and records step count.
     * Time Complexity: O(n) worst/average, O(1) best.
     */
    public int search(int value) {
        if (size == 0) {
            System.out.println("Array is empty.");
            return -1;
        }
        int steps = 0;
        for (int i = 0; i < size; i++) {
            steps++;
            if (arr[i] == value) {
                System.out.println("Element " + value + " found at index " + i + " in " + steps + " step(s).");
                return i;
            }
        }
        System.out.println("Element " + value + " not found after " + steps + " step(s).");
        return -1;
    }

    private int searchIndex(int value) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Displays all active elements in the array.
     */
    public void display() {
        if (size == 0) {
            System.out.println("Array is currently empty: []");
            return;
        }
        System.out.print("Array [" + size + "/" + arr.length + "]: [");
        for (int i = 0; i < size; i++) {
            System.out.print(arr[i]);
            if (i < size - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /**
     * Resizes internal array by doubling its capacity.
     */
    private void resize() {
        int newCapacity = arr.length * 2;
        arr = Arrays.copyOf(arr, newCapacity);
        System.out.println("[Internal] Array capacity expanded to " + newCapacity);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int[] getArray() {
        return Arrays.copyOf(arr, size);
    }

    public void clear() {
        size = 0;
        System.out.println("Array cleared.");
    }
}
