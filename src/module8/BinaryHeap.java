package module8;

import java.util.Arrays;

public abstract class BinaryHeap<Key extends Comparable<Key>> {
    protected Key[] keys;
    protected int size;
    protected int capacity;

    abstract void swim(int i);

    abstract void sink(int i);

    protected void swap(int i, int j) {
        Key tmp = keys[i];
        keys[i] = keys[j];
        keys[j] = tmp;
    }

    public void insert(Key val) {
        if (size + 1 > capacity) {
            grow();
        }

        size += 1;
        keys[size] = val;
        swim(size);
    }

    public Key remove() {
        if (isEmpty()) {
            return null;
        }

        Key ans = keys[1];
        keys[1] = keys[size];
        keys[size] = null;
        size -= 1;

        if (size > 0) {
            sink(1);
        }

        if (size <= capacity / 4 && capacity > 1) {
            shrink();
        }

        return ans;
    }

    private void grow() {
        resize(2 * capacity);
    }

    private void shrink() {
        resize(Math.max(2, capacity / 2));
    }

    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Key[] newKeys = (Key[]) new Comparable[1 + newCapacity];
        newKeys[0] = null;

        System.arraycopy(keys, 1, newKeys, 1, size);
        this.capacity = newCapacity;
        this.keys = newKeys;
    }

    public Key peek() {
        if (isEmpty()) {
            return null;
        }

        return keys[1];
    }

    public Key[] toArray() {
        return Arrays.copyOfRange(keys, 1, 1 + size());
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }
}