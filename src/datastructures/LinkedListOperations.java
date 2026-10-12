/**
 * CIT300 Data Structures and Algorithms
 * Graded Practical Assignment 2
 * Component: Singly Linked List Operations
 * Author     : M.S.F. Noaf  |  Reg No: 23DA2-0975
 * GitHub     : NOAF2002
 * Email      : 23da2-0975@sltc.ac.lk
 */
package datastructures;

public class LinkedListOperations {

    /**
     * Internal Node representation for Singly Linked List
     */
    public static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;
    private int size;

    public LinkedListOperations() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Inserts an element at the end (tail) of the linked list.
     * Time Complexity: O(n) traversal to end (or O(1) if tail pointer maintained).
     */
    public void insert(int value) {
        Node newNode = new Node(value);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        System.out.println("Success: Inserted " + value + " at end of linked list.");
    }

    /**
     * Inserts an element at the beginning (head) of the linked list.
     * Time Complexity: O(1).
     */
    public void insertHead(int value) {
        Node newNode = new Node(value);
        newNode.next = head;
        head = newNode;
        size++;
        System.out.println("Success: Inserted " + value + " at head of linked list.");
    }

    /**
     * Deletes the first occurrence of an element with given value.
     * Time Complexity: O(n).
     * @return true if found and removed, false otherwise.
     */
    public boolean delete(int value) {
        if (head == null) {
            System.out.println("Error: Cannot delete from an empty linked list.");
            return false;
        }

        // Deleting the head node
        if (head.data == value) {
            head = head.next;
            size--;
            System.out.println("Success: Deleted " + value + " from head of linked list.");
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.data != value) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Notice: Element " + value + " was not found in the linked list.");
            return false;
        }

        // Bypass target node
        current.next = current.next.next;
        size--;
        System.out.println("Success: Deleted element " + value + " from linked list.");
        return true;
    }

    /**
     * Searches for a value, reports index (0-based) and step count.
     * Time Complexity: O(n).
     * @return index if found, -1 if not found.
     */
    public int search(int value) {
        if (head == null) {
            System.out.println("Notice: Linked list is empty.");
            return -1;
        }

        Node current = head;
        int index = 0;
        int steps = 0;

        while (current != null) {
            steps++;
            if (current.data == value) {
                System.out.println("Success: Found " + value + " at index " + index + " in " + steps + " step(s).");
                return index;
            }
            current = current.next;
            index++;
        }

        System.out.println("Notice: Value " + value + " not found after checking " + steps + " node(s).");
        return -1;
    }

    /**
     * Displays all nodes in the linked list.
     */
    public void display() {
        if (head == null) {
            System.out.println("Linked List is currently empty: [Head -> null]");
            return;
        }
        System.out.print("Linked List: [Head] -> ");
        Node current = head;
        while (current != null) {
            System.out.print("[" + current.data + "]");
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println(" -> null (Total nodes: " + size + ")");
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        head = null;
        size = 0;
        System.out.println("Linked list cleared.");
    }
}
