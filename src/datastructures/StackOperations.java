/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Stack Operations (LIFO - Last In First Out)
 * Author     : M.S.F. Noaf  |  Reg No: 23DA2-0975
 * GitHub     : NOAF2002
 * Email      : 23da2-0975@sltc.ac.lk
 */
package datastructures;

import java.util.Arrays;

public class StackOperations {
    private int[] stack;
    private int top;
    private static final int DEFAULT_CAPACITY = 10;

    public StackOperations() {
        this.stack = new int[DEFAULT_CAPACITY];
        this.top = -1;
    }

    public StackOperations(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.stack = new int[capacity];
        this.top = -1;
    }

    /**
     * Pushes an element onto the top of the stack.
     * Time Complexity: O(1) amortized.
     */
    public void push(int value) {
        if (top == stack.length - 1) {
            resize();
        }
        stack[++top] = value;
        System.out.println("Success: Pushed " + value + " to stack (Top is now index " + top + ").");
    }

    /**
     * Pops the top element from the stack.
     * Handles empty stack condition safely.
     * Time Complexity: O(1).
     * @return Integer value popped, or null if empty.
     */
    public Integer pop() {
        if (isEmpty()) {
            System.out.println("Error: Stack Underflow! Cannot pop from an empty stack.");
            return null;
        }
        int poppedValue = stack[top--];
        System.out.println("Success: Popped " + poppedValue + " from stack.");
        return poppedValue;
    }

    /**
     * Peeks at the top element without removing it.
     * Handles empty stack condition safely.
     * Time Complexity: O(1).
     * @return Integer top value, or null if empty.
     */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Notice: Stack is currently empty. Nothing to peek.");
            return null;
        }
        System.out.println("Top element is: " + stack[top]);
        return stack[top];
    }

    /**
     * Displays all elements from top to bottom.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Stack is currently empty.");
            return;
        }
        System.out.println("Stack elements (Top -> Bottom):");
        for (int i = top; i >= 0; i--) {
            if (i == top) {
                System.out.println("  [TOP]  -> | " + stack[i] + " |");
            } else if (i == 0) {
                System.out.println("  [BASE] -> | " + stack[i] + " |");
            } else {
                System.out.println("            | " + stack[i] + " |");
            }
        }
        System.out.println("            +-----+");
    }

    /**
     * Checks if the stack is empty.
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * Returns current number of elements in the stack.
     */
    public int size() {
        return top + 1;
    }

    /**
     * Clears all elements from the stack.
     */
    public void clear() {
        top = -1;
        System.out.println("Stack cleared.");
    }

    private void resize() {
        int newCapacity = stack.length * 2;
        stack = Arrays.copyOf(stack, newCapacity);
        System.out.println("[Internal] Stack capacity expanded to " + newCapacity);
    }
}
