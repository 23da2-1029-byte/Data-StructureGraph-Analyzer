/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Queue Operations (FIFO - First In First Out)
 * Implementation: Circular Array with Dynamic Resizing
 * Author     : M.S.F. Noaf  |  Reg No: 23DA2-0975
 * GitHub     : NOAF2002
 * Email      : 23da2-0975@sltc.ac.lk
 */
package datastructures;

public class QueueOperations {
    private int[] queue;
    private int front;
    private int rear;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    public QueueOperations() {
        this.queue = new int[DEFAULT_CAPACITY];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public QueueOperations(int capacity) {
        if (capacity <= 0) {
            capacity = DEFAULT_CAPACITY;
        }
        this.queue = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    /**
     * Enqueues an element at the rear of the queue.
     * Time Complexity: O(1) amortized.
     */
    public void enqueue(int value) {
        if (size == queue.length) {
            resize();
        }
        rear = (rear + 1) % queue.length;
        queue[rear] = value;
        size++;
        System.out.println("Success: Enqueued " + value + " to rear (Size: " + size + ").");
    }

    /**
     * Dequeues an element from the front of the queue.
     * Handles empty queue condition safely.
     * Time Complexity: O(1).
     * @return Integer dequeued value, or null if empty.
     */
    public Integer dequeue() {
        if (isEmpty()) {
            System.out.println("Error: Queue Underflow! Cannot dequeue from an empty queue.");
            return null;
        }
        int dequeuedValue = queue[front];
        front = (front + 1) % queue.length;
        size--;
        System.out.println("Success: Dequeued " + dequeuedValue + " from front.");
        return dequeuedValue;
    }

    /**
     * Peeks at the front element without removing it.
     * Handles empty queue condition safely.
     * Time Complexity: O(1).
     * @return Integer front value, or null if empty.
     */
    public Integer peek() {
        if (isEmpty()) {
            System.out.println("Notice: Queue is currently empty. Nothing to peek.");
            return null;
        }
        System.out.println("Front element is: " + queue[front]);
        return queue[front];
    }

    /**
     * Displays all elements from front to rear.
     */
    public void display() {
        if (isEmpty()) {
            System.out.println("Queue is currently empty.");
            return;
        }
        System.out.print("Queue [FRONT] -> ");
        for (int i = 0; i < size; i++) {
            int idx = (front + i) % queue.length;
            System.out.print(queue[idx]);
            if (i < size - 1) {
                System.out.print(" -> ");
            }
        }
        System.out.println(" -> [REAR] (Count: " + size + ")");
    }

    /**
     * Checks if the queue is empty.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns current number of elements in the queue.
     */
    public int size() {
        return size;
    }

    /**
     * Clears all elements from the queue.
     */
    public void clear() {
        front = 0;
        rear = -1;
        size = 0;
        System.out.println("Queue cleared.");
    }

    private void resize() {
        int newCapacity = queue.length * 2;
        int[] newQueue = new int[newCapacity];
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front + i) % queue.length];
        }
        queue = newQueue;
        front = 0;
        rear = size - 1;
        System.out.println("[Internal] Queue capacity expanded to " + newCapacity);
    }
}
