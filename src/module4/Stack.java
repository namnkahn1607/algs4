/* Iterable Generic Stack implemented using Linked List */
package module4;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Item> implements Iterable<Item> {

    private class Node {
        Item val;
        Node next;

        Node(Item val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    private Node head = null;
    private int size = 0;
    private int modCount = 0;

    @Override
    public Iterator<Item> iterator() {
        return new StackIterator();
    }

    private class StackIterator implements Iterator<Item> {

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

    public void push(Item val) {
        ++size;
        ++modCount;
        head = new Node(val, head);
    }

    public Item pop() {
        if (isEmpty()) throw new RuntimeException("Popping from empty stack");

        ++modCount;
        Item ans = head.val;
        head = head.next;
        --size;

        return ans;
    }

    public Item peek() {
        if (isEmpty()) throw new NoSuchElementException("Empty stack do not have peek element");

        return head.val;
    }

    public static void main(String[] args) {
        System.out.println("=== Stack Unit Testing ===");

        // Test 1: Default constructor
        System.out.println("Test 1: Stack Default Constructor");
        Stack<Integer> stack = new Stack<>();
        System.out.println("Stack created successfully");

        // Test 2: isEmpty() on empty stack
        System.out.println("\nTest 2: isEmpty() on empty stack");
        System.out.println("isEmpty(): " + stack.isEmpty()); // Should be true

        // Test 3: size() on empty stack
        System.out.println("\nTest 3: size() on empty stack");
        System.out.println("size(): " + stack.size()); // Should be 0

        // Test 4: peek() on empty stack (should throw exception)
        System.out.println("\nTest 4: peek() on empty stack");
        try {
            stack.peek();
            System.out.println("ERROR: Should have thrown NoSuchElementException");
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw NoSuchElementException: " + e.getMessage());
        }

        // Test 5: pop() on empty stack (should throw exception)
        System.out.println("\nTest 5: pop() on empty stack");
        try {
            stack.pop();
            System.out.println("ERROR: Should have thrown RuntimeException");
        } catch (RuntimeException e) {
            System.out.println("Correctly threw RuntimeException: " + e.getMessage());
        }

        // Test 6: push() single element
        System.out.println("\nTest 6: push() single element");
        stack.push(10);
        System.out.println("Pushed 10");
        System.out.println("size(): " + stack.size()); // Should be 1
        System.out.println("isEmpty(): " + stack.isEmpty()); // Should be false
        System.out.println("peek(): " + stack.peek()); // Should be 10

        // Test 7: push() multiple elements
        System.out.println("\nTest 7: push() multiple elements");
        stack.push(20);
        stack.push(30);
        stack.push(40);
        System.out.println("Pushed 20, 30, 40");
        System.out.println("size(): " + stack.size()); // Should be 4
        System.out.println("peek(): " + stack.peek()); // Should be 40 (LIFO)

        // Test 8: Iterator on populated stack
        System.out.println("\nTest 8: Iterator on populated stack");
        System.out.print("Stack contents (top to bottom): ");
        for (Integer item : stack) {
            System.out.print(item + " ");
        }
        System.out.println(); // Should print: 40 30 20 10

        // Test 9: pop() operations
        System.out.println("\nTest 9: pop() operations");
        System.out.println("Popped: " + stack.pop()); // Should be 40
        System.out.println("Popped: " + stack.pop()); // Should be 30
        System.out.println("size(): " + stack.size()); // Should be 2
        System.out.println("peek(): " + stack.peek()); // Should be 20

        // Test 10: Iterator after modifications
        System.out.println("\nTest 10: Iterator after modifications");
        System.out.print("Stack contents after pops: ");
        for (Integer item : stack) {
            System.out.print(item + " ");
        }
        System.out.println(); // Should print: 20 10

        // Test 11: ConcurrentModificationException
        System.out.println("\nTest 11: ConcurrentModificationException");
        try {
            Iterator<Integer> it = stack.iterator();
            stack.push(99); // Modify stack while iterating
            System.out.println(it.hasNext()); // Should throw ConcurrentModificationException
            System.out.println("ERROR: Should have thrown ConcurrentModificationException");
        } catch (ConcurrentModificationException e) {
            System.out.println("Correctly threw ConcurrentModificationException: " + e.getMessage());
        }

        // Test 12: Empty stack completely
        System.out.println("\nTest 12: Empty stack completely");
        stack.pop(); // Remove 99 (was added in previous test)
        stack.pop(); // Remove 20
        stack.pop(); // Remove 10
        System.out.println("Popped all elements");
        System.out.println("size(): " + stack.size()); // Should be 0
        System.out.println("isEmpty(): " + stack.isEmpty()); // Should be true

        // Test 13: Stack with different types (String)
        System.out.println("\nTest 13: Stack with String type");
        Stack<String> stringStack = new Stack<>();
        stringStack.push("Hello");
        stringStack.push("World");
        stringStack.push("Stack");
        System.out.print("String stack: ");
        for (String s : stringStack) {
            System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("Peek: " + stringStack.peek());
    }
}