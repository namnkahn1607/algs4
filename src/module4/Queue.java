/* Iterable Generic Queue implement using Linked List */
package module4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private class Node {
        Item val;
        Node next;

        Node(Item val, Node next) {
            this.val = val;
            this.next = next;
        }

        Node(Item val) {
            this(val, null);
        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;
    private int modCount = 0;

    @Override
    public Iterator<Item> iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node current = head;
        private final int capturedModCount = modCount;

        @Override
        public boolean hasNext() {
            detectModification();
            return current != null;
        }

        @Override
        public Item next() {
            detectModification();
            Item val = current.val;
            current = current.next;

            return val;
        }

        private void detectModification() {
            if (capturedModCount != modCount)
                throw new ConcurrentModificationException("no modification while iterating");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void enqueue(Item val) {
        Node newTail = new Node(val);
        ++modCount;

        if (isEmpty()) {
            head = tail = newTail;
        } else {
            tail.next = newTail;
            tail = newTail;
        }

        ++size;
    }

    public Item dequeue() {
        if (isEmpty()) throw new RuntimeException("Popping from empty queue");

        ++modCount;
        Item ans = head.val;
        head = head.next;
        --size;

        if (isEmpty())
            tail = null;

        return ans;
    }

    public Item front() {
        if (isEmpty()) throw new NoSuchElementException("Empty queue do not have front element");

        return head.val;
    }

    public static void main(String[] args) {
        System.out.println("=== Queue Unit Testing ===");

        // Test 1: Default constructor
        System.out.println("Test 1: Queue Default Constructor");
        Queue<Integer> queue = new Queue<>();
        System.out.println("Queue created successfully");

        // Test 2: isEmpty() on empty queue
        System.out.println("\nTest 2: isEmpty() on empty queue");
        System.out.println("isEmpty(): " + queue.isEmpty()); // Should be true

        // Test 3: size() on empty queue
        System.out.println("\nTest 3: size() on empty queue");
        System.out.println("size(): " + queue.size()); // Should be 0

        // Test 4: front() on empty queue (should throw exception)
        System.out.println("\nTest 4: front() on empty queue");
        try {
            queue.front();
            System.out.println("ERROR: Should have thrown NoSuchElementException");
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw NoSuchElementException: " + e.getMessage());
        }

        // Test 5: dequeue() on empty queue (should throw exception)
        System.out.println("\nTest 5: dequeue() on empty queue");
        try {
            queue.dequeue();
            System.out.println("ERROR: Should have thrown RuntimeException");
        } catch (RuntimeException e) {
            System.out.println("Correctly threw RuntimeException: " + e.getMessage());
        }

        // Test 6: enqueue() single element
        System.out.println("\nTest 6: enqueue() single element");
        queue.enqueue(10);
        System.out.println("Enqueued 10");
        System.out.println("size(): " + queue.size()); // Should be 1
        System.out.println("isEmpty(): " + queue.isEmpty()); // Should be false
        System.out.println("front(): " + queue.front()); // Should be 10

        // Test 7: enqueue() multiple elements
        System.out.println("\nTest 7: enqueue() multiple elements");
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        System.out.println("Enqueued 20, 30, 40");
        System.out.println("size(): " + queue.size()); // Should be 4
        System.out.println("front(): " + queue.front()); // Should be 10 (FIFO)

        // Test 8: Iterator on populated queue
        System.out.println("\nTest 8: Iterator on populated queue");
        System.out.print("Queue contents (front to back): ");
        for (Integer item : queue) {
            System.out.print(item + " ");
        }
        System.out.println(); // Should print: 10 20 30 40

        // Test 9: dequeue() operations
        System.out.println("\nTest 9: dequeue() operations");
        System.out.println("Dequeued: " + queue.dequeue()); // Should be 10
        System.out.println("Dequeued: " + queue.dequeue()); // Should be 20
        System.out.println("size(): " + queue.size()); // Should be 2
        System.out.println("front(): " + queue.front()); // Should be 30

        // Test 10: Iterator after modifications
        System.out.println("\nTest 10: Iterator after modifications");
        System.out.print("Queue contents after dequeues: ");
        for (Integer item : queue) {
            System.out.print(item + " ");
        }
        System.out.println(); // Should print: 30 40

        // Test 11: ConcurrentModificationException
        System.out.println("\nTest 11: ConcurrentModificationException");
        try {
            Iterator<Integer> it = queue.iterator();
            queue.enqueue(99); // Modify queue while iterating
            System.out.println(it.hasNext()); // Should throw ConcurrentModificationException
            System.out.println("ERROR: Should have thrown ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            System.out.println("Correctly threw ConcurrentModificationException: " + e.getMessage());
        }

        // Test 12: Mixed enqueue/dequeue operations
        System.out.println("\nTest 12: Mixed enqueue/dequeue operations");
        queue.dequeue(); // Remove 30
        queue.enqueue(50); // Add 50
        queue.dequeue(); // Remove 40
        queue.enqueue(60); // Add 60
        System.out.println("After mixed operations - size(): " + queue.size()); // Should be 3
        System.out.println("front(): " + queue.front()); // Should be 99

        // Test 13: Empty queue completely
        System.out.println("\nTest 13: Empty queue completely");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
        System.out.println("size(): " + queue.size()); // Should be 0
        System.out.println("isEmpty(): " + queue.isEmpty()); // Should be true

        // Test 14: Queue with different types (String)
        System.out.println("\nTest 14: Queue with String type");
        Queue<String> stringQueue = new Queue<>();
        stringQueue.enqueue("First");
        stringQueue.enqueue("Second");
        stringQueue.enqueue("Third");
        System.out.print("String queue: ");
        for (String s : stringQueue) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Front: " + stringQueue.front());

        // Test 15: Ensure tail is properly managed
        System.out.println("\nTest 15: Tail management test");
        Queue<Integer> tailTest = new Queue<>();
        tailTest.enqueue(1);
        tailTest.dequeue(); // Should set tail to null when empty
        tailTest.enqueue(2); // Should properly set both head and tail
        System.out.println("Tail management test passed - size: " + tailTest.size());
        System.out.println("Front: " + tailTest.front());
    }
}