package module4;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INITIAL_CAPACITY = 8;
    private Item[] array;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        array = (Item[]) new Object[INITIAL_CAPACITY];
        capacity = INITIAL_CAPACITY;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item val) {
        validateItem(val);

        if (size == capacity) {
            grow();
        }

        array[size] = val;
        ++size;
    }

    private void grow() {
        resize(2 * capacity);
    }

    private void validateItem(Item val) {
        if (val == null) {
            throw new IllegalArgumentException("Item must be non-null");
        }
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty Queue");
        }

        int pos = StdRandom.uniformInt(size);
        Item ans = array[pos];
        array[pos] = array[--size];

        if (size > 0 && size <= capacity / 4 && capacity > 0) {
            shrink();
        }

        return ans;
    }

    private void shrink() {
        resize(Math.max(1, capacity / 2));
    }

    private void resize(int newCapacity) {
        Item[] newArray = (Item[]) new Object[newCapacity];
        System.arraycopy(array, 0, newArray, 0, size);

        array = newArray;
        capacity = newCapacity;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty Queue");
        }

        int pos = StdRandom.uniformInt(size);

        return array[pos];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedIterator();
    }

    private class RandomizedIterator implements Iterator<Item> {

        private final int[] indices;
        private int current;

        public RandomizedIterator() {
            indices = new int[size()];
            current = 0;

            for (int i = 0; i < size; ++i) {
                indices[i] = i;
            }

            StdRandom.shuffle(indices);
        }

        @Override
        public boolean hasNext() {
            return current < indices.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more item to iterate");
            }

            Item ans = array[indices[current]];
            ++current;

            return ans;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("No such operation found");
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Randomized Queue Unit Testing ===");

        // Test 1: Constructor
        System.out.println("\n1. Testing Constructor:");
        RandomizedQueue<String> queue = new RandomizedQueue<>();
        System.out.println("✓ Constructor called successfully");

        // Test 2: isEmpty() and size() on empty queue
        System.out.println("\n2. Testing isEmpty() and size() on empty queue:");
        System.out.println("isEmpty(): " + queue.isEmpty() + " (expected: true)");
        System.out.println("size(): " + queue.size() + " (expected: 0)");

        // Test 3: enqueue()
        System.out.println("\n3. Testing enqueue():");
        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        System.out.println("Added 4 elements: A, B, C, D");
        System.out.println("isEmpty(): " + queue.isEmpty() + " (expected: false)");
        System.out.println("size(): " + queue.size() + " (expected: 4)");

        // Test 4: sample() - should not change size
        System.out.println("\n4. Testing sample():");
        System.out.println("Sample 1: " + queue.sample());
        System.out.println("Sample 2: " + queue.sample());
        System.out.println("Sample 3: " + queue.sample());
        System.out.println("size(): " + queue.size() + " (expected: 4 - size unchanged)");
        System.out.println("Note: Samples should be random, may repeat");

        // Test 5: Iterator - random order
        System.out.println("\n5. Testing Iterator (random order):");
        System.out.print("Iterator 1: ");

        for (String item : queue) {
            System.out.print(item + " ");
        }

        System.out.println("(should contain A B C D in some random order)");

        System.out.print("Iterator 2: ");

        for (String item : queue) {
            System.out.print(item + " ");
        }

        System.out.println("(should be different order from Iterator 1)");

        // Test 6: Iterator independence
        System.out.println("\n6. Testing Iterator independence:");
        Iterator<String> it1 = queue.iterator();
        Iterator<String> it2 = queue.iterator();

        System.out.print("Iterator 1 elements: ");

        while (it1.hasNext()) {
            System.out.print(it1.next() + " ");
        }

        System.out.println();

        System.out.print("Iterator 2 elements: ");

        while (it2.hasNext()) {
            System.out.print(it2.next() + " ");
        }

        System.out.println("(should be different random order)");

        // Test 7: dequeue() - should reduce size
        System.out.println("\n7. Testing dequeue():");
        String dequeued1 = queue.dequeue();
        String dequeued2 = queue.dequeue();
        System.out.println("Dequeued: " + dequeued1 + ", " + dequeued2);
        System.out.println("size(): " + queue.size() + " (expected: 2)");

        System.out.print("Remaining elements: ");

        for (String item : queue) {
            System.out.print(item + " ");
        }

        System.out.println("(should be 2 remaining elements)");

        // Test 8: Dequeue all elements
        System.out.println("\n8. Testing dequeue until empty:");

        while (!queue.isEmpty()) {
            String item = queue.dequeue();
            System.out.println("Dequeued: " + item + ", size now: " + queue.size());
        }
        System.out.println("isEmpty(): " + queue.isEmpty() + " (expected: true)");

        // Test 9: Array resizing (growth)
        System.out.println("\n9. Testing array resizing (growth):");
        RandomizedQueue<Integer> intQueue = new RandomizedQueue<>();
        System.out.println("Adding 20 elements to test array growth...");

        for (int i = 1; i <= 20; i++) {
            intQueue.enqueue(i);
            if (i % 5 == 0) {
                System.out.println("Added " + i + " elements, size: " + intQueue.size());
            }
        }

        // Test 10: Array resizing (shrinkage)
        System.out.println("\n10. Testing array resizing (shrinkage):");
        System.out.println("Removing elements to test array shrinking...");

        for (int i = 0; i < 15; i++) {
            intQueue.dequeue();
            if ((i + 1) % 5 == 0) {
                System.out.println("Removed " + (i + 1) + " elements, size: " + intQueue.size());
            }
        }

        // Test 11: Exception testing - IllegalArgumentException
        System.out.println("\n11. Testing IllegalArgumentException:");

        try {
            queue.enqueue(null);
            System.out.println("✗ Should have thrown IllegalArgumentException for enqueue(null)");
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Correctly threw IllegalArgumentException for enqueue(null)");
        }

        // Test 12: Exception testing - NoSuchElementException for empty queue operations
        System.out.println("\n12. Testing NoSuchElementException for empty queue operations:");
        RandomizedQueue<String> emptyQueue = new RandomizedQueue<>();

        try {
            emptyQueue.dequeue();
            System.out.println("✗ Should have thrown NoSuchElementException for dequeue() on empty queue");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for dequeue() on empty queue");
        }

        try {
            emptyQueue.sample();
            System.out.println("✗ Should have thrown NoSuchElementException for sample() on empty queue");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for sample() on empty queue");
        }

        // Test 13: Exception testing - Iterator exceptions
        System.out.println("\n13. Testing Iterator exceptions:");
        Iterator<String> emptyIt = emptyQueue.iterator();

        try {
            emptyIt.next();
            System.out.println("✗ Should have thrown NoSuchElementException for next() on empty iterator");
        } catch (NoSuchElementException e) {
            System.out.println("✓ Correctly threw NoSuchElementException for next() on empty iterator");
        }

        try {
            emptyIt.remove();
            System.out.println("✗ Should have thrown UnsupportedOperationException for remove()");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ Correctly threw UnsupportedOperationException for remove()");
        }

        // Test 14: hasNext() behavior
        System.out.println("\n14. Testing hasNext() behavior:");
        RandomizedQueue<String> testQueue = new RandomizedQueue<>();
        testQueue.enqueue("X");
        testQueue.enqueue("Y");
        testQueue.enqueue("Z");

        Iterator<String> testIt = testQueue.iterator();
        System.out.println("hasNext(): " + testIt.hasNext() + " (expected: true)");
        System.out.println("next(): " + testIt.next());
        System.out.println("hasNext(): " + testIt.hasNext() + " (expected: true)");
        System.out.println("next(): " + testIt.next());
        System.out.println("hasNext(): " + testIt.hasNext() + " (expected: true)");
        System.out.println("next(): " + testIt.next());
        System.out.println("hasNext(): " + testIt.hasNext() + " (expected: false)");

        // Test 15: Randomness verification
        System.out.println("\n15. Testing randomness (sample multiple times):");
        RandomizedQueue<String> randQueue = new RandomizedQueue<>();
        randQueue.enqueue("Apple");
        randQueue.enqueue("Banana");
        randQueue.enqueue("Cherry");
        randQueue.enqueue("Date");

        System.out.println("Sampling 10 times from same queue:");

        for (int i = 0; i < 10; i++) {
            System.out.print(randQueue.sample() + " ");
        }

        System.out.println("\n(should show random distribution, may have repeats)");

        System.out.println("Dequeuing 4 times from same queue:");

        for (int i = 0; i < 4; i++) {
            System.out.print(randQueue.dequeue() + " ");
        }

        System.out.println("\n(should be random order, no repeats)");

        // Test 16: Different data types
        System.out.println("\n16. Testing with different data types:");
        RandomizedQueue<Double> doubleQueue = new RandomizedQueue<>();
        doubleQueue.enqueue(4.38);
        doubleQueue.enqueue(2.71);
        doubleQueue.enqueue(1.41);

        System.out.print("Double queue: ");

        for (Double d : doubleQueue) {
            System.out.print(d + " ");
        }

        System.out.println("(expected: 3.14, 2.71, 1.41 in random order)");

        System.out.println("\n=== All tests completed ===");
    }
}