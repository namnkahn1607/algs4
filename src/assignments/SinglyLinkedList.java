package assignments;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code assignments.SinglyLinkedList} represent a Singly Iterable Linked List
 *  with nested inner class {@code Node} implemented inside.
 *  <p>
 *  Performance Analysis:
 *  O(1) for each operation exposed to the client,
 *  O(<em>N</em>) for constructor
 *  
 *  @author Nguyen Le Nam Khanh
 */

public class SinglyLinkedList<T> implements Iterable<T> {

    private class Node {
        T val;
        Node next;

        Node(T val, Node next) {
            this.val = val;
            this.next = next;
        }

        Node(T val) {
            this(val, null);
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    public void addFirst(T val) {
        head = new Node(val, head);
        ++size;
    }

    public void append(T val) {
        if (isEmpty()) {
            addFirst(val);
            return;
        }

        Node curr = head;

        while (curr.next != null) {
            curr = curr.next;
        }

        curr.next = new Node(val);
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty " + getClass());
        }

        T ans = head.val;
        head = head.next;
        --size;

        return ans;
    }

    public T removeTail() {
        if (isEmpty()) {
            throw new NoSuchElementException("Can not operate on empty " + getClass());
        }

        Node prev = null;
        Node curr = head;

        while (curr.next != null) {
            Node nextNode = curr.next;
            prev = curr;
            curr = nextNode;
        }

        if (prev != null) {
            prev.next = null;
        }

        return curr.val;
    }

    // iterator setup
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {

        private Node curr = head;

        @Override
        public boolean hasNext() {
            return curr != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more item to iterate");
            }

            T ans = curr.val;
            curr = curr.next;

            return ans;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("No such operation found");
        }
    }
}