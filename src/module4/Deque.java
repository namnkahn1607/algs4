package module4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private class Node {
        Item val;
        Node prev;
        Node next;

        Node(Node prev, Item val, Node next) {
            this.prev = prev;
            this.val = val;
            this.next = next;
        }

        Node(Node prev, Item val) {
            this(prev, val, null);
        }

        Node(Item val, Node next) {
            this(null, val, next);
        }

        Node(Item val) {
            this(null, val, null);
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public Deque() {
        head = null;
        tail = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item val) {
        validateItem(val);

        if (isEmpty()) {
            head = new Node(val);
            tail = head;
        } else {
            Node newNode = new Node(val, head);
            head.prev = newNode;
            head = newNode;
        }

        ++size;
    }

    public void addLast(Item val) {
        validateItem(val);

        if (isEmpty()) {
            tail = new Node(val);
            head = tail;
        } else {
            Node newNode = new Node(tail, val);
            tail.next = newNode;
            tail = newNode;
        }

        ++size;
    }

    private void validateItem(Item val) {
        if (val == null) {
            throw new IllegalArgumentException("Item must be non-null");
        }
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty module4.Deque");
        }

        Item ans = head.val;
        head = head.next;
        --size;

        if (isEmpty()) {
            tail = null;
        } else {
            head.prev = null;
        }

        return ans;
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty module4.Deque");
        }

        Item ans = tail.val;
        tail = tail.prev;
        --size;

        if (isEmpty()) {
            head = null;
        } else {
            tail.next = null;
        }

        return ans;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more element to iterate");
            }

            Item val = current.val;
            current = current.next;

            return val;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("No such operation found");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== module4.Deque Unit Testing ===");

        // Test 1: Constructor
        System.out.println("\n1. Testing Constructor:");
        Deque<String> deque = new Deque<>();
        System.out.println("✓ Constructor called successfully");

        // Test 2: isEmpty() and size() on empty deque
        System.out.println("\n2. Testing isEmpty() and size() on empty deque:");
        System.out.println("isEmpty(): " + deque.isEmpty() + " (expected: true)");
        System.out.println("size(): " + deque.size() + " (expected: 0)");

        // Test 3: addFirst()
        System.out.println("\n3. Testing addFirst():");
        deque.addFirst("second");
        deque.addFirst("first");
        System.out.println("Added 'second' then 'first' to front");
        System.out.println("isEmpty(): " + deque.isEmpty() + " (expected: false)");
        System.out.println("size(): " + deque.size() + " (expected: 2)");

        // Test 4: addLast()
        System.out.println("\n4. Testing addLast():");
        deque.addLast("third");
        deque.addLast("fourth");
        System.out.println("Added 'third' then 'fourth' to back");
        System.out.println("size(): " + deque.size() + " (expected: 4)");

        // Test 5: Iterator (testing hasNext() and next())
        System.out.println("\n5. Testing Iterator:");
        System.out.print("Elements from front to back: ");

        for (String item : deque) {
            System.out.print(item + " ");
        }

        System.out.println("(expected: first second third fourth)");

        // Test 6: removeFirst()
        System.out.println("\n6. Testing removeFirst():");
        String removed = deque.removeFirst();
        System.out.println("Removed: " + removed + " (expected: first)");
        System.out.println("size(): " + deque.size() + " (expected: 3)");
        System.out.print("Remaining elements: ");

        for (String item : deque) {
            System.out.print(item + " ");
        }

        System.out.println("(expected: second third fourth)");

        // Test 7: removeLast()
        System.out.println("\n7. Testing removeLast():");
        removed = deque.removeLast();
        System.out.println("Removed: " + removed + " (expected: fourth)");
        System.out.println("size(): " + deque.size() + " (expected: 2)");
        System.out.print("Remaining elements: ");

        for (String item : deque) {
            System.out.print(item + " ");
        }

        System.out.println("(expected: second third)");

        // Test 8: Remove all elements
        System.out.println("\n8. Testing removing all elements:");
        deque.removeFirst(); // remove "second"
        deque.removeLast();  // remove "third"
        System.out.println("isEmpty(): " + deque.isEmpty() + " (expected: true)");
        System.out.println("size(): " + deque.size() + " (expected: 0)");

        // Test 9: Add to empty deque again
        System.out.println("\n9. Testing operations on empty deque:");
        deque.addFirst("alone");
        System.out.println("Added 'alone' to empty deque");
        System.out.println("size(): " + deque.size() + " (expected: 1)");
        String last = deque.removeLast();
        System.out.println("Removed from back: " + last + " (expected: alone)");
        System.out.println("isEmpty(): " + deque.isEmpty() + " (expected: true)");

        // Test 10: Exception testing - IllegalArgumentException
        System.out.println("\n10. Testing IllegalArgumentException:");

        try {
            deque.addFirst(null);
            System.out.println("✗ Should have thrown IllegalArgumentException for addFirst(null)");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly threw IllegalArgumentException for addFirst(null)");
        }

        try {
            deque.addLast(null);
            System.out.println("✗ Should have thrown IllegalArgumentException for addLast(null)");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly threw IllegalArgumentException for addLast(null)");
        }

        // Test 11: Exception testing - NoSuchElementException for remove operations
        System.out.println("\n11. Testing NoSuchElementException for remove operations:");

        try {
            deque.removeFirst();
            System.out.println("✗ Should have thrown NoSuchElementException for removeFirst() on empty deque");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for removeFirst() on empty deque");
        }

        try {
            deque.removeLast();
            System.out.println("✗ Should have thrown NoSuchElementException for removeLast() on empty deque");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for removeLast() on empty deque");
        }

        // Test 12: Exception testing - Iterator exceptions
        System.out.println("\n12. Testing Iterator exceptions:");
        Iterator<String> it = deque.iterator();

        try {
            it.next();
            System.out.println("✗ Should have thrown NoSuchElementException for next() on empty iterator");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for next() on empty iterator");
        }

        try {
            it.remove();
            System.out.println("✗ Should have thrown UnsupportedOperationException for remove()");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ Correctly threw UnsupportedOperationException for remove()");
        }

        // Test 13: Iterator on non-empty deque
        System.out.println("\n13. Testing Iterator on populated deque:");
        deque.addFirst("A");
        deque.addLast("B");
        deque.addLast("C");

        Iterator<String> iterator = deque.iterator();
        System.out.print("Using iterator manually: ");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println("(expected: A B C)");

        // Test hasNext() when no more elements
        System.out.println("hasNext() after iteration: " + iterator.hasNext() + " (expected: false)");

        // Test 14: Mixed operations
        System.out.println("\n14. Testing mixed operations:");
        Deque<Integer> intDeque = new Deque<>();
        intDeque.addFirst(2);
        intDeque.addFirst(1);
        intDeque.addLast(3);
        intDeque.addLast(4);

        System.out.print("Integer deque: ");
        for (Integer num : intDeque) {
            System.out.print(num + " ");
        }
        System.out.println("(expected: 1 2 3 4)");

        System.out.println("removeFirst(): " + intDeque.removeFirst() + " (expected: 1)");
        System.out.println("removeLast(): " + intDeque.removeLast() + " (expected: 4)");
        System.out.print("Final integer deque: ");
        for (Integer num : intDeque) {
            System.out.print(num + " ");
        }
        System.out.println("(expected: 2 3)");

        System.out.println("\n=== All tests completed ===");
    }
}